package com.company;
import java.io.*;
import java.util.Scanner;

public class FileHandling {
    public void saveDonors(DonorData[] donors, int count) {
        try {
            FileWriter writer = new FileWriter("donors.txt");
            for(int i = 0; i < count; i++) {
                writer.write(donors[i].getId() + "," + donors[i].getName() + "," + donors[i].getAge() + "," + donors[i].getBloodType() + "," + donors[i].getPhone() + "," + donors[i].getAddress() + "," + donors[i].getLastDonation() + "\n");
            }
            writer.close();
        } catch(Exception e) {
            System.out.println("Error saving donors");
        }
    }

    public void readDonors(DonorData[] donors, DonorManager manager )
    {
        try {
            File file = new File("donors.txt");
            if(!file.exists()) {
                return;
            }
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                DonorData donor = new DonorData();

                donor.setId(Integer.parseInt(data[0]));
                donor.setName(data[1]);
                donor.setAge(Integer.parseInt(data[2]));
                donor.setBloodType(data[3]);
                donor.setPhone(data[4]);
                donor.setAddress(data[5]);
                donor.setLastDonation(Integer.parseInt(data[6]));
                donors[manager.count] = donor;

                manager.count++;
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Error loading donors");
        }
    }

    public void saveDonation(DonorData donor, String date) {
        try {
            FileWriter writer = new FileWriter("donations.txt", true);
            writer.write(
                    donor.getId() + "," +
                    donor.getName() + "," +
                    donor.getBloodType() + "," +
                    date + "\n"
            );
            writer.close();
        } catch(Exception e) {
            System.out.println("Error saving donation");
        }
    }

    public void saveStock(int Aplus, int Aminus, int Bplus, int Bminus, int ABplus, int ABminus, int Oplus, int Ominus) {
        try {
            FileWriter writer = new FileWriter("stock.txt");

            writer.write("A+," + Aplus + "\n");
            writer.write("A-," + Aminus + "\n");
            writer.write("B+," + Bplus + "\n");
            writer.write("B-," + Bminus + "\n");
            writer.write("AB+," + ABplus + "\n");
            writer.write("AB-," + ABminus + "\n");
            writer.write("O+," + Oplus + "\n");
            writer.write("O-," + Ominus + "\n");

            writer.close();
        } catch(Exception e) {
            System.out.println("Error saving stock");
        }
    }

    public void readStock() {
        try {
            File file = new File("stock.txt");
            if(!file.exists()) {
                return;
            }
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                String blood = data[0];
                int amount = Integer.parseInt(data[1]);

                switch(blood) {
                    case "A+":
                        Book_Donation.Aplus = amount;
                        break;
                    case "A-":
                        Book_Donation.Aminus = amount;
                        break;
                    case "B+":
                        Book_Donation.Bplus = amount;
                        break;
                    case "B-":
                        Book_Donation.Bminus = amount;
                        break;
                    case "AB+":
                        Book_Donation.ABplus = amount;
                        break;
                    case "AB-":
                        Book_Donation.ABminus = amount;
                        break;
                    case "O+":
                        Book_Donation.Oplus = amount;
                        break;
                    case "O-":
                        Book_Donation.Ominus = amount;
                        break;
                }
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Error loading stock");
        }
    }


    // Patient File Handling
    public void savePatients(PatientData[] patients, int count) {
        try {
            FileWriter writer = new FileWriter("patients.txt");
            for(int i = 0; i < count; i++) {
                writer.write(patients[i].getId() + "," + patients[i].getName() + "," + patients[i].getBloodType() + "," + patients[i].getBagsNeeded() + "\n");
            }
            writer.close();
        } catch(Exception e) {
            System.out.println("Error saving patients");
        }
    }

    public void readPatients(PatientData[] patients, PatientManager manager) {
        try {
            File file = new File("patients.txt");
            if(!file.exists()) {
                return;
            }
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                PatientData patient = new PatientData();

                patient.setId(Integer.parseInt(data[0]));
                patient.setName(data[1]);
                patient.setBloodType(data[2]);
                patient.setBagsNeeded(Integer.parseInt(data[3]));
                patients[manager.count] = patient;

                manager.count++;
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Error loading patients");
        }
    }

    public void saveRequest(PatientData patient) {
        try {
            FileWriter writer = new FileWriter("requests.txt", true);
            writer.write(patient.getId() + "," + patient.getName() + "," + patient.getBloodType() + "," + patient.getBagsNeeded() + "\n");
            writer.close();
        } catch(Exception e) {
            System.out.println("Error saving request");
        }
    }

}