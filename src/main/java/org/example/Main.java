package org.example;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        ExecutorService workers = Executors.newFixedThreadPool(5);
        ScheduledExecutorService scheduledWorkers = Executors.newScheduledThreadPool(1);
        PartidaOnline p = new PartidaOnline();

        for (int i = 1;i<=18;i++){
            CompletableFuture.supplyAsync(() ->{
                return p.criarSala();
            },workers).thenApply(valorPassado ->{
                Integer ponto = new Random().nextInt(10,100);
                if (ponto<25){
                    throw new RuntimeException("Pontuação inválida!");
                }
                return ponto;
            }).exceptionally(ex ->{
                System.out.println("ERRO!"+ex.getCause().getMessage());
                return 25;
            }).thenAccept(valorPassado ->{
                p.getPontuacaoPorRegiao().merge("BR",valorPassado,Integer::sum);
            });
        }

      scheduledWorkers.scheduleWithFixedDelay(() ->{
          for (String key:p.getPontuacaoPorRegiao().keySet()){
              System.out.println("A quantidade de pontos do continente "+key+" foi de "+p.getPontuacaoPorRegiao().get(key));
          }
      },0,1,TimeUnit.SECONDS);

        Utilitarios.fecharPools(workers,scheduledWorkers);
        Utilitarios.exibirValoresFinais(p);
    }
}