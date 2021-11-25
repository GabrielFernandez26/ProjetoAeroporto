package com.example.projetoaeroporto.DAO;

import java.util.List;

import com.example.projetoaeroporto.entity.*;

public interface VooDAO {
    void salvar(Voo v);
    List<Voo> pesquisarPorId(Integer id);
    void remover(int id);
    void atualizar(int id, Voo v);
}
