package guru.sfg.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
public class JmsConfig {

    public static final String NEW_INVENTORY_QUEUE = "new-inventory";
    public static final String ALLOCATE_ORDER_QUEUE = "allocate-order";
    public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-response";
    public static final String DEALLOCATE_ORDER_QUEUE = "deallocate-order";

    // Serialize message content to json using TextMessage
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper){
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
