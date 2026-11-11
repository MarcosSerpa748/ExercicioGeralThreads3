package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Utilitarios {

    public static void fecharPools(ExecutorService workers, ScheduledExecutorService scheduledWorkers){
        workers.shutdown();
        try {
            if (!workers.awaitTermination(5, TimeUnit.SECONDS)){
                workers.shutdownNow();
                scheduledWorkers.shutdownNow();
            }
            scheduledWorkers.shutdownNow();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            workers.shutdownNow();
            scheduledWorkers.shutdownNow();
        }
    }
    public static void exibirValoresFinais(PartidaOnline p){

        for (String key:p.getPontuacaoPorRegiao().keySet()){
            System.out.println("A pontuação final da região "+key+" foi de "+p.getPontuacaoPorRegiao().get(key));
        }
    }
}
