package hmi;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author wenyz
 */
public class MqttBean {

    private MqttClient mqttClient;
    public MqttBean(){

        try {
            if (mqttClient == null) {
                mqttClient = getMqttClient();
            }


            mqttClient.subscribe("/test/hmi/001");
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable throwable) {

                }

                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    WebSocketServer.sendInfo(new String(mqttMessage.getPayload()));
                }

                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

                }
            });
        } catch (Exception e) {
        }


    }

    private MqttClient getMqttClient() throws Exception {
        String broker = "tcp://121.43.180.66:1883";
        String clientId = "hmi-device";
        MemoryPersistence persistence = new MemoryPersistence();
        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        System.out.println("Connecting to broker: " + broker);

        connOpts.setUserName("admin");
        connOpts.setPassword("admin".toCharArray());
        sampleClient.connect(connOpts);

        return sampleClient;
    }
}
