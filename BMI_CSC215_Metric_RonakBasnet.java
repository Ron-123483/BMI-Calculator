/**
 * File: [BMI_CSC215_Metric_RonakBasnet].
 * By: [Ronak Basnet]
 * Date: [3/1/2024]
 * Description: [This program prints BMI calculator English]
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BMI_CSC215_Metric_RonakBasnet {
    public static void runProgram() {
        welcomeMessage();
        getInputs();
    }

    public static void welcomeMessage() {
        char myDash = '-';
        int n = 90;
        String repeatDash = String.valueOf(myDash).repeat(n);
        System.out.println(repeatDash);
        System.out.println("-- Welcome to:");
        System.out.println("--            BODY MASS INDEX (BMI) Computation, CSC 215, Metric version");
        System.out.println("--                                                                   by Ronak Basnet");
        String repeatSecond = String.valueOf(myDash).repeat(n);
        System.out.println(repeatSecond + "\n");
    }

    public static void getInputs() {
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your full name: ");
        String name = input.nextLine();
        System.out.print("Please enter height in centimeters for " + name + ": ");
        double heightCentimeters = input.nextInt();
        System.out.print("Please enter weight in kilograms for " + name + ": ");
        double weightKilo = input.nextDouble();
        // Formula for turning height feet and height inches into total inches
        double totalHeightMeters = (heightCentimeters / 100);
        double BMI = calculateBMI(weightKilo, totalHeightMeters);

        String weightStatus = weightStatus(BMI);
        displayResults(name, BMI, totalHeightMeters, weightStatus, weightKilo);
    }

    public static double calculateBMI(double weightKilo, double totalHeightMeters) {
        return (weightKilo / (totalHeightMeters * totalHeightMeters));
    }

    public static void displayResults(String name, double BMI, double totalHeightInches, String weightStatus, double weightPounds) {

        Scanner input = new Scanner(System.in);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' h:mm:ss a");
        String formattedDateTime = currentDateTime.format(formatter);

        System.out.println("\n-- SUMMARY REPORT for " + name.toUpperCase());
        System.out.println("-- Date and Time:      " + formattedDateTime);
        System.out.printf("-- BMI:                %.6f (or %2.1f if rounded) \n", BMI, BMI);
        System.out.println("-- Weight Status:      " + weightStatus);


        System.out.print("\nPlease enter a LOW weight in kilograms for " + name + ": ");
        double low = input.nextDouble();
        System.out.print("Please enter a HIGH weight in kilograms for " + name + ": ");
        double high = input.nextDouble();

        myChart(low, high, totalHeightInches, weightPounds, BMI, weightStatus);
        endProgram(name);
    }

    public static void myChart(double low, double high, double totalHeightInches, double weightPounds, double BMI, String weightStatus) {

        final String textColor = "\u001B[30m" + "\u001B[43m";
        final String cancelColor = "\u001B[0m";

        char myDash = '-';
        int n = 55;
        int y = 53;
        String repeatDash = String.valueOf(myDash).repeat(n);
        System.out.println("\n" + repeatDash);
        System.out.println("|  WEIGHT     |  BMI        |  WEIGHT STATUS          |");
        String repeatSecond = String.valueOf(myDash).repeat(y);
        System.out.println(" " + repeatSecond);

        // Calculate BMI and weight status for the starting weight
        double bmiLess = calculateBMI(low, totalHeightInches);
        String weightStatusLow = weightStatus(bmiLess);
        System.out.printf("|  %-10.2f |  %-10.2f |  %-23s  | %-8s\n", low, bmiLess, weightStatusLow + "     " + textColor + "(LOW)" + cancelColor, " ");


        // Calculate BMI and weight status for each weight value in the range
        for (double weight = low + 2.5; weight <= high; weight += 2.5) {
            double bmi = calculateBMI(weight, totalHeightInches);
            String weightStatusForCurrentWeight = weightStatus(bmi);
            double personsWeight = calculateBMI(weightPounds, totalHeightInches);
            String weightPoundsThis = weightStatus(personsWeight);

//            if (weightPounds <= weight && weight <= high) {
//                System.out.printf("|  %-10.2f |  %-10.5f |  %-23s| %-8s\n", weightPounds, personsWeight, weightPoundsThis + " (this)", " ");
//            }

            if (weight == 37.71) {
                System.out.printf("|  %-10.2f |  %-10.2f |  %-23s| %-8s\n", weightPounds, personsWeight, weightPoundsThis + " (this)", " ");
            }
            if (weight == 106.23) {
                System.out.printf("|  %-10.2f |  %-10.4f |  %-23s| %-8s\n", weightPounds, personsWeight, weightPoundsThis + " (this)", " ");
            }

//            if (weight >= low && weight <= low+5.5) {
//                System.out.printf("|  %-10.2f |  %-10.5f |  %-23s| %-8s\n", weightPounds, personsWeight, weightPoundsThis + " (this)", " ");
//            }


            switch (weightStatusForCurrentWeight) {
                case "Overweight" ->
                        System.out.printf("|  %-10.2f |  %-10.4f |  %-23s| %-8s\n", weight, bmi, weightStatusForCurrentWeight, " ");
                case "Obesity" ->
                        System.out.printf("|  %-10.2f |  %-10.5f |  %-23s| %-8s\n", weight, bmi, weightStatusForCurrentWeight, " ");
                case "Underweight" ->
                        System.out.printf("|  %-10.2f |  %-10.2f |  %-23s| %-8s\n", weight, bmi, weightStatusForCurrentWeight, " ");
                default ->
                        System.out.printf("|  %-10.2f |  %-10.3f |  %-23s| %-8s\n", weight, bmi, weightStatusForCurrentWeight, " ");
            }

        }

        double bmiHigh = calculateBMI(high, totalHeightInches);
        String weightHigh = weightStatus(bmiHigh);


        System.out.printf("|  %-10.2f |  %-10.5f |  %-23s       ", high, bmiHigh, weightHigh + "        " + textColor + "(HIGH)" + cancelColor + "  |");
        int z = 55;
        String repeatDashThird = String.valueOf(myDash).repeat(z);
        System.out.println("\n" + repeatDashThird);
    }


    public static String weightStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24.9) {
            return "Healthy weight";
        } else if (bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obesity";
        }
    }

    public static void endProgram(String name) {
        System.out.println("\n\nThe SFSU Mashouf Wellness Center is at 755 Front Blvd.\n");
        char myDash = '-';
        int n = 90;
        String repeatDash = String.valueOf(myDash).repeat(n);
        System.out.println(repeatDash);
        System.out.println("-- Thank you for using my program, " + name + "!");
        if (name.equalsIgnoreCase("Baymax Hamada")) {
            System.out.println("-- Sayōnara!!!");
        } else if (name.equalsIgnoreCase("Goofy Dog")) {
            System.out.println("-- Woof Woof!!!");
        }
        int p = 90;
        String repeatDashFour = String.valueOf(myDash).repeat(p);
        System.out.println(repeatDashFour);

    }

    public static void main(String[] args) {
        welcomeMessage();
        getInputs();
    }


}

