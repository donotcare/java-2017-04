import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class EntityLoader {

    public Map<Class, EntityInfo> scanPackage(String packageName) {
        List<ClassLoader> classLoadersList = new ArrayList<>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[classLoadersList.size()])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

        return loadEntities(reflections.getSubTypesOf(Object.class));
    }

    private Map<Class, EntityInfo> loadEntities(Set<Class<?>> classes) {
        return classes.stream().filter(c -> c.isAnnotationPresent(Entity.class)).collect(Collectors.toMap(Function.identity(), this::createQueryInfo));
    }

    private EntityInfo createQueryInfo(Class<?> clazz) {
        String tableName = clazz.getSimpleName();
        if (clazz.isAnnotationPresent(Table.class))
            tableName = clazz.getAnnotation(Table.class).name();
        FieldColumn[] columns = loadEntityColumns(clazz);
        return new EntityInfo(tableName, columns);
    }

    private FieldColumn[] loadEntityColumns(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        FieldColumn[] columns = new FieldColumn[fields.length];
        int i = 0;
        for (Field field : fields) {
            String columnName = field.getName();
            if (field.isAnnotationPresent(Column.class))
                columnName = field.getAnnotation(Column.class).name();
            columns[i++] = new FieldColumn(field.getName(), columnName);
        }
        return columns;
    }

}
