package ru.otus.lesson2;

import java.util.function.Supplier;

public interface MemoryMeasure {
    long measure(Supplier<Object> object);
}
