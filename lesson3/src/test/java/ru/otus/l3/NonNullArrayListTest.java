package ru.otus.l3;

import ru.otus.l3.NonNullArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class NonNullArrayListTest {
    private List<String> list;

    @Before
    public void init() {
        list = new NonNullArrayList<>(0);
    }

    @Test
    public void testAdd() {
        list.add("First");
        list.add("Third");

        assertEquals("First", list.get(0));
        assertEquals("Third", list.get(1));
        assertEquals(2, list.size());

        list.add(1, "Second");

        assertEquals("Second", list.get(1));
        assertEquals("Third", list.get(2));
    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, list.isEmpty());

        list.add("One");

        assertEquals(false, list.isEmpty());
    }

    @Test
    public void testContains() {
        list.add("One");

        assertEquals(false, list.contains("Two"));

        list.add("Two");

        assertEquals(true, list.contains("Two"));
    }

    @Test
    public void testRemove() {
        list.add("One");
        list.remove("One");

        assertEquals(true, list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testSet() {
        list.add("One");
        list.set(0, "Another");

        assertEquals("Another", list.get(0));
        assertEquals(1, list.size());
    }

    @Test
    public void testLastIndexOf() {
        list.add("One");

        assertEquals(0, list.lastIndexOf("One"));

        list.add("One");

        assertEquals(1, list.lastIndexOf("One"));

        assertFalse(false);
    }

    @Test
    public void testAddAll() {
        list.add("One");
        List<String> anotherList = new ArrayList<>();
        anotherList.add("Two");
        anotherList.add("Three");

        list.addAll(anotherList);

        Object[] expected = {"One", "Two", "Three"};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testAddAllWithIndex() {
        list.add("One");
        List<String> anotherList = new ArrayList<>();
        anotherList.add("Two");
        anotherList.add("Three");

        list.addAll(0, anotherList);

        Object[] expected = {"Two", "Three", "One"};
        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testSubList() {
        list.add("One");
        list.add("Two");
        list.add("Three");

        Object[] arr = list.subList(1, 2).toArray();
        Object[] expected = {"Two", "Three"};

        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRemoveAll() {
        list.add("One");
        list.add("Two");
        list.add("Three");

        List<String> anotherList = new ArrayList<>();
        anotherList.add("Two");
        anotherList.add("Three");

        list.removeAll(anotherList);

        assertEquals("One", list.get(0));
    }

    @Test
    public void testIterator() {
        Collections.addAll(list, "One", "Two");
        ListIterator<String> iterator = list.listIterator();
        assertEquals(false, iterator.hasPrevious());
        assertEquals(true, iterator.hasNext());
        assertEquals("One", iterator.next());

        iterator.remove();

        assertEquals(false, iterator.hasPrevious());
        assertEquals(true, iterator.hasNext());
        assertEquals("Two", iterator.next());

        iterator.add("Three");

        assertEquals("Three", iterator.next());
        assertEquals("Two", iterator.previous());

        iterator.next();

        assertEquals(false, iterator.hasNext());
    }

    @Test
    public void testCollectionsAddAll() {
        Collections.addAll(list, "One", "Two", "Three");

        Object[] expected = {"One", "Two", "Three"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testCollectionsSort() {
        Collections.addAll(list, "Bill", "Daniel", "Adam");
        Collections.sort(list, Comparator.comparing(String::toLowerCase));
        Object[] expected = {"Adam", "Bill", "Daniel"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test
    public void testCollectionsCopy() {
        Collections.addAll(list, "Bill", "Daniel", "Adam");
        List<String> anotherList = new ArrayList<>();
        Collections.addAll(anotherList, "One", "Two", "Three");
        Collections.copy(list, anotherList);
        Object[] expected = {"One", "Two", "Three"};

        assertArrayEquals(expected, list.toArray());
    }

    @Test(expected = NullPointerException.class)
    public void testAddNull() {
        list.add("One");
        list.add(null);
    }

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        List<String> anotherList = new ArrayList<>();
        anotherList.add("Two");
        anotherList.add(null);
        list.addAll(anotherList);
    }
}
