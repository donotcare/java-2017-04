package ru.otus.l8.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectWithCollections {
    public final String[] arr = {"1", "2", "3"};
    public final String name = "Object with collections";
    public final List<Integer> list = Arrays.asList(10, 20, 30);
    public final Map<Integer, String> testMap = new HashMap<>();

    {
        testMap.put(1, "First");
        testMap.put(2, "Second");
    }
}
