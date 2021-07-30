package com.example.java.model;

public class TestObject {
    private String category;
    private String instructions;
    private double price;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "TestObject{" +
                "category='" + category + '\'' +
                ", instructions='" + instructions + '\'' +
                ", price=" + price +
                '}';
    }
}
