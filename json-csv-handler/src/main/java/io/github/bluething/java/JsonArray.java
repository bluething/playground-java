package io.github.bluething.java;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class JsonArray implements JsonParser {
    @Override
    public void parse(String input) {
        try {
            Gson gson = new Gson();
            com.google.gson.JsonArray jsonArray = gson.fromJson(input, com.google.gson.JsonArray.class);
            for (JsonElement jsonElement : jsonArray) {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                System.out.printf("ID: %d, Name: %s, Price: $%.2f",
                        jsonObject.get("id").getAsInt(),
                        jsonObject.get("name").getAsString(),
                        jsonObject.get("price").getAsDouble());
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Json parsing error: " + e.getMessage());
        }
    }
}
