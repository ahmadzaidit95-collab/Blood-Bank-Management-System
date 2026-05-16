package com.company;
import java.util.Scanner;

public class PatientManager {

    // تبع الفايل
    FileHandling file = new FileHandling();

    public PatientManager() {

        file.readPatients(patients, this);
    }
    //---------------
    Scanner input = new Scanner(System.in);
    PatientData[] patients = new PatientData[100];
    int count = 0;
    public void addPatient() {
        PatientData patient = new PatientData();
        int id;
        while (true) {
            System.out.print("Enter Patient's ID: ");
            if (input.hasNextInt()) {
                id = input.nextInt();
                boolean exist = false;
                for (int i = 0; i < count; i++) {
                    if (patients[i].getId() == id) {
                        exist = true;
                        break;
                    }
                }
                if (exist) {
                    System.out.println("ID already exists ");
                } else {
                    patient.setId(id);
                    break;
                }
            } else {
                System.out.println("invalid ID");
                input.next();
                return;
            }
        }
        input.nextLine();
        System.out.print("please enter a Name: ");
        patient.setName(input.nextLine());
        String bloodType;
        while (true) { //عشان يدخل فصيله دم من دول  ميكتبش اي حاجه
            System.out.print("please enter the blood type: ");
            bloodType = input.nextLine().toUpperCase().trim();
            if (
                    bloodType.equals("A+") || bloodType.equals("A-") ||
                            bloodType.equals("B+") || bloodType.equals("B-") ||
                            bloodType.equals("AB+") || bloodType.equals("AB-") ||
                            bloodType.equals("O+") || bloodType.equals("O-")
            ) {
                patient.setBloodType(bloodType);
                break;
            } else {
                System.out.println("invalid a blood type");
            }
        }
        int bags;
        while (true) {
            System.out.print("Enter number of bags needed: ");
            if (input.hasNextInt()) {
                bags = input.nextInt();
                if (bags > 0) {
                    patient.setBagsNeeded(bags);
                    break;
                } else {
                    System.out.println("Bags must be greater than 0");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        }
        patients[count] = patient;
        count++;
        System.out.println("Patient Added Successfully!");
        //دي عشان يحفظ الراجل في الملف
        file.savePatients(patients, count);
        //-------
    }
    public void showPatients() {
        if (count == 0) {
            System.out.println("No patients found");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println("\n===== Patient " + (i + 1) + " =====");
            System.out.println("ID: " + patients[i].getId());
            System.out.println("Name: " + patients[i].getName());
            System.out.println("Blood Type: " + patients[i].getBloodType());
            System.out.println("Bags Needed: " + patients[i].getBagsNeeded());
        }
    }

    public void deletePatient() {
        System.out.print("Enter Patient ID To Delete: ");
        int id = input.nextInt();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (patients[i].getId() == id) {
                for (int j = i; j < count - 1; j++) {
                    patients[j] = patients[j + 1];
                }
                patients[count - 1] = null;
                count--;
                found = true;
                System.out.println("Patient Deleted Successfully");

                //دي عشان نشيل الراجل من الملف
                file.savePatients(patients, count);
                //-----
                break;
            }
        }
        if (!found) {
            System.out.println("Patient not found");
        }
    }
    public void searchPatient() {
        System.out.print("Enter Patient ID: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (patients[i].getId() == id) {
                    System.out.println("\nPatient Found");
                    System.out.println("ID: " + patients[i].getId());
                    System.out.println("Name: " + patients[i].getName());
                    System.out.println("Blood Type: " + patients[i].getBloodType());
                    System.out.println("Bags Needed: " + patients[i].getBagsNeeded());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Patient not found");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
        }
    }

    public void viewMyData() {
        System.out.print("Enter Your ID: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (patients[i].getId() == id) {
                    System.out.println("\n===== Your Data =====");
                    System.out.println("ID: " + patients[i].getId());
                    System.out.println("Name: " + patients[i].getName());
                    System.out.println("Blood Type: " + patients[i].getBloodType());
                    System.out.println("Bags Needed: " + patients[i].getBagsNeeded());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Data not found");
            }
        } else {
            System.out.println("Invalid input. Please enter a number.");
            input.next();
        }
    }
    public void requestBlood() {
        System.out.print("Enter Your ID: ");
        if (input.hasNextInt()) {
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (patients[i].getId() == id) {
                    PatientData patient = patients[i];
                    found = true;
                    String blood = patient.getBloodType();
                    int bags = patient.getBagsNeeded();
                    int available = 0;
                    switch (blood) {
                        case "A+":
                            available = Book_Donation.Aplus;
                            break;
                        case "A-":
                            available = Book_Donation.Aminus;
                            break;
                        case "B+":
                            available = Book_Donation.Bplus;
                            break;
                        case "B-":
                            available = Book_Donation.Bminus;
                            break;
                        case "AB+":
                            available = Book_Donation.ABplus;
                            break;
                        case "AB-":
                            available = Book_Donation.ABminus;
                            break;
                        case "O+":
                            available = Book_Donation.Oplus;
                            break;
                        case "O-":
                            available = Book_Donation.Ominus;
                            break;
                    }
                    if (available >= bags) {
                        switch (blood) {
                            case "A+":
                                Book_Donation.Aplus -= bags;
                                break;
                            case "A-":
                                Book_Donation.Aminus -= bags;
                                break;
                            case "B+":
                                Book_Donation.Bplus -= bags;
                                break;
                            case "B-":
                                Book_Donation.Bminus -= bags;
                                break;
                            case "AB+":
                                Book_Donation.ABplus -= bags;
                                break;
                            case "AB-":
                                Book_Donation.ABminus -= bags;
                                break;
                            case "O+":
                                Book_Donation.Oplus -= bags;
                                break;
                            case "O-":
                                Book_Donation.Ominus -= bags;
                                break;
                        }
                        System.out.println("\nBlood Request Approved");
                        System.out.println("Patient Name: " + patient.getName());
                        System.out.println("Blood Type: " + blood);
                        System.out.println("Bags Given: " + bags);
                        System.out.println("Blood Stock Updated");
                        file.saveRequest(patient);
                        file.saveStock(Book_Donation.Aplus, Book_Donation.Aminus, Book_Donation.Bplus, Book_Donation.Bminus, Book_Donation.ABplus, Book_Donation.ABminus, Book_Donation.Oplus, Book_Donation.Ominus);
                    } else {
                        System.out.println("Not enough blood in stock. Available: " + available + " bags");
                    }
                    break;
                }
            }
            if (!found) {
                System.out.println("Patient not found");
            }

        } else {
            System.out.println("Invalid input. Please enter a number.");
            input.next();
        }

    }
}