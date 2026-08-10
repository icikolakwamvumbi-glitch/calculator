import java.util.Scanner;

public class Main {

    private double current = 0;
    private double previous = 0;
    private String operator = "";
    private boolean hasPending = false;

    public static void main(String[] args) {
        Main calculator = new Main();
        calculator.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Java Calculator (type 'exit' to quit, 'help' for commands)");
        System.out.println("Result: 0");

        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            if (line.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }
            if (line.equalsIgnoreCase("C")) {
                current = 0;
                previous = 0;
                operator = "";
                hasPending = false;
                System.out.println("Result: 0");
                continue;
            }

            try {
                processLine(line);
            } catch (Exception e) {
                System.out.println("Invalid input. Type 'help' for usage.");
            }
        }

        scanner.close();
        System.out.println("Goodbye.");
    }

    private void printHelp() {
        System.out.println("Examples:");
        System.out.println("  5 + 3        addition");
        System.out.println("  10 - 4       subtraction");
        System.out.println("  8 x 2        multiplication");
        System.out.println("  10 ÷ 4       division");
        System.out.println("  sqrt 16      square root");
        System.out.println("  sq 5         square");
        System.out.println("  inv 4        reciprocal (1/x)");
        System.out.println("  neg 7        negate (±)");
        System.out.println("  pct 50       percent (/100)");
        System.out.println("  C            clear");
        System.out.println("  exit         quit");
    }

    private void processLine(String line) {
        String[] parts = line.split("\\s+");

        // Single-argument operations: sqrt, sq, inv, neg, pct
        if (parts.length == 2) {
            double value = Double.parseDouble(parts[1]);
            double result;
            switch (parts[0].toLowerCase()) {
                case "sqrt":
                    result = Math.sqrt(value);
                    break;
                case "sq":
                    result = Math.pow(value, 2);
                    break;
                case "inv":
                    result = 1 / value;
                    break;
                case "neg":
                    result = -value;
                    break;
                case "pct":
                    result = value / 100;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown command");
            }
            current = result;
            System.out.println("Result: " + format(result));
            return;
        }

        // Binary operations: num1 op num2
        if (parts.length == 3) {
            previous = Double.parseDouble(parts[0]);
            operator = parts[1];
            current = Double.parseDouble(parts[2]);
            double result = calculate(previous, operator, current);
            current = result;
            System.out.println("Result: " + format(result));
            return;
        }

        throw new IllegalArgumentException("Unrecognized format");
    }

    private double calculate(double num1, String operator, double num2) {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "x":
            case "*":
                return num1 * num2;
            case "÷":
            case "/":
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private String format(double value) {
        if (value == Math.floor(value) && !Double.isInfinite(value)) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}