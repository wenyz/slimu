package demo.drone.internal;

import javax.net.SocketFactory;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPNetworkModule implements NetworkModule {

    protected Socket socket;
    private SocketFactory factory;
    private String host;
    private int port;
    private int conTimeout;

    public TCPNetworkModule(Socket socket, SocketFactory factory, String host, int port, int conTimeout) {
        this.socket = socket;
        this.factory = factory;
        this.host = host;
        this.port = port;
        this.conTimeout = conTimeout;
    }

    public void start() throws Exception {
        System.out.println("TCP network module has started");
    }

    public void stop() throws Exception {
        System.out.println("TCP network module has stopped");
    }

    public InputStream getInputStream() throws Exception {
        return socket.getInputStream();
    }

    public OutputStream getOutputStream() throws Exception {
        return socket.getOutputStream();
    }
}
