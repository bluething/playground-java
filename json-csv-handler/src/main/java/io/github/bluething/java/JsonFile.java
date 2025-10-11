package io.github.bluething.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonFile implements JsonParser {
    @Override
    public void parse(String input) {
        try {
            String content = Files.readString(Path.of(input));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonElement jsonElement = gson.fromJson(content, JsonElement.class);
            String prettyJson = gson.toJson(jsonElement);
            System.out.println(prettyJson);
        } catch (IOException e) {
            System.err.println("File reading error: " + e.getMessage());
            throw new RuntimeException(e);
        } catch (JsonSyntaxException e) {
            System.err.println("Json syntax error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
