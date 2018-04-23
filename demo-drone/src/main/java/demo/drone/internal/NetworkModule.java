package demo.drone.internal;

import java.io.InputStream;
import java.io.OutputStream;

public interface NetworkModule {

    void start() throws Exception;
    void stop() throws Exception;

    InputStream getInputStream() throws Exception;
    OutputStream getOutputStream() throws Exception;
}
