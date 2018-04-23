package demo.drone.device;

import demo.drone.Callback;
import demo.drone.Device;

public interface TimesliceSensor extends Device{

    byte[] getOutputMessage();
    void setCallback(Callback callback);
    Callback getCallback();
}
