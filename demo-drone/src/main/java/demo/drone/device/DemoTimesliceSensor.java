package demo.drone.device;

import demo.drone.Callback;

public class DemoTimesliceSensor implements TimesliceSensor {

    private byte[] payload;
    private Callback callback;

    public byte[] getOutputMessage() {
        return payload;
    }

    public void start() throws Exception {
        System.out.println("timeslice sensor has stated");
        new Thread(new Runnable() {
            public void run() {
                payload = "message form demo timeslice sensor".getBytes();

                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stop() throws Exception {
        System.out.println("timeslice sensor has stopped");
    }

    public void setCallback(Callback callback) {
        this.callback = new TimesliceSensorCallback();
    }

    public Callback getCallback() {
        return callback;
    }
}
