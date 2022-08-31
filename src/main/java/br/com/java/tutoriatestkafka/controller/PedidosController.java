package br.com.java.tutoriatestkafka.controller;

import br.com.java.tutoriatestkafka.kafka.data.PedidoData;
import br.com.java.tutoriatestkafka.service.RegistraEventoService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PedidosController {
    @Autowired
    private final RegistraEventoService eventoService;

//    @GetMapping("/pedido/get")
//    public ResponseEntity <List<PedidoData>> getPedido(){
//        return ResponseEntity.ok();
//    }

    @PostMapping("/pedido/salva-pedido")
    public ResponseEntity<String> salvarPedido(@RequestBody PedidoData pedido){
        eventoService.adicionarEvento("salvarPedido", pedido);
        return ResponseEntity.ok("Sucesso");
    }
}
