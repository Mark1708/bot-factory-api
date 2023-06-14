package com.mark1708.notification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(
		scanBasePackages = {
				"com.mark1708.kafka",
				"com.mark1708.notification",
		}
)
@RequiredArgsConstructor
public class NotificationServiceApplication {

//	@Value(value = "${spring.kafka.topic}")
//	private String topic;
//
//	private final KafkaMessageProducer producer;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

//	@PostConstruct
//	public void processing() throws IOException {
//
//		Path path = Paths.get("/Users/mark/Documents/bot-factory/bot-factory-api/notification-service/src/main/resources/banner.txt");
//		byte[] imageBytes = Files.readAllBytes(path);
//
//		// Кодирование изображения в Base64
//		String base64File = Base64.getEncoder().encodeToString(imageBytes);
//
//		NewsletterMessage newsletterMessage = NewsletterMessage.builder()
//				.apiKey("apiKey")
//				.chatIds(List.of("1245"))
//				.text("Some text")
//				.documents(List.of(
//						new Document("banner.txt", base64File)
//				))
//				.build();
//
//		producer.publish(newsletterMessage, topic, "1");
//	}


}
