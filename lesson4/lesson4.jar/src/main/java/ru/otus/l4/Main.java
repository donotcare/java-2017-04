package ru.otus.l4;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static List<Object> objects;

    public static void main(String[] args) {
        installMonitorGc();
        int iteration = 1000000;
        objects = new ArrayList<>();
        IntStream.range(0, iteration).forEach(Main::iteration);
    }

    private static void iteration(int num) {
        try {
            int objectsPerIteration = 17000;
            LongStream.range(0, objectsPerIteration).forEach(i -> objects.add(new Object()));
            Thread.sleep(100);
            LongStream.range(0, objectsPerIteration / 2).forEach(i -> objects.remove(objects.size() - 1));
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void installMonitorGc() {
        List<GarbageCollectorMXBean> gcBeans = ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcBean : gcBeans) {
            NotificationEmitter emitter = (NotificationEmitter) gcBean;
            System.out.println("gc name-" + gcBean.getName());
            NotificationListener listener = (notification, handback) -> {
                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());
                    long duration = info.getGcInfo().getDuration();
                    String gcType = info.getGcAction();
                    if (gcType.contains("minor"))
                        gcType = "MINOR";
                    else if (gcType.contains("major"))
                        gcType = "MAJOR";
                    System.out.println(gcType + "-" + duration);
                }
            };
            emitter.addNotificationListener(listener, null, null);
        }
    }
}
