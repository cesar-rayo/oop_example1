package com.example.java;

import com.example.java.model.ClothingItem;
import com.example.java.model.Product;
import com.example.java.model.Shirt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    public static void main(String[] args){
        Shirt shirt = new Shirt("M", 14.99);
        displayProduct(shirt);
        shirt.setPattern("Plaid");
        System.out.println("This shirt pattern is: " + shirt.getPattern());

        // casting objects
        ClothingItem reallyAShirt = new Shirt("L", 12.99);
        displayProduct(reallyAShirt);

        Shirt shirt2 = (Shirt) reallyAShirt;
        shirt2.setPattern("Solid");
        System.out.println("This shirt pattern is: " + shirt2.getPattern());

        System.out.println("-->>  Array of ints");
        int[] ints = {3,6,9};
        for (int anInt: ints) {
            System.out.println(anInt);
        }

        // Arrays are not resizeable
        System.out.println("-->> Array of Strings");
        String[] colors = {"Red","Blue","Green"};
        for (String color: colors) {
            System.out.println(color);
        }

        System.out.println("-->> Array of ClothingItem");
        ClothingItem[] items = new ClothingItem[3];
        items[0] = new Shirt("L", 15.5);
        items[1] = new Shirt("M", 10.99);

        for (ClothingItem item: items) {
            System.out.println(item);
        }

        ClothingItem[] copied = Arrays.copyOf(items, items.length); // pointing to the same object
        for (ClothingItem item: copied) {
            System.out.println(item);
        }

        // change is reflected in both objects
        items[0].setSize("S");
        System.out.println(items[0]);
        System.out.println(copied[0]);

        // Lists-Collections are resizeable
        List<String> states = new ArrayList<>();
        states.add("California");
        states.add("Oregon");
        states.add("Washington");
        for (String state: states) {
            System.out.println(state);
        }
        System.out.println("The second state is: " + states.get(1));
        System.out.println("The idex of Washington is: " + states.indexOf("Washington"));

        List<Integer> ints1 = new ArrayList<>();
        ints1.add(12);
        ints1.add(23);
        ints1.add(32);
        for (Integer anInt: ints1) {
            System.out.println(anInt);
        }
        ints1.remove(1);
        for (Integer anInt: ints1) {
            System.out.println(anInt);
        }

        // <keyType, valueType>
        Map<String, String> map = new HashMap<>();
        map.put("California", "Sacramento");
        map.put("Oregon", "Salem");
        map.put("Washington", "Olympia");
        System.out.println(map);

        map.put("Alaska", "Juneau");
        System.out.println(map);

        String capitol = map.get("Oregon");
        System.out.println("The capitol is " + capitol);

        map.remove("California");
        System.out.println(map);

        Date now = new Date();
        System.out.println(now.toString());

        // month = 1 => February because the indexing
        GregorianCalendar gc = new GregorianCalendar(2009, 1, 28);
        Date d1 = gc.getTime();
        System.out.println(d1);

        // creates new date
        gc.add(GregorianCalendar.DATE, 1);
        Date d2 = gc.getTime();
        System.out.println(d2);
        DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
        // Sunday, March 1, 2009
        System.out.println(df.format(d2));

        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);
        // 2009-01-28
        LocalDate ld = LocalDate.of(2009,1,28);
        System.out.println(ld);

        // 1/28/2009
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("M/d/yyyy");
        System.out.println(dtf.format(ld));


        // Reading files
        String sourceFile = "files/loremipsum.txt";
        String targetFile = "files/target.txt";

        // Adding readers and writer to be closed automatically
        try(FileReader fileReader = new FileReader(sourceFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter writer = new FileWriter(targetFile);
            ) {
            while(true){
                String line = bufferedReader.readLine();
                if(line == null){
                    break;
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading files new way
        Path sourceFile1 = Paths.get("files", "loremipsum.txt");
        Path targetFile1 = Paths.get("files", "target1.txt");

        try {
            //Files.copy(sourceFile1, targetFile1, StandardCopyOption.REPLACE_EXISTING);
            System.out.println(Files.readAllLines(sourceFile1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void displayProduct(Product product){
        String output = product.getClass().getSimpleName() + "{" +
                "type='" + product.getType() + '\'' +
                ", size='" + product.getSize() + '\'' +
                ", price=" + product.getPrice() +
                '}';
        System.out.println(output);
    }
}
