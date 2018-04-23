package demo.drone;

import java.util.ArrayList;
import java.util.List;

public class DeviceManager {

    private static final List<Device> devices = new ArrayList<Device>();
    private final  DeviceState deviceState;

    public DeviceManager(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public void registerDevice(Device device){

        devices.add(device);
    }

    public List<Device> getDevices(){
        return  devices;
    }


}
