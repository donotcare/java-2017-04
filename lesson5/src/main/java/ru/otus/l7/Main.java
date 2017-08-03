package ru.otus.l7;

import ru.otus.l7.testframework.Executor;

import java.util.List;

public class Main {
    private static List<Object> objects;

    public static void main(String[] args) {
        Executor.executeTestsByPackage("ru.otus.l5.test");
    }
}
