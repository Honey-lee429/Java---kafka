package br.com.alura.ecommerce;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var producer = new KafkaProducer<String, String>(properties());
        var value = "132123,67523,7894589745";
        var record = new ProducerRecord<>("ECOMMERCE_NEW_ORDER", value, value);
        producer.send(record, (data, ex) -> { //callback(data, ex)=(metadados de sucesso, mensagem de erro)
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("sucesso enviando " + data.topic() + ":::partition " + data.partition() + "/ offset " + data.offset() + "/ timestamp " + data.timestamp());
        }).get();

       /* O método send (linha 17) devolve um Future, um Future é alguma coisa que vai executar daqui a pouco,
        então quer dizer, o send não é blocante, ele não segura, ele não é síncrono, ele é assíncrono.
        Então, se eu quiser esperar ele terminar, eu vou chamar um .get(), que daí o get você espera
         o Future terminar, aqui a gente está esperando e aí, pode dar uma exception,
        porque quanto você está esperando, alguém pode interromper ou enquanto você está esperando,
        ode dar um erro na execução.*/
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }
}
