package br.com.java.tutoriatestkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class TutoriaTestKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TutoriaTestKafkaApplication.class, args);
	}

}
