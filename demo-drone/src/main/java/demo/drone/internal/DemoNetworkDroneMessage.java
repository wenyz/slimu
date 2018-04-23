package demo.drone.internal;

import demo.drone.DroneMessage;

public class DemoNetworkDroneMessage implements DroneMessage {

    private byte[] payload;

    public DemoNetworkDroneMessage() {
        this.payload  = new byte[]{};
    }

    public DemoNetworkDroneMessage(byte[] payload) {
        this.payload = payload;
    }

    public byte[] getPayload(){
        return this.payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }
}
