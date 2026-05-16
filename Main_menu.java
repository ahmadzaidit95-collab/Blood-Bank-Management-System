package com.company;
import java.util.Scanner;

public class Main_menu {
    Admin admin = new Admin();
    Donor donor = new Donor();
    Patient patient = new Patient();
    Scanner input = new Scanner(System.in);
    public void showMenu() {
        int choice = 0;
        do {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Admin");
            System.out.println("2. Donor");
            System.out.println("3. Patient");
            System.out.println("4. Exit");

            System.out.print("Please enter the transaction number: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        admin.login();
                        break;
                    case 2:
                        donor.menu();
                        break;
                    case 3:
                        patient.menu();
                        break;
                    case 4:
                        System.out.println("Program Closed");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();// يمسح الحرف او الحاجه الغلط عشان مش تفضل متخزنه
            }
        } while (choice != 4);
    }
}