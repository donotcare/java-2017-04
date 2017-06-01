package ru.otus.l8.model;

import com.google.gson.Gson;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.l8.JsonSerializer;

public class SimpleObject {
    private Integer testInteger = 100;
    private String name = "Simple object";
    private int testPrimitive = 1;

    public static class JsonSerializerTest {
        private JsonSerializer serializer;
        private Gson gson;

        @Before
        public void setUp() {
            serializer = new JsonSerializer();
            gson = new Gson();

        }

        @Test
        public void serializeSimpleObject() {
            Assert.assertEquals(gson.toJson(new SimpleObject()), serializer.toJson(new SimpleObject()));
        }

        @Test
        public void serializeObjectWithCollections() {
            Assert.assertEquals(gson.toJson(new ObjectWithCollections()), serializer.toJson(new ObjectWithCollections()));
        }

    }
}
