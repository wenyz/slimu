package hmi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author wenyz
 */
@Configuration
public class EveryConfig {

    @Bean
    public MqttBean mqttBean(){
        return new MqttBean();
    }
}
