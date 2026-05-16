package com.company;
import java.util.Scanner;

public class Admin {
    Scanner input = new Scanner(System.in);
    Book_Donation donation = new Book_Donation(Donor.manager);
    Patient patient = new Patient();
    public void login() {

        String correctUser = "ahmed";
        String correctPass = "1234";

        for (int i = 0; i < 3; i++) {   // ahmed zaid

            System.out.print("Username: ");
            String user = input.next();

            System.out.print("Password: ");
            String pass = input.next();

            if (user.equals(correctUser) && pass.equals(correctPass)) {
                System.out.println("Login Successful");
                adminMenu();
                return;
            } else {
                System.out.println("Wrong data");
            }
        }
        System.out.println("Too many attempts. Back to Main Menu");
    }
    public void adminMenu() {
        int choice = 0;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. View Donors");
            System.out.println("2. Search Donor");
            System.out.println("3. Delete Donor");
            System.out.println("4. View Patients");
            System.out.println("5. Search Patient");
            System.out.println("6. Delete Patient");
            System.out.println("7. View Blood Stock");
            System.out.println("8. Logout");

            System.out.print("Please enter the transaction number: ");

            if (input.hasNextInt()) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        Donor.manager.showDonors();
                        break;
                    case 2:
                        Donor.manager.searchDonor();
                        break;
                    case 3:
                        Donor.manager.deleteDonor();
                        break;
                    case 4:
                        patient.manager.showPatients();
                        break;
                    case 5:
                        patient.manager.searchPatient();
                        break;
                    case 6:
                        patient.manager.deletePatient();
                        break;
                    case 7:
                        donation.viewBloodStock();
                        break;
                    case 8:
                        System.out.println("Logging out...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        } while (choice != 8);
    }
}