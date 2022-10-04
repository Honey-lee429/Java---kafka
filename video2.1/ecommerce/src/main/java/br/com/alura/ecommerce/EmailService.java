package br.com.alura.ecommerce;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class EmailService {

    public static void main(String[] args) {
        var consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("ECOMMERCE_SEND_EMAIL"));
        /*O consumidor vai falar o seguinte: “Eu gostaria de consumir mensagens de algum lugar”,
        consumer.subscribe, em algum tópico. Eu preciso falar qual
        é o tópico que eu quero subscrever e aí, você passa uma lista, uma colections, uma lista, podia
        ser uma lista qualquer. Eu vou passar uma singletonList, que é uma forma fácil de criar uma lista
        e na minha lista é o meu tópico é o e-commerce new order, esse é o tópico que eu estou escutando */
        while(true) {
            var records = consumer.poll(Duration.ofMillis(100));
            // ver se tem mensagem, um Duration.ofMillis, milissegundos, cem milissegundos
            if (!records.isEmpty()) {
                System.out.println("Encontrei " + records.count() + " registros");
                for (var record : records) {
                    System.out.println("------------------------------------------");
                    System.out.println("Send email");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.partition());
                    System.out.println(record.offset());
                    try {
                        //simulando envio de emails para aparecer no console
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // ignoring
                        e.printStackTrace();
                    }
                    System.out.println("Email sent");
                }
            }
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, EmailService.class.getSimpleName());
        return properties;
    }
}
