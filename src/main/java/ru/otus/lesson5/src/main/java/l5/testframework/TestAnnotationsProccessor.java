package l5.testframework;

import l5.annotations.After;
import l5.annotations.Before;
import l5.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestAnnotationsProccessor {

    public Method beforeMethod;
    public Method afterMethod;
    private Class<?> clazz;

    public TestAnnotationsProccessor(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void execute() {
        Set<Method> testMethods = new HashSet<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Before.class)) {
                beforeMethod = method;
            } else if (method.isAnnotationPresent(After.class)) {
                afterMethod = method;
            } else if (method.isAnnotationPresent(Test.class)) {
                testMethods.add(method);
            }
        }
        testMethods.forEach(this::executeTest);
    }

    private void executeTest(Method method) {
        Object testsClass = createClass();
        executeBeforeMethod(testsClass);
        try {
            method.invoke(testsClass);
            System.out.println(TextColors.ANSI_GREEN + method.getName() + ": " + "OK!" + TextColors.ANSI_RESET);
        } catch (Exception e) {
            System.out.println(TextColors.ANSI_RED + method.getName() + ": " + e.getCause().getMessage() + "\n" +  printStackTraceArray(e.getCause().getStackTrace()) + TextColors.ANSI_RESET);
        } finally {
            executeAfterMethod(testsClass);
        }
    }

    private Object createClass() {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void executeBeforeMethod(Object testsClass) {
        executeMethod(beforeMethod, testsClass);
    }

    private void executeAfterMethod(Object testsClass) {
        executeMethod(afterMethod, testsClass);
    }

    private void executeMethod(Method method, Object testsClass) {
        try {
            if (method != null)
                method.invoke(testsClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static String printStackTraceArray(StackTraceElement[] stackTrace){
        return Stream.of(stackTrace).map(String::valueOf).collect(Collectors.joining("\n"));
    }

}
