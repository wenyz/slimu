package demo.drone;

import demo.drone.device.DemoSensorMessage;
import demo.drone.device.SensorMessage;

import java.util.ArrayList;
import java.util.List;

public class DeviceState {
    private static final List<SensorMessage> messageQueue = new ArrayList<SensorMessage>(20);//TODO data struct should be reconsidered
    private static int flashCapacity = 30;
    private Object messageQueueLock = new Object();

    public SensorMessage send() throws Exception {
        SensorMessage result = null;
        synchronized (messageQueueLock) {

            while (messageQueue.size() > flashCapacity) {
                messageQueueLock.wait();
            }
            // TODO
            result = messageQueue.get(0);
            // execute callback
            if(result.getSensor().getCallback() != null){
                result.getSensor().getCallback().deliveryComplete(new DemoSensorMessage(result.getPayload()));
            }
            messageQueue.remove(0);
            messageQueueLock.notifyAll();
        }
        return result;
    }

    public void receive(SensorMessage message) throws Exception{
        synchronized (messageQueueLock){

            while (messageQueue.size() < 0){
                messageQueue.wait();
            }
            messageQueue.add(message);
            // execute callback
            if(message.getSensor().getCallback() != null){
                message.getSensor().getCallback().messageArrived(new DemoSensorMessage(message.getPayload()));
            }
            messageQueueLock.notifyAll();
        }
    }
}
