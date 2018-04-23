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
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

@Component("vehicleControlService")
public class VehicleControlService {

    private static final Logger logger = LoggerFactory.getLogger(VehicleControlService.class);
    private static final String TOPIC_DEVICE = "v1/vehicle/control/app2ivi/launchdesign00001";
    private static final String ENGINE_START = "008000001";
    private static final String ENGINE_STOP = "008000000";
    private static final String LIGHT_OPEN = "008002111";
    private static final String LIGHT_CLOSE = "008002110";
    private static final String DOOR_OPEN = "008001111";
    private static final String DOOR_CLOSE = "008001110";

    private MqttClient mqttClient;

    public TaskResult executeOpen(TaskQuery taskQuery) {

        logger.info("VehicleControlService execute...");
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
            if (paramMap.get("items").equals("车")) {
                MqttMessage message = new MqttMessage(ENGINE_START.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("车门")) {
                MqttMessage message = new MqttMessage(DOOR_OPEN.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("灯")) {
                MqttMessage message = new MqttMessage(LIGHT_OPEN.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            }
        } else if (paramMap.get("action").equals("关闭")) {
            if (paramMap.get("items").equals("车")) {
                MqttMessage message = new MqttMessage(ENGINE_STOP.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("车门")) {
                MqttMessage message = new MqttMessage(DOOR_CLOSE.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
                }
            } else if (paramMap.get("items").equals("灯")) {
                MqttMessage message = new MqttMessage(LIGHT_CLOSE.getBytes());
                message.setQos(0);
                try {
                    mqttClient.publish(TOPIC_DEVICE, message);
                } catch (Exception e) {
                    resultFlg = false;
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
//
//    private TaskResult baseQuery(TaskQuery taskQuery, Map<String, String> paramMap) {
//        TaskResult result = new TaskResult();
//        try {
//            //请求服务并填充回复语句
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("areaName", paramMap.get("city")));
//            params.add(new BasicNameValuePair("date", DateUtil.getStartDate(paramMap.get("time"))));
//            String executeBody = httpGet(params);
//            String weather = getWeather(executeBody);
//            Map<String, String> properties = new HashMap<String, String>();
//
//            properties.put("city", paramMap.get("city"));
//            properties.put("time", paramMap.get("time"));
//            properties.put("weather", weather);
//            properties.put("temp_low", getTempLow(executeBody));
//            properties.put("temp_high", getTempHigh(executeBody));
//            properties.put("wind_direct", getWindDirect(executeBody));
//            properties.put("power", getPower(executeBody));
//            if (weather == null) {
//                result.setReply("对不起，我现在只支持查询最近4天的天气");
//            } else {
//                result.setReply(TemplateFillUtil
//                        .fillTemplate(
//                                "@{city} @{time}天气 @{weather}，温度@{temp_low}到@{temp_high}度，@{wind_direct}@{power}",
//                                properties));
//            }
//
//            result.setExecuteCode(ExecuteCode.SUCCESS);
//            result.setResultType(ResultType.RESULT);
//        } catch (Exception e) {
//            logger.info("query exception", e);
//            result.setExecuteCode(ExecuteCode.EXECUTE_ERROR);
//        }
//
//        return result;
//    }

    private MqttClient getMqttClient() throws Exception {
        String broker = "tcp://121.43.180.66:1883";
        String clientId = "Tmall-spirt-device";
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
