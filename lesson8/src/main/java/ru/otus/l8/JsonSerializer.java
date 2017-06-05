package ru.otus.l8;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings("all")
public class JsonSerializer {

    public String toJson(Object object) {
        return parse(object).toString();
    }

    private Object parse(Object object) {
        if (object == null) {
            return "";
        }
        Class type = object.getClass();
        if (object instanceof Number || object instanceof String) {
            return object;
        } else if (type.isArray()) {
            return parseArray((Object) object);
        } else if (object instanceof Collection) {
            return parseArray(((Collection) object).toArray());
        } else if (object instanceof Map) {
            return parseMap(object);
        } else {
            return parseObject(object);
        }
    }

    private Object parseMap(Object object) {
        JSONObject json = new JSONObject();
        for (Map.Entry entry : ((Map<Object, Object>) object).entrySet()) {
            json.put(entry.getKey(), parse(entry.getValue()));
        }
        return json;
    }

    private Object parseObject(Object object) {
        JSONObject json = new JSONObject();
        try {
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value = field.get(object);
                json.put(field.getName(), parse(value));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return json;
    }

    private Object parseArray(Object array) {
        JSONArray jArray = new JSONArray();
        int arrlength = Array.getLength(array);
        for (int i = 0; i < arrlength; ++i) {
            jArray.add(parse(Array.get(array, i)));
        }
        return jArray;
    }
}
