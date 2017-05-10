package ru.otus.l5;

import ru.otus.l5.testframework.Executor;

import java.util.List;

public class Main {
    private static List<Object> objects;

    public static void main(String[] args) {
        Executor.executeTestsByPackage("l5.test");
    }
}
