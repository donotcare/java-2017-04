package l4;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GcStatistics {
    public final String gcName;
    public final int numberOfAllCollects;
    public final int numberOfMajorCollects;
    public final int numberOfMinorCollects;
    public final double averageCollectDuration;
    public final long maxCollectDuration;
    public final long minCollectDuration;

    public GcStatistics(String gcName, int numberOfAllCollects, int numberOfMajorCollects, int numberOfMinorCollects,
                        double averageCollectDuration, long maxCollectDuration, long minCollectDuration) {
        this.gcName = gcName;
        this.numberOfAllCollects = numberOfAllCollects;
        this.numberOfMajorCollects = numberOfMajorCollects;
        this.numberOfMinorCollects = numberOfMinorCollects;
        this.averageCollectDuration = averageCollectDuration;
        this.maxCollectDuration = maxCollectDuration;
        this.minCollectDuration = minCollectDuration;
    }

    @Override
    public String toString() {
        return "GcStatistics{" +
                "gcName='" + gcName + '\'' +
                ", numberOfAllCollects=" + numberOfAllCollects +
                ", numberOfMajorCollects=" + numberOfMajorCollects +
                ", numberOfMinorCollects=" + numberOfMinorCollects +
                ", averageCollectDuration=" + averageCollectDuration +
                ", maxCollectDuration=" + maxCollectDuration +
                ", minCollectDuration=" + minCollectDuration +
                '}';
    }

    public static GcStatistics processGcData(String name, List<GcDataLine> data) {
        Map<GcType, List<GcDataLine>> dataByGcType = data.stream().collect(Collectors.groupingBy(i -> i.type));
        List<GcDataLine> majorGcData = dataByGcType.get(GcType.MAJOR);
        List<GcDataLine> minorGcData = dataByGcType.get(GcType.MINOR);

        int numberOfAllCollects = majorGcData.size() + minorGcData.size();
        double averageCollectDuration = data.stream().mapToLong(i -> i.duration).average().orElse(0);
        long minCollectDuration = data.stream().mapToLong(i -> i.duration).min().orElse(0);
        long maxCollectDuration = data.stream().mapToLong(i -> i.duration).max().orElse(0);

        return new GcStatistics(name, numberOfAllCollects, majorGcData.size(), minorGcData.size(), averageCollectDuration, maxCollectDuration, minCollectDuration);
    }

}
