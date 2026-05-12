package br.sgc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sgc.model.*;
import br.sgc.repository.*;

import java.time.LocalDate;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository repository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Venda registrar(Venda venda) {
        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new BusinessException("A venda deve ter pelo menos um item");
        }

        Cliente cliente = clienteRepository.findById(venda.getCliente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
        venda.setCliente(cliente);

        Usuario usuario = usuarioRepository.findById(venda.getUsuario().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        venda.setUsuario(usuario);

        double total = 0;
        for (ItemVenda item : venda.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            item.setPrecoUnitario(produto.getPreco());
            item.setVenda(venda);

            total += produto.getPreco() * item.getQuantidade();
        }

        venda.setValorTotal(total);
        venda.setData(LocalDate.now());

        return repository.save(venda);
    }

    public List<Venda> listarTodas() {
        return repository.findAll();
    }

    public Venda buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Venda não encontrada"));
    }

    public List<Venda> listarPorCliente(int clienteId) {
        return repository.findByClienteId(clienteId);
    }
}