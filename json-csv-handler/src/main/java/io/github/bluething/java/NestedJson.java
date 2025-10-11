package io.github.bluething.java;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class NestedJson implements JsonParser {
    @Override
    public void parse(String input) {
        try {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(input, JsonObject.class);
            JsonObject user = root.getAsJsonObject("user");
            JsonObject address = user.getAsJsonObject("address");
            JsonArray orders = user.getAsJsonArray("orders");

            System.out.println("User: " + user.get("name").getAsString());
            System.out.println("City: " + address.get("city").getAsString());
            System.out.println("Number of orders: " + orders.size());
        } catch (JsonSyntaxException e) {
            System.err.println("Json parsing error: " + e.getMessage());
        }
    }
}
