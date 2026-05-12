package br.sgc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.sgc.model.Venda;
import br.sgc.service.VendaService;

import java.util.List;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @PostMapping
    public ResponseEntity<Venda> registrar(@RequestBody Venda venda) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrar(venda));
    }

    @GetMapping
    public List<Venda> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    public Venda buscar(@PathVariable int id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Venda> listarPorCliente(@PathVariable int clienteId) {
        return service.listarPorCliente(clienteId);
    }
}