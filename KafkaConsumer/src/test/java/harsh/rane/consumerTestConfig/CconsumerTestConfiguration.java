package harsh.rane.consumerTestConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@TestConfiguration
public class CconsumerTestConfiguration {
	
	@Autowired
	EmbeddedKafkaBroker embeddedKafkaBroker;
	 
	@Bean
	public ProducerFactory<String, String> producerFactory() {
	    return new DefaultKafkaProducerFactory<>(KafkaTestUtils.producerProps(embeddedKafkaBroker));
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
	    KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory());
	    kafkaTemplate.setDefaultTopic("topicresult");
	    return kafkaTemplate;
	}
}
