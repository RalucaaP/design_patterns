package org.example.factory;

public class HTMLDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening an HTML document.");
    }
}