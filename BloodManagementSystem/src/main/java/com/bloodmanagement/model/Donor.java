package com.bloodmanagement.model;

import java.time.LocalDate;

public class Donor {
    private int donorID;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dateOfBirth;
    private int bloodGroupID;
    private String email;
    private String phoneNumber;
    private String address;


    public Donor(int donorID, String firstName, String lastName, String gender,
                 LocalDate dateOfBirth, int bloodGroupID, String email,
                 String phoneNumber, String address) {
        this.donorID = donorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroupID = bloodGroupID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public Donor(String firstName, String lastName, String gender, LocalDate dateOfBirth,
                 int bloodGroupID, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroupID = bloodGroupID;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public int getDonorID() { return donorID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getGender() { return gender; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public int getBloodGroupID() { return bloodGroupID; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
}