package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());

        for (var i = 0; i < 100; i++) {

            var key = UUID.randomUUID().toString();
            // a chave  muito importante para o hash, define a particição que cairá a msg, se for uma unica chave, sempre cairá na mesma partição
            //Ela é peça fundamental para paralelizar o processamento de mensagens em um tópico dentro do mesmo consumer group.
            //A chave é usada para distribuir a mensagem entre as partições existentes e consequentemente entre as instâncias de um serviço dentro de um consumer group.
            var value = key + ",67523,1234";
            var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", key, value);
            Callback callback = (data, ex) -> {
                if (ex != null) {
                    ex.printStackTrace();
                    return;
                }
                System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
            };
            var email = "Thank you for your order! We are processing your order!";
            var emailRecord = new ProducerRecord<>("ECOMMERCE_SEND_EMAIL", key, email);
            producer.send(record, callback).get();
            producer.send(emailRecord, callback).get();
        }
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
