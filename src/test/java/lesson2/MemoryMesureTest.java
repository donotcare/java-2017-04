package lesson2;

import org.ehcache.sizeof.SizeOf;
import org.junit.Before;
import org.junit.Test;
import ru.otus.lesson2.MemoryMeasure;
import ru.otus.lesson2.RuntimeMemoryMeasureTool;

import java.util.function.Supplier;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

public class MemoryMesureTest {
    private SizeOf sizeOf;

    @Before
    public void setUp() throws Exception {
        sizeOf = SizeOf.newInstance();
    }

    @Test
    public void testMemoryUsageOfObject() {
        Supplier<Object> supplier = Object::new;

        final Long expected = sizeOf.deepSizeOf(supplier.get());

        MemoryMeasure tool = new RuntimeMemoryMeasureTool();
        final Long actual = tool.measure(supplier);

        assertEquals(expected, actual);
    }

    @Test
    public void testMemoryUsageOfEmptyString() {
        Supplier<Object> supplier = () -> new String(new char[0]);

        final Long expected = sizeOf.deepSizeOf(supplier.get());

        MemoryMeasure tool = new RuntimeMemoryMeasureTool();
        final Long actual = tool.measure(supplier);

        assertEquals(expected, actual);
    }

    @Test
    public void testMemoryUsageOfEmptyArray() {
        Supplier<Object> supplier = () -> new String[0];

        final Long expected = sizeOf.deepSizeOf(supplier.get());

        MemoryMeasure tool = new RuntimeMemoryMeasureTool();
        final Long actual = tool.measure(supplier);

        assertEquals(expected, actual);
    }

    @Test
    public void testMemoryUsageOfArray() {
        Supplier<Object> supplier = () -> {
            String[] arr = new String[10];
            IntStream.range(0, 10).forEach(i -> arr[i] = String.valueOf(i));
            return arr;
        };

        final Long expected = sizeOf.deepSizeOf(supplier.get());

        MemoryMeasure tool = new RuntimeMemoryMeasureTool();
        final Long actual = tool.measure(supplier);

        assertEquals(expected, actual);
    }
}
