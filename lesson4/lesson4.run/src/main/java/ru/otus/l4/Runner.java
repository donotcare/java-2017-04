package ru.otus.l4;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Runner {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture[] fututes = new CompletableFuture[6];
        System.out.println(LocalDateTime.now());
        fututes[0] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.COPY_MARKSWEEPCOMPACT).collect());
        fututes[1] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.PARNEW_MARKSWEEPCOMPACT).collect());
        fututes[2] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.G1).collect());
        fututes[3] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.SCAVENGER_MARKSWEEP).collect());
        fututes[4] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.PARNEW_CONCURRENTMARKSWEEP).collect());
        fututes[5] = CompletableFuture.supplyAsync(() -> new GcStatisticsCollector(GarbageCollector.COPY_CONCURRENTMARKSWEEP).collect());

        CompletableFuture.allOf(fututes).get();

        for(CompletableFuture<GcStatistics> future : fututes) {
            System.out.println(future.get());
        }
    }
}
