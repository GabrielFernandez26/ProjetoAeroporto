package com.example.projetoaeroporto.entity;

import java.time.LocalDateTime;


public class Voo {
    private int id;
    private String origem;
    private String destino;
    private LocalDateTime decolagem;
    private LocalDateTime pouso;
    private int assentosDisponiveis;
    private Double preco;

    public int getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(int assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public LocalDateTime getDecolagem() {
        return decolagem;
    }

    public void setDecolagem(LocalDateTime decolagem) {
        this.decolagem = decolagem;
    }

    public LocalDateTime getPouso() {
        return pouso;
    }

    public void setPouso(LocalDateTime pouso) {
        this.pouso = pouso;
    }
}
