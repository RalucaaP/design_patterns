package org.example;

import org.example.builder.Car;
import org.example.factory.Document;
import org.example.factory.DocumentFactory;

import java.util.Scanner;

public class CarManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DocumentEditor editor = new DocumentEditor();

        System.out.println("Car Management System");
        System.out.println("1. Configure a Car");
        System.out.println("2. Open a Document");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                System.out.println("Configuring a Car...");
                Car car = new Car.Builder()
                        .setEngine("V6")
                        .setTransmission("Manual")
                        .setInterior("Fabric")
                        .setColor("Blue")
                        .setSunroof(false)
                        .setGPS(true)
                        .setSafetyPackage(true)
                        .build();
                System.out.println("Configured Car: " + car);
                break;
            case 2:
                System.out.print("Enter document type (PDF, Word, HTML): ");
                String docType = scanner.next();
                editor.openDocument(docType);
                break;
            default:
                System.out.println("Invalid option.");
        }
        scanner.close();
    }
}
