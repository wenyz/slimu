package demo.drone.device;

import demo.drone.Device;
import demo.drone.DroneMessage;

public class DemoSensorMessage implements DroneMessage {
    private byte[] payload;
    private TimesliceSensor sensor;

    public DemoSensorMessage() {
        this.payload = new byte[]{};
    }

    public DemoSensorMessage(byte[] payload) {
        this.payload = payload;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public TimesliceSensor getSensor() {
        return sensor;
    }

    public void setSensor(TimesliceSensor sensor) {
        this.sensor = (TimesliceSensor)sensor;//TODO check type
    }
}
