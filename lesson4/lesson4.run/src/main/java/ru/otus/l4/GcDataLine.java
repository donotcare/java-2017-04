package ru.otus.l4;

public class GcDataLine {
    public final GcType type;
    public final long duration;

    public GcDataLine(GcType type, long duration) {
        this.type = type;
        this.duration = duration;
    }
}
