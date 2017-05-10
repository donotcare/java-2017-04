package ru.otus.l4;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GcStatisticsCollector {

    private GarbageCollector gc;

    public GcStatisticsCollector(GarbageCollector gc) {
        this.gc = gc;
    }

    public GcStatistics collect() {
        try {
            System.out.println("Starting... " + gc.name());
            File file = new File(getClass().getClassLoader().getResource("lesson4.jar").getFile());
            System.out.println(file.getAbsoluteFile());
            Process pr = Runtime.getRuntime().exec("java -Xmx200m " + gc.startupCommands + " -jar " + file);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            List<GcDataLine> data = new ArrayList<>();
            String s;
            String gcName = "";
            while ((s = stdInput.readLine()) != null) {
                if(s.contains("gc name")) {
                    gcName += gcName.length() > 0 ? "-" : "";
                    gcName += s.split("-")[1];
                    continue;
                }
                String[] dataLine = s.split("-");
                data.add(new GcDataLine(GcType.valueOf(dataLine[0]), Long.valueOf(dataLine[1])));
            }
            return GcStatistics.processGcData(gcName, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
