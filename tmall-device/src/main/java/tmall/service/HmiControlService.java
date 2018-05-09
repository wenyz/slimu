package tmall.service;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wenyz
 */
@Service
public class HmiControlService {

    private static final Logger logger = LoggerFactory.getLogger(HmiControlService.class);
    private static final String TOPIC_DEVICE = "test/hmi/001";
    private static final String OPEN_AIR = "打开空调";
    private static final String ADJUST_AIR = "空调温度为23度";
    private static final String CLOSE_AIR = "关闭空调";
    private static final String CALL_PHONE = "拨打1010110";
    private static final String OPEN_WINDOW = "打开车窗";
    private static final String CLOSE_WINDOW = "关闭车窗";
    private static final String AJUST_RADIO = "调节到FM101.7";

    private MqttClient mqttClient;

    public TaskResult executeOpen(TaskQuery taskQuery) {
        logger.info("HmiControlService execute...");
        //从请求中获取意图参数以及参数值
        Map<String, String> paramMap = taskQuery
                .getSlotEntities()
                .stream()
                .collect(
                        Collectors.toMap(slotItem -> slotItem.getIntentParameterName(),
                                slotItem -> slotItem.getStandardValue()));
        Map<String, String> originalParamMap = taskQuery
                .getSlotEntities()
                .stream()
                .collect(
                        Collectors.toMap(slotItem -> slotItem.getIntentParameterName(),
                                slotItem -> slotItem.getOriginalValue()));
        logger.info("paramMap ：" + paramMap.toString());

        TaskResult result = new TaskResult();
        boolean resultFlg = true;

        try {
            if (mqttClient == null) {
                mqttClient = getMqttClient();
            }
        } catch (Exception e) {
            resultFlg = false;
        }

        analyzeAndExecuteQuery(paramMap,originalParamMap,result,resultFlg);

        return result;
    }

    private void analyzeAndExecuteQuery(Map<String, String> paramMap, Map<String, String> originalParamMap,
                                        TaskResult result, boolean resultFlg) {

        if (paramMap.get("action").equals("打开")) {
            if (paramMap.get("items").equals("空调")) {
                MqttMessage message = new MqttMessage(OPEN_AIR.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("车窗")) {
                MqttMessage message = new MqttMessage(OPEN_WINDOW.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            }
        } else if (paramMap.get("action").equals("关闭")) {
            if (paramMap.get("items").equals("空调")) {
                MqttMessage message = new MqttMessage(CLOSE_AIR.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("车窗")) {
                MqttMessage message = new MqttMessage(CLOSE_WINDOW.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            }  else if (paramMap.get("action").equals("调节")) {
                if (paramMap.get("items").equals("空调")) {
                    MqttMessage message = new MqttMessage(ADJUST_AIR.getBytes());
                    message.setQos(0);
                    try {
                        mqttClient.publish(TOPIC_DEVICE, message);
                    } catch (Exception e) {
                        resultFlg = false;
                    }
                }else   if (paramMap.get("items").equals("收音机")) {
                    MqttMessage message = new MqttMessage(AJUST_RADIO.getBytes());
                    message.setQos(0);
                    try {
                        mqttClient.publish(TOPIC_DEVICE, message);
                    } catch (Exception e) {
                        resultFlg = false;
                    }
                }
            } else if (paramMap.get("action").equals("拨打")) {
                if (paramMap.get("items").equals("电话")) {
                    MqttMessage message = new MqttMessage(CALL_PHONE.getBytes());
                    message.setQos(0);
                    try {
                        mqttClient.publish(TOPIC_DEVICE, message);
                    } catch (Exception e) {
                        resultFlg = false;
                    }
                }
            }
        }

        if (resultFlg) {
            result.setReply(originalParamMap.get("action") + originalParamMap.get("items") + "成功");
        } else result.setReply("执行命令失败了");

        result.setExecuteCode(ExecuteCode.SUCCESS);
        result.setResultType(ResultType.RESULT);
        //result.setResultType(ResultType.ASK_INF);


    }

    private  MqttClient getMqttClient() throws Exception {
        String broker = "tcp://121.43.180.66:1883";
        String clientId = "Tmall-spirt-device-hmi";
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
