package harsh.rane.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
	
	@KafkaListener(topics = "topicresult", group = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }
}
