package com.company;
import java.util.Scanner;
public class DonorManager {
    // تبع الفايل
    //عشان اول متعمل اوبجكت يحمل كل البيانات الي في الملف ل الاراي
    FileHandling file = new FileHandling();
    public DonorManager() {
        file.readDonors(donors, this);
    }
    //---------------
    Scanner input = new Scanner(System.in);
    DonorData[] donors =new DonorData[100];
    int count=0;
    public void addDonor(){
        DonorData donor=new DonorData();
        int id;
            System.out.print("Enter Donor's ID: ");
            if(input.hasNextInt()) {
                id = input.nextInt();
                boolean exist = false;
                for (int i = 0; i < count; i++) {
                    if (donors[i].getId() == id) {
                        exist = true;
                        break;
                    }
                }
                if (exist) {
                    System.out.println("ID already exists ");
                    return;
                } else {
                    donor.setId(id);
                }
            } else{
                System.out.println("invalid ID");
                input.next();
                return;
            }
        input.nextLine();
        System.out.print("please enter a Name: ");
        donor.setName(input.nextLine());
        int age;
            System.out.print("Enter Age: ");
            if(input.hasNextInt()) {
                age = input.nextInt();
                if(age >= 18 && age <= 60) {
                    donor.setAge(age);
                } else {
                    System.out.println("Age must be between 18 and 60");
                    return;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                input.next();
            }
        input.nextLine();
        String bloodType;
        //عشان يدخل فصيله دم من دول  ميكتبش اي حاجه
            System.out.print("please enter the blood type: ");
            bloodType=input.nextLine().toUpperCase().trim();
            if(
                bloodType.equals("A+")||bloodType.equals("A-") ||
                bloodType.equals("B+")|| bloodType.equals("B-")||
                bloodType.equals("AB+")||bloodType.equals("AB-")||
                bloodType.equals("O+")||bloodType.equals("O-")
            ) {
            donor.setBloodType(bloodType);
            }
            else{
            System.out.println("invalid a blood type");
            return;
            }
        System.out.print("please enter a phone number: ");
        donor.setPhone(input.nextLine());
        System.out.print("please enter a address: ");
        donor.setAddress(input.nextLine());
        int months;
            System.out.print("How many months since your last donation? ");
            if(input.hasNextInt()) {
                months = input.nextInt();
                input.nextLine();
                if(months >= 6) {
                    donor.setLastDonation(months);
                } else {
                    System.out.println("You cannot donate before 6 months");
                    return;
                }
            } else {
                System.out.println("Invalid input");
                input.next();
            }
        donors[count]=donor;
        count++;
        System.out.println("Donor Added Successfully!");

        //دي عشان يحفظ الراجل في الملف
        file.saveDonors(donors, count);
        //-------
    }

    public void showDonors() {
        if(count == 0) {
            System.out.println("No donors found");
            return;
        }

        for(int i = 0; i < count; i++) {
            System.out.println("\n===== Donor " + (i + 1) + " =====");
            System.out.println("ID: " + donors[i].getId());
            System.out.println("Name: " + donors[i].getName());
            System.out.println("Age: " + donors[i].getAge());
            System.out.println("Blood Type: " + donors[i].getBloodType());
            System.out.println("Phone: " + donors[i].getPhone());
            System.out.println("Last Donation: " + donors[i].getLastDonation());
        }
    }

    public void deleteDonor() {
        System.out.print("Enter Donor ID To Delete: ");
        if(input.hasNextInt()) {
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (donors[i].getId() == id) {
                    for (int j = i; j < count - 1; j++) {
                        donors[j] = donors[j + 1];
                    }
                    donors[count - 1] = null;
                    count--;
                    found = true;
                    System.out.println("Donor Deleted Successfully");

                    //دي عشان نشيل الراجل من الملف
                    file.saveDonors(donors, count);
                    //-----
                    break;
                }
            }
            if (!found) {
                System.out.println("Donor not found");
            }
        }
        else{
            System.out.println("Invalid input");
        }
    }
    public void searchDonor() {
        System.out.print("Enter Donor ID: ");
        if(input.hasNextInt()) {
            int id = input.nextInt();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (donors[i].getId() == id) {
                    System.out.println("\nDonor Found");
                    System.out.println("ID: " + donors[i].getId());
                    System.out.println("Name: " + donors[i].getName());
                    System.out.println("Age: " + donors[i].getAge());
                    System.out.println("Blood Type: " + donors[i].getBloodType());
                    System.out.println("Phone: " + donors[i].getPhone());
                    System.out.println("Last Donation: " + donors[i].getLastDonation());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Donor not found");
            }
        }
        else{
            System.out.println("Invalid input");
        }
    }
    public void viewMyData() {
        System.out.print("Enter Your ID: ");
        int id = input.nextInt();
        boolean found = false;
        for(int i = 0; i < count; i++) {
            if(donors[i].getId() == id) {
                System.out.println("\n===== Your Data =====");
                System.out.println("ID: " + donors[i].getId());
                System.out.println("Name: " + donors[i].getName());
                System.out.println("Age: " + donors[i].getAge());
                System.out.println("Blood Type: " + donors[i].getBloodType());
                System.out.println("Phone: " + donors[i].getPhone());
                System.out.println("Last Donation: " + donors[i].getLastDonation());
                found = true;
                break;
            }
        }
        if(!found) {
            System.out.println("Data not found");
        }
    }
}