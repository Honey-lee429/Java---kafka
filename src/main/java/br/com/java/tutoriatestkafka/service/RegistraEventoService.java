package br.com.java.tutoriatestkafka.service;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistraEventoService {

    private final KafkaTemplate<Object, Object> template;

    public  <T> void adicionarEvento(String topico, T dados) {
        template.send(topico, dados);
    }



}
