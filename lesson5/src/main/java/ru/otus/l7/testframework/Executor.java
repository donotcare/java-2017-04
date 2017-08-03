package ru.otus.l7.testframework;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Executor {
    public static void executeTests(Set<Class<?>> classes) {
        classes.stream().map(TestAnnotationsProccessor::new).forEach(TestAnnotationsProccessor::execute);
    }

    public static void executeTestsByPackage(String packageName) {
        List<ClassLoader> classLoadersList = new ArrayList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        executeTests(classes);
    }
}
