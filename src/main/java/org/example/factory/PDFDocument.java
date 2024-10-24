package org.example.factory;

public class PDFDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document.");
    }
}