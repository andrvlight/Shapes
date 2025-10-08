import java.io.*;
import java.util.*;

import Shapes.Point.*;
import Shapes.*;

public class Main {
    private static void printLine() {
        System.out.println("-".repeat(100));
    }

    private static double getValidatedCoordinate(Scanner input, String coordinate) {
        while (true) {
            try {
                printLine();
                System.out.print("Enter coordinate " + coordinate + " between -5 and 5: ");
                double value = input.nextDouble();

                if (value < -5 || value > 5) {
                    printLine();
                    System.out.println("Coordinates should be between -10 and 10, try again");
                    continue;
                }

                return value;
            } catch (InputMismatchException e) {
                printLine();
                System.out.println("Pleases enter a DOUBLE. Try again");
                input.next();
            }
        }
    }

    private static int getValidatedNumberOfShapes(Scanner input) {
        while (true) {
            try {
                printLine();
                System.out.print("Enter number of shapes between 1 and 50: ");
                int value = input.nextInt();

                if (value < 1 || value > 50) {
                    printLine();
                    System.out.println("Number should be between 1 and 50, try again");
                    continue;
                }

                return value;
            } catch (InputMismatchException e) {
                printLine();
                System.out.println("Pleases enter an INTEGER. Try again");
                input.next();
            }
        }
    }

    private static void createFile() {
        try {
            File file = new File("shapes.txt");
            if (file.createNewFile()) {
                printLine();
                System.out.println("File created: " + file.getName());
            } else {
                printLine();
                System.out.println("File already exists: " + file.getName());
            }
        } catch (IOException e) {
            printLine();
            System.out.println("Error while creating file: " + e.getMessage());
        }
    }

    private static void fillFileWithShapes(int numberOfShapes) {
        ArrayList<String> shapes = new ArrayList<>();
        shapes.add("C");
        shapes.add("S");
        shapes.add("H");
        shapes.add("T");

        try (FileWriter fw = new FileWriter("shapes.txt")) {
            fw.write(numberOfShapes + "\n");

            for (int i = 0; i < numberOfShapes; i++) {
                String shapeType = shapes.get((int) (Math.random() * shapes.size()));
                double x = (int) (Math.random() * 22 - 11);
                double y = (int) (Math.random() * 22 - 11);
                int size = (int) (Math.random() * 10 + 1);

                fw.write(shapeType + " " + x + " " + y + " " + size + "\n");
            }

            printLine();
            System.out.println("Shapes filled successfully");
        } catch (IOException e) {
            printLine();
            System.out.println("Error while filling file: " + e.getMessage());
        }
    }

    private static String getResult(Map.Entry<String, Shape> entry, Point point) {
        String[] temp = entry.getKey().split(" ");
        String type = temp[0];
        Shape shape = entry.getValue();

        String result = (entry.getKey() + " with center (" + shape.getCenter().getX() + ", " + shape.getCenter().getY()) + ")";
        String pointInfo = (" contains the point (" + point.getX() + ", " + point.getY()) + ")";

        if (type.equals("Circle")) {
            result += (" and radius: " + shape.getSize());
        } else {
            result += (" and side length: " + shape.getSize());
        }

        return result + pointInfo;
    }

    public static Map<String, Shape> loadShapesFromFile() {
        int circleCount = 0;
        int squareCount = 0;
        int triangleCount = 0;
        int hexagonCount = 0;
        Map<String, Shape> shapes = new LinkedHashMap<>();

        try(Scanner scanner = new Scanner(new File("shapes.txt"))) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] temp  = line.split(" ");
                String type = temp[0];
                double x = Double.parseDouble(temp[1]);
                double y = Double.parseDouble(temp[2]);
                double size = Double.parseDouble(temp[3]);

                switch (type) {
                    case "C":
                        circleCount++;
                        Circle circle = new Circle(new Point(x, y), size);
                        shapes.put("Circle ID:" + circleCount, circle);
                        break;

                    case "S":
                        squareCount++;
                        Square square = new Square(new Point(x, y), size);
                        shapes.put("Square ID:" + squareCount, square);
                        break;

                    case "T":
                        triangleCount++;
                        Triangle triangle = new Triangle(new Point(x, y), size);
                        shapes.put("Triangle ID:" + triangleCount, triangle);
                        break;

                    case "H":
                        hexagonCount++;
                        Hexagon hexagon = new Hexagon(new Point(x, y), size);
                        shapes.put("Hexagon ID:" + hexagonCount, hexagon);
                        break;

                    default:
                        printLine();
                        System.out.println("Warning: Unknown shape type " + type + " skipping");
                }
            }
        } catch (IOException e) {
            printLine();
            System.out.println("Error while filling file: " + e.getMessage());
        }


        return shapes;
    }

    static void main() {
        double x = -11;
        double y = -11;
        int numberOfShapes = 0;

        Map<String, Shape> shapes;

        try (Scanner input = new Scanner(System.in)) {
            x = getValidatedCoordinate(input, "X");
            y = getValidatedCoordinate(input, "Y");
            numberOfShapes = getValidatedNumberOfShapes(input);
        } catch (Exception e) {
            printLine();
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }

        Point point  = new Point(x,y);
        createFile();
        fillFileWithShapes(numberOfShapes);

        shapes = loadShapesFromFile();
        printLine();
        shapes.forEach((key, shape) ->
                System.out.println(key + " -> center: (" + shape.getCenter().getX() + ", " + shape.getCenter().getY() + "), size: " + shape.getSize()));

        printLine();
        int containsCount = 0;
        for (Map.Entry<String, Shape> entry : shapes.entrySet()) {
            if (entry.getValue().contains(point)) {
                containsCount++;
                System.out.println(getResult(entry, point));
            }
        }

        if (containsCount == 0) {
            System.out.println("No shapes contain this point.");
        } else {
            printLine();
            System.out.println("Total shapes containing the point: " + containsCount);
        }

        printLine();
    }
}
