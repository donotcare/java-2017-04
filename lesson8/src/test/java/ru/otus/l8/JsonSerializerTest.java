package ru.otus.l8;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import ru.otus.l8.model.ComplexObject;
import ru.otus.l8.model.ObjectWithCollections;
import ru.otus.l8.model.SimpleObject;

import static org.junit.Assert.assertEquals;

public class JsonSerializerTest {
    private JsonSerializer serializer;
    private Gson gson;

    @Before
    public void setUp() {
        serializer = new JsonSerializer();
        gson = new Gson();

    }

    @Test
    public void serializePrimitive() {
        int primitive = 1;

        assertEquals(gson.toJson(primitive), serializer.toJson(primitive));
    }

    @Test
    public void serializePrimitiveArray() {
        int [] arr = {1,2,3};
        assertEquals(gson.toJson(arr), serializer.toJson(arr));
    }

    @Test
    public void serializeArray() {
        String[] arr = {"First", "Second"};

        assertEquals(gson.toJson(arr), serializer.toJson(arr));
    }

    @Test
    public void serializeSimpleObject() {
        SimpleObject simpleObject = new SimpleObject();

        assertEquals(gson.toJson(simpleObject), serializer.toJson(simpleObject));
    }

    @Test
    public void serializeObjectWithCollections() {
        ObjectWithCollections objectWithCollections = new ObjectWithCollections();

        assertEquals(gson.toJson(objectWithCollections), serializer.toJson(objectWithCollections));
    }

    @Test
    public void serializeComplexObject() {
        ComplexObject complexObject = new ComplexObject();

        assertEquals(gson.toJson(complexObject), serializer.toJson(complexObject));
    }
}
