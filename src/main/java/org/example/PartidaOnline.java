package org.example;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class PartidaOnline {
    private Integer quantidadePartidasIniciadas = 100;
    private ConcurrentHashMap<String,Integer> pontuacaoPorRegiao = new ConcurrentHashMap<>();

    public PartidaOnline(){
        pontuacaoPorRegiao.put("BR",0);
        pontuacaoPorRegiao.put("EU",0);
        pontuacaoPorRegiao.put("USA",0);
    }

    public synchronized Integer criarSala(){
        if (Thread.currentThread().isInterrupted()){
            throw new RuntimeException("A Thread que estava fazendo essa atividade foi interrompida!");
        }
        quantidadePartidasIniciadas++;
        return new Random().nextInt(50);
    }

    public ConcurrentHashMap<String, Integer> getPontuacaoPorRegiao() {
        return pontuacaoPorRegiao;
    }

    public Integer getQuantidadePartidasIniciadas() {
        return quantidadePartidasIniciadas;
    }
}
