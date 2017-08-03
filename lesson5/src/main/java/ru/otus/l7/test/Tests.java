package ru.otus.l7.test;

import ru.otus.l7.annotations.After;
import ru.otus.l7.annotations.Before;
import ru.otus.l7.annotations.Test;
import ru.otus.l7.testframework.Assert;

import java.util.ArrayList;
import java.util.List;

public class Tests {
    private List<Integer> list;

    @Before
    public void before() {
        list = new ArrayList<>();
    }

    @Test
    public void testConditionFailed() {
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void testAssertEqualsFailed() {
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testAssertTrue() {
        list.add(1);
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void testAssertEquals() {
        list.add(1);
        Assert.assertEquals(1, list.size());
    }

    @After
    public void after() {
        System.out.println("After test");
    }
}
