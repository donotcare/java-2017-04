package l4;

public enum GarbageCollector {
    COPY_MARKSWEEPCOMPACT("-XX:+UseSerialGC"),
    PARNEW_MARKSWEEPCOMPACT("-XX:+UseParNewGC"),
    G1("-XX:+UseG1GC"),
    SCAVENGER_MARKSWEEP("-XX:+UseParallelGC -XX:+UseParallelOldGC"),
    PARNEW_CONCURRENTMARKSWEEP("-XX:+UseConcMarkSweepGC -XX:+UseParNewGC"),
    COPY_CONCURRENTMARKSWEEP("-XX:+UseConcMarkSweepGC -XX:-UseParNewGC");

    public String startupCommands;

    GarbageCollector(String startupCommands) {
        this.startupCommands = startupCommands;
    }
}
