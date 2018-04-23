package demo.drone.device;

import demo.drone.Callback;
import demo.drone.DroneMessage;

public class TimesliceSensorCallback implements Callback {

    public void connectionLost(Throwable cause) {

    }

    public void connectionComplete(String url) {

    }

    public void messageArrived(DroneMessage droneMessage) throws Exception {
        System.out.println("message arrive callback method has been executed");
    }

    public void deliveryComplete(DroneMessage droneMessage) {

    }
}
