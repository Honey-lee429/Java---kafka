package br.com.java.tutoriatestkafka.kafka.data;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoData {

    private String codigo;
    private String nomeProduto;
    private BigDecimal valor;

}
