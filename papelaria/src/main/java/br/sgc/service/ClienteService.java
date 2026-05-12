package br.sgc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sgc.model.Cliente;
import br.sgc.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        if (repository.findByCpf(cliente.getCpf()) != null) {
            throw new BusinessException("CPF já cadastrado");
        }
        return repository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return repository.findAll();
    }

    public Cliente buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
    }

    public Cliente atualizar(int id, Cliente dadosNovos) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dadosNovos.getNome());
        cliente.setEmail(dadosNovos.getEmail());
        cliente.setTelefone(dadosNovos.getTelefone());
        cliente.setEndereco(dadosNovos.getEndereco());
        return repository.save(cliente);
    }

    public void remover(int id) {
        Cliente cliente = buscarPorId(id);
        if (!cliente.getVendas().isEmpty()) {
            throw new BusinessException("Cliente possui vendas registradas");
        }
        repository.delete(cliente);
    }
}