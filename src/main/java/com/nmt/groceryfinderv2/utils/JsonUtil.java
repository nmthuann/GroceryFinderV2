package com.nmt.groceryfinderv2.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nmt.groceryfinderv2.modules.products.documents.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LENOVO
 * @project GroceryFinder
 * @date 9/25/2024
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert JSON to object", e);
        }
    }

    public static List<Specification> parseKeyAndValuePair(String specsString) {
        List<Specification> specifications = new ArrayList<>();
        String[] pairs = specsString.split(";");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("^\"|\"$", "");  // Remove quotes around key
                String value = keyValue[1].trim().replaceAll("^\"|\"$", "");  // Remove quotes around value

                Specification spec = new Specification();
                spec.setK(key);
                spec.setV(value);
                specifications.add(spec);
            }
        }
        return specifications;
    }
}
