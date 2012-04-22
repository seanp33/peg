package peg.bayeux.server;

import com.google.common.eventbus.EventBus;
import org.cometd.server.AbstractServerTransport;
import org.cometd.server.CometdServlet;
import org.cometd.server.JacksonJSONContextServer;
import org.cometd.websocket.server.WebSocketTransport;
import org.eclipse.jetty.server.Server;
import org.cometd.server.BayeuxServerImpl;
import org.eclipse.jetty.server.handler.HandlerWrapper;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.ssl.SslSelectChannelConnector;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.thread.ExecutorThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import peg.bayeux.client.BayeuxPegClient;
import peg.bayeux.server.service.GroupInformationService;
import peg.server.PegServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.management.ManagementFactory;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.util.ssl.SslContextFactory;

public class BayeuxPegServer implements PegServer {
    // logger
    protected final static Logger logger = LoggerFactory.getLogger(BayeuxPegClient.class);

    private EventBus eventBus;
    // internal jetty server. will need to properly abstract the rest of the system away from this
    // @Inject, guice or spring
    private Server jettyServer;

    // TODO: assign via configuration
    private boolean ssl = false;
    private int port = 8080;
    private int maxThreads = 30;
    private String cometServletPath = "/peg";

    public BayeuxPegServer(int port, EventBus eventBus) {
        this.port = port;
        this.eventBus = eventBus;
    }


    @Override
    public void initialize() throws Exception {

        jettyServer = new Server();

        // Setup JMX
        MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
        jettyServer.getContainer().addEventListener(mbContainer);
        jettyServer.addBean(mbContainer);

        SelectChannelConnector connector;
        if (ssl) {
            SslSelectChannelConnector sslConnector = new SslSelectChannelConnector();
            File keyStoreFile = new File("src/main/resources/keystore.jks");
            if (!keyStoreFile.exists()) {
                throw new FileNotFoundException(keyStoreFile.getAbsolutePath());
            }
            SslContextFactory sslContextFactory = sslConnector.getSslContextFactory();
            sslContextFactory.setKeyStorePath(keyStoreFile.getAbsolutePath());
            sslContextFactory.setKeyStorePassword("storepwd");
            sslContextFactory.setKeyManagerPassword("keypwd");
            //sslConnector.setUseDirectBuffers(true);
            connector = sslConnector;
        } else {
            connector = new SelectChannelConnector();
        }

        // TODO: correctly configure these params or remove
        // Make sure the OS is configured properly for load testing;
        // see http://cometd.org/documentation/howtos/loadtesting
        connector.setAcceptQueueSize(2048);
        // Make sure the server timeout on a TCP connection is large
        connector.setMaxIdleTime(240000);

        // TODO: what are acceptors?
        // connector.setAcceptors(acceptors);

        connector.setPort(port);
        jettyServer.addConnector(connector);


        ExecutorThreadPool jettyThreadPool = new ExecutorThreadPool(maxThreads, maxThreads, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        jettyServer.setThreadPool(jettyThreadPool);

        HandlerWrapper handler = jettyServer;

        String contextPath = "";
        ServletContextHandler context = new ServletContextHandler(handler, contextPath, ServletContextHandler.SESSIONS);

        // Setup default servlet to serve static files
        context.addServlet(DefaultServlet.class, "/");

        // Setup comet servlet
        CometdServlet cometServlet = new CometdServlet();
        ServletHolder cometdServletHolder = new ServletHolder(cometServlet);
        // Make sure the expiration timeout is large to avoid clients to timeout
        // This value must be several times larger than the client value
        // (e.g. 60 s on server vs 5 s on client) so that it's guaranteed that
        // it will be the client to dispose idle connections.
        cometdServletHolder.setInitParameter(AbstractServerTransport.MAX_INTERVAL_OPTION, String.valueOf(60000));
        // Explicitly set the timeout value
        cometdServletHolder.setInitParameter(AbstractServerTransport.TIMEOUT_OPTION, String.valueOf(30000));
        // Use the faster JSON parser/generator
        cometdServletHolder.setInitParameter(BayeuxServerImpl.JSON_CONTEXT, JacksonJSONContextServer.class.getName());
        context.addServlet(cometdServletHolder, cometServletPath + "/*");

        jettyServer.start();

        BayeuxServerImpl bayeux = cometServlet.getBayeux();

        ThreadPoolExecutor websocketThreadPool = new ThreadPoolExecutor(maxThreads, maxThreads, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        LoadWebSocketTransport webSocketTransport = new LoadWebSocketTransport(bayeux, websocketThreadPool);
        webSocketTransport.init();
        bayeux.addTransport(webSocketTransport);
        bayeux.setAllowedTransports("websocket", "long-polling");

        // configure GroupInformationService
        new GroupInformationService(bayeux, "group-information-service", maxThreads);
    }


    @Override
    public void shutDown() throws Exception {
        this.jettyServer.stop();
    }

    public static class LoadWebSocketTransport extends WebSocketTransport {
        private final Executor executor;

        public LoadWebSocketTransport(BayeuxServerImpl bayeux, Executor executor) {
            super(bayeux);
            this.executor = executor;
        }

        @Override
        protected Executor newExecutor() {
            return executor;
        }
    }

    /**
     * Driver for testing
     *
     * @param args String[] containing port to run server
     */
    public static void main(String[] args) {
        new BayeuxPegServer(Integer.parseInt(args[0]), new EventBus());
    }

}
