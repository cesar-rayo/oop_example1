package com.example.java;

import com.example.java.model.TestObject;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainJson {
    private static final String DATA_URL = "https://services.hanselandpetal.com/feeds/flowers.json";
    public static void main(String[] args) throws IOException {
        // Reading json files using gson lib
        String fileName = "files/data_json.txt";

        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader(fileName);
             JsonReader reader = new JsonReader(fileReader);
        ){
            TestObject[] data = gson.fromJson(reader, TestObject[].class);
            for ( TestObject object: data) {
                System.out.println(object.toString());
            }
        }

        // Reading from http request
        System.out.println("---> Performing http request");
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(new URI(DATA_URL))
                            .GET()
                            .build();
            // perform http request
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            // get response
            String requestData = response.body();
            try{
                Gson gson2 = new Gson();
                TestObject[] testObjects = gson.fromJson(requestData, TestObject[].class);
                for ( TestObject object: testObjects) {
                    System.out.println(object.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }


    }
}
