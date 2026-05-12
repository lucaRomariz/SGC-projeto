package br.sgc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sgc.model.Produto;
import br.sgc.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new BusinessException("Preço não pode ser negativo");
        }
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Produto buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));
    }

    public Produto atualizar(int id, Produto dadosNovos) {
        Produto produto = buscarPorId(id);
        produto.setNome(dadosNovos.getNome());
        produto.setDescricao(dadosNovos.getDescricao());
        produto.setPreco(dadosNovos.getPreco());
        produto.setEstoque(dadosNovos.getEstoque());
        return repository.save(produto);
    }

    public void remover(int id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}