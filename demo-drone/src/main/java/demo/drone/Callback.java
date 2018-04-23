package demo.drone;

public interface Callback {

    void connectionLost(Throwable cause);
    void connectionComplete(String url);

    void messageArrived(DroneMessage droneMessage) throws Exception;
    void deliveryComplete(DroneMessage droneMessage) ;
}
