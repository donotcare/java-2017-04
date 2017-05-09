package l5.test;

import l5.annotations.After;
import l5.annotations.Before;
import l5.annotations.Test;
import l5.testframework.Assert;

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
