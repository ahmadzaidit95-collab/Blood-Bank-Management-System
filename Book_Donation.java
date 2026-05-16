package com.company;

import java.util.Scanner;

public class Book_Donation {
    //دي تبع الفايل
    FileHandling file = new FileHandling();
    public Book_Donation(DonorManager manager) {
        this.manager = manager;
        file.readStock();
    }
    //---------
    Scanner input = new Scanner(System.in);

    DonorManager manager;

    // Blood Stock
    static int Aplus = 0;
    static int Aminus = 0;
    static int Bplus = 0;
    static int Bminus = 0;
    static int ABplus = 0;
    static int ABminus = 0;
    static int Oplus = 0;
    static int Ominus = 0;

    public void newDonation() {
        System.out.print("Enter Your ID: ");
        int id = input.nextInt();
        boolean found = false;
        for (int i = 0; i < manager.count; i++) {
            if (manager.donors[i].getId() == id) {
                DonorData donor = manager.donors[i];
                found = true;
                if (donor.getAge() >= 18 && donor.getLastDonation() >= 6) {
                    input.nextLine();
                    System.out.print("Enter Donation Date: ");
                    String date = input.nextLine();
                    System.out.println("\nDonation Booked Successfully");

                    System.out.println("Donor Name: " + donor.getName());
                    System.out.println("Blood Type: " + donor.getBloodType());

                    String blood = donor.getBloodType();
                    switch (blood) {

                        case "A+":
                            Aplus++;
                            break;

                        case "A-":
                            Aminus++;
                            break;

                        case "B+":
                            Bplus++;
                            break;

                        case "B-":
                            Bminus++;
                            break;

                        case "AB+":
                            ABplus++;
                            break;

                        case "AB-":
                            ABminus++;
                            break;

                        case "O+":
                            Oplus++;
                            break;

                        case "O-":
                            Ominus++;
                            break;
                    }
                    System.out.println("Blood Stock Updated");

                    // تبع الفايل
                    file.saveDonation(donor, date);
                    file.saveStock(Aplus,Aminus,Bplus, Bminus, ABplus, ABminus, Oplus, Ominus);
                    //-----------
                } else {
                    System.out.println("You cannot donate now");
                }
                break;
            }
        }
        if (!found) {
            System.out.println("Donor not found");
        }
    }
    public void viewBloodStock() {
        System.out.println("\nBlood Stock\n -----------");
        System.out.println("A+ : " + Aplus + " bags");
        System.out.println("A- : " + Aminus + " bags");
        System.out.println("B+ : " + Bplus + " bags");
        System.out.println("B- : " + Bminus + " bags");
        System.out.println("AB+ : " + ABplus + " bags");
        System.out.println("AB- : " + ABminus + " bags");
        System.out.println("O+ : " + Oplus + " bags");
        System.out.println("O- : " + Ominus + " bags");
        System.out.println("\n Warnings\n--------");
        if (Aplus < 3)
            System.out.println("Warning: A+ blood is low");
        if (Aminus < 3)
            System.out.println("Warning: A- blood is low");
        if (Bplus < 3)
            System.out.println("Warning: B+ blood is low");
        if (Bminus < 3)
            System.out.println("Warning: B- blood is low");
        if (ABplus < 3)
            System.out.println("Warning: AB+ blood is low");
        if (ABminus < 3)
            System.out.println("Warning: AB- blood is low");
        if (Oplus < 3)
            System.out.println("Warning: O+ blood is low");
        if (Ominus < 3)
            System.out.println("Warning: O- blood is low");
    }
}