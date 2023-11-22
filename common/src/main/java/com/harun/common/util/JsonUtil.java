package com.harun.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.harun.common.model.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public final class JsonUtil {

    private JsonUtil(){}

    private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);

    static ObjectWriter ow = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false).writer().withDefaultPrettyPrinter();

    static ObjectMapper mapper = new ObjectMapper();

    static JSONParser jsonParser = new JSONParser();

    public static Object jsonToObjectWithoutClass(String src) {

        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject = (JSONObject) jsonParser.parse(src);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        return jsonObject;

    }

    public static String objectToJson(Object src) {
        String json = null;
        try {
            json = ow.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage(), e);
        }
        return json;

    }

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        T obj = null;
        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            obj = mapper.readValue(json, clazz);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return obj;

    }


    public static <T> T convertValue(Object object, Class<T> clazz) {
        T obj;
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        obj = mapper.convertValue(object, clazz);
        return obj;
    }

    public static <T> T convertValue(Object object, TypeReference<?> toValueTypeRef) {
        T obj;
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        obj = mapper.convertValue(object, (TypeReference<T>) toValueTypeRef);
        return obj;
    }

    @SuppressWarnings("rawtypes")
    public static Object jsonToParameterizedObject(String json, TypeReference tr) {
        Object obj = null;
        try {
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            obj = mapper.readValue(json, tr);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return obj;

    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T getContent(ResponseEntity<Response> responseEntity, Class<T> clazz) {
        T obj;
        Map<?,?> result = (Map<?,?>) Objects.requireNonNull(responseEntity.getBody()).getResult();
        Object content = result.get("content");
        obj = JsonUtil.convertValue(content, clazz);
        return obj;
    }

    public static <T> List<T> convertToTypedList(List<Object> inputList, String field, TypeReference<List<T>> typeReference) {
        List<T> resultList = new ArrayList<>();

        for (Object obj : inputList) {
            List<T> pojos = convertValueWithConfigure(((Map<?, ?>) obj).get(field), typeReference);
            if (pojos != null && !pojos.isEmpty()) {
                resultList.add(pojos.get(0));
            }
        }

        return resultList;
    }

    private static <T> T convertValueWithConfigure(Object fromValue, TypeReference<T> typeReference) {
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return mapper.convertValue(fromValue, typeReference);
    }
}

