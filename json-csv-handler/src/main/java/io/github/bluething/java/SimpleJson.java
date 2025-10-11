package io.github.bluething.java;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class SimpleJson implements JsonParser {
    @Override
    public void parse(String input) {
        try {
            Gson gson =  new Gson();
            JsonObject jsonObject = gson.fromJson(input, JsonObject.class);
            System.out.println("Name: " + jsonObject.get("name").getAsString());
            System.out.println("Age: " + jsonObject.get("age").getAsInt());
            System.out.println("Active: " + jsonObject.get("active").getAsBoolean());
        } catch (JsonSyntaxException e) {
            System.err.println("JSON parsing error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
