package demo.drone.device;

import demo.drone.Device;

public interface SensorMessage {
    TimesliceSensor getSensor();
    byte[] getPayload();
}
