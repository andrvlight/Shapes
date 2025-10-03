import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import Shapes.*;
import Shapes.Point.*;

public class Main {
    public static void printLineFunction() {
        System.out.println();
        String line = "";

        for (int i = 0; i < 100; i++)
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
                double x = (int) (Math.random() * 20 - 10);
                double y = (int) (Math.random() * 20 - 10);
                double size = (int) (Math.random() * 5 + 1);

                fw.write(shapeType + " " + x + " " + y + " " + size + "\n");
            }
            System.out.println("Successfully wrote " + n + " shapes to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static String getResult(Map.Entry<String, Shape> entry, Point point) {
        String result = entry.getKey()+ " with center (" + entry.getValue().getCenter().getX() + ", " + entry.getValue().getCenter().getY() + ")";
        String tempPoint = " contains the point (" + point.getX() + ", " + point.getY() + ")";
        String[] temp = entry.getKey().split(" ");
        String type = temp[0];

        if (Objects.equals(type, "Circle"))
            result = result + " and radius: " + entry.getValue().getSize() + tempPoint;
        else
            result = result + " and side length: " + entry.getValue().getSize() + tempPoint;
        return result;
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

        double x = 0;
        try {
            System.out.print("Enter coordinate X: ");
            x = input.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid Input");
        }

        if (x > 10 || x < -10)
            throw new IllegalArgumentException("Invalid input: x should be between -10 and 10");

        double y = 0;
        try {
            System.out.print("Enter coordinate Y: ");
            y = input.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid Input");
        }

        if (y > 10 || y < -10)
            throw new IllegalArgumentException("Invalid input: y should be between -10 and 10");

        Point point = new Point(x, y);

        int numberOfShapes = 0;
        try {
            System.out.print("Pls enter how many shapes you would like to check between(any number form 1 to 100): ");
            numberOfShapes = input.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Invalid input");
        }

        if (numberOfShapes < 1 || numberOfShapes > 100)
            throw new IllegalArgumentException("Invalid Input: number should be between 1 and 100");

        printLineFunction();
        createFile();
        printLineFunction();
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
                String result = getResult(entry, point);

                System.out.println(result);
            }
        }

        if(containsCount == 0)
            System.out.println("No such shapes that contains this point");
        else {
            System.out.println("Total shapes containing the point: " + containsCount);
        }

        printLineFunction();
    }

}
