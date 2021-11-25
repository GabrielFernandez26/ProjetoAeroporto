package src.com.example.projetoaeroporto.DAO;

import src.com.example.projetoaeroporto.entity.Cliente;

import java.util.List;

public interface ClienteDAO {
    void salvar(Cliente c);
    List<Cliente> pesquisarPorId(Integer id);
    void remover(int id);
    void atualizar(int id, Cliente c);
}
