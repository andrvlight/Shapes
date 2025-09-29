import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import Shapes.*;
import Shapes.Point.*;

public class Main {
    public static void printLineFunction() {
        System.out.println();
        String line = "";

        for (int i = 0; i < 50; i++)
            line = line + "-";

        System.out.println(line);
        System.out.println();
    }

    public static void createFile() {
        try {
            File file = new File("shapes.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void fillFileWithShapes(int n) {
        ArrayList<String> shapesList = new ArrayList<>();
        shapesList.add("C");
        shapesList.add("S");
        shapesList.add("T");
        shapesList.add("H");

        try (FileWriter fw = new FileWriter("shapes.txt")) {
            fw.write(n + "\n");

            for (int i = 0; i < n; i++) {
                String shapeType = shapesList.get((int)(Math.random() * 4));
                double x = (int) (Math.random() * 100 - 50);
                double y = (int) (Math.random() * 100 - 50);
                double size = (int) (Math.random() * 15 + 1);

                fw.write(shapeType + " " + x + " " + y + " " + size + "\n");
            }
            System.out.println("Successfully wrote " + n + " shapes to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        File shapes = new File("shapes.txt");
        Map<String, Shape> shapesList = new LinkedHashMap<>();

        int circleCount = 0;
        int squareCount = 0;
        int triangleCount = 0;
        int hexagonCount = 0;
        int containsCount = 0;

        Scanner input = new Scanner(System.in);
        System.out.print("Enter coordinate X: ");
        double x = input.nextDouble();
        System.out.print("Enter coordinate Y: ");
        double y = input.nextDouble();
        Point point = new Point(x, y);

        System.out.print("Pls enter how many shapes you would like to check: ");
        int numberOfShapes = input.nextInt();

        createFile();
        fillFileWithShapes(numberOfShapes);

        try (Scanner scanner = new Scanner(shapes)) {
            numberOfShapes = Integer.parseInt(scanner.nextLine());
            int tempNum = numberOfShapes;
            while (scanner.hasNextLine() && tempNum > 0) {
                String line = scanner.nextLine();
                String[] temp = line.split(" ");
                String shapeType =  temp[0];
                double coordinateX = Double.parseDouble(temp[1]);
                double coordinateY = Double.parseDouble(temp[2]);
                double sideLengthOrRadius = Double.parseDouble(temp[3]);
                tempNum--;

                switch (shapeType) {
                    case "C":
                        circleCount++;
                        Circle circle = new Circle(new Point(coordinateX, coordinateY), sideLengthOrRadius);
                        shapesList.put("Circle ID:" + circleCount, circle);
                        break;

                    case "S":
                        squareCount++;
                        Square square = new Square(new Point(coordinateX, coordinateY), sideLengthOrRadius);
                        shapesList.put("Square ID:" + squareCount, square);
                        break;

                    case "T":
                        triangleCount++;
                        Triangle triangle = new Triangle(new Point(coordinateX, coordinateY), sideLengthOrRadius);
                        shapesList.put("Triangle ID:" + triangleCount, triangle);
                        break;

                    case "H":
                        hexagonCount++;
                        Hexagon hexagon = new Hexagon(new Point(coordinateX, coordinateY), sideLengthOrRadius);
                        shapesList.put("Hexagon ID:" + hexagonCount, hexagon);
                        break;

                    default:
                        throw new IllegalArgumentException("Invalid shapeType " + shapeType);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("Shapes list size: " + numberOfShapes);

        printLineFunction();
        shapesList.forEach((k, v) -> {System.out.println(k + " -> " + v);});

        printLineFunction();
        for (Map.Entry<String, Shape> entry : shapesList.entrySet()) {
            if (entry.getValue().contains(point)) {
                containsCount++;
                System.out.println(entry.getKey() + " contains the point (" + point.getX() + ", " + point.getY() + ")");
            }
        }

        printLineFunction();
        System.out.println("Total shapes containing the point: " + containsCount);

        printLineFunction();
    }
}
