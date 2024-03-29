/**
 *File: [BMI_CSC215_Master_RonakBasnet].
 * By: [Ronak Basnet]
 * Date: [3/1/2024]
 * Description: [This program prints BMI calculator English]
 *
 */
import java.util.Scanner;

public class BMI_Master_RonakBasnet {
    public static void runMaster() {
        String choice;

        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("My CSC 215 BMI Calculator Projects:");
            System.out.println("   1. BMI, English ");
            System.out.println("   2. BMI, Metric \n");
            System.out.println("[ USER MANUAL ] Enter an exclamation mark ! to end.");
            System.out.print("Please enter the version you want to try: ");
            choice = input.nextLine();

            if (choice.equals("!")) {
                System.out.println("Exiting!!");
                break;
            }
            String choiceLower = choice.toLowerCase();
            String choiceUpper = choice.toUpperCase();

            if (choiceLower.startsWith("eng")) {
                BMI_CSC215_English_RonakBasnet.runProgram();
            } else if (choiceUpper.startsWith("MET") && choiceLower.startsWith("met") ) {
                BMI_CSC215_Metric_RonakBasnet.runProgram();
            } else if(choice.contains("m")){
                BMI_CSC215_Metric_RonakBasnet.runProgram();
            }
        }
    }

    public static void main(String[] args) {
        runMaster();


    }
}



