package com.company;
import java.util.Scanner;

public class Donor {
    Scanner input = new Scanner(System.in);
    static DonorManager manager = new DonorManager();
    Book_Donation book =new Book_Donation(manager);
    public void menu() {
        int choice = 0;
        do {
            System.out.println("\n=== Donor Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Book Donation");
            System.out.println("3. View My Data");
            System.out.println("4. Exit");
            System.out.print("Please enter the transaction number:");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        manager.addDonor();
                        break;
                    case 2:
                        book.newDonation() ;

                        break;
                    case 3:
                        manager.viewMyData();
                        break;
                    case 4:
                        System.out.println("Exiting Donor Menu...");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        } while (choice != 4);
    }
}