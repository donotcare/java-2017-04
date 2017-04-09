package ru.otus.lesson2;

import java.util.function.Supplier;

public class RuntimeMemoryMeasureTool implements MemoryMeasure {
    private static int SAMPLE_SIZE = 1000;
    private long memoryBefore = 0;
    private long memoryAfter = 0;
    private long totalMemory = 0;
    private long freeMemory = 0;
    private int i = 0;

    public long measure(Supplier<Object> object) {
        Object[] objects = new Object[SAMPLE_SIZE];
        memoryBefore = getMemoryUsage();
        for (i = 0; i < objects.length; i++) {
            objects[i] = object.get();
        }
        memoryAfter = getMemoryUsage();
        return (memoryAfter - memoryBefore) / SAMPLE_SIZE;
    }

    private long getMemoryUsage() {
        putOutTheGarbage();
        totalMemory = Runtime.getRuntime().totalMemory();
        putOutTheGarbage();
        freeMemory = Runtime.getRuntime().freeMemory();
        return totalMemory - freeMemory;
    }

    private void putOutTheGarbage() {
        collectGarbage();
        collectGarbage();
    }

    private void collectGarbage() {
        try {
            System.gc();
            Thread.sleep(100);
            System.runFinalization();
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
