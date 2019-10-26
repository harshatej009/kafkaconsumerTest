package harsh.rane.consumerTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import harsh.rane.consumerTestConfig.CconsumerTestConfiguration;
import harsh.rane.service.KafkaConsumerService;

@EnableKafka
@SpringBootTest(classes = {KafkaConsumerService.class}) // Specify @KafkaListener class if its not the same class,or not loaded with test config
@EmbeddedKafka(partitions = 1, controlledShutdown = false,brokerProperties = {"listeners=PLAINTEXT://127.0.0.1:9092", "port=9092"})
public class ConsumerTest {
	 private static final String TEST_TOPIC = "topicresult";

	 @Autowired
	 EmbeddedKafkaBroker embeddedKafkaBroker;
	 
	 @Autowired
	 CconsumerTestConfiguration config ;
	 
	 @Autowired
	 KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

//	 @Before
//	 public void setUp() throws Exception {
//	   for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry.getListenerContainers()) {
//	     ContainerTestUtils.waitForAssignment(messageListenerContainer, 
//	    		 embeddedKafkaBroker.getPartitionsPerTopic());
//	   }
	// }
	 @Test
	 public void testReceive() throws Exception {
		 config.kafkaTemplate().send(TEST_TOPIC, "hey");//.send("topicresult", "hello");
		 System.out.println("message sent");
	 }
	 
}

