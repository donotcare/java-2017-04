package l5;

import l5.testframework.Executor;

import java.util.List;

public class Main {
    private static List<Object> objects;

    public static void main(String[] args) {
        Executor.executeTestsByPackcage("l5.test");
    }
}
