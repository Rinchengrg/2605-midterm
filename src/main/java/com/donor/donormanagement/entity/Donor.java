package com.donor.donormanagement.entity;

import com.donor.donormanagement.DonationDetail;
import com.donor.donormanagement.enums.BloodType;
import com.donor.donormanagement.enums.Gender;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Donor {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private BloodType bloodType;
    private String email;
    private String mobile;
    private String address;
    private String notes;

    public List<DonationDetail> bloodDonationList = new ArrayList<>();

    public List<DonationDetail> plasmaDonationList = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public static List<Donor> initialize() {
        List<String> firstNames = List.of("Olivia", "Charlotte", "Isla", "Oliver");
        List<String> lastNames = List.of("Smith", "Jones", "Martin", "Williams");
        List<Gender> genders = List.of(Gender.MALE, Gender.FEMALE, Gender.MALE, Gender.FEMALE);
        List<BloodType> bloodTypes = List.of(BloodType.A_NEGATIVE, BloodType.A_POSITIVE, BloodType.B_POSITIVE, BloodType.B_NEGATIVE);
        List<String> emails = List.of("olivia.smith@gmail.com", "charlotte.jones@gmail.com", "isla.martin@gmail.com", "oliver.williams@gmail.com");
        List<LocalDate> dbos = List.of(
                LocalDate.of(1990, 1, 2),
                LocalDate.of(1990, 1, 2),
                LocalDate.of(1990, 1, 2),
                LocalDate.of(1990, 1, 2));
        List<String> mobiles = List.of("(03) 5301 5654", "(08) 9091 6949", "(08) 8743 1770", "(02) 6271 3623");
        List<String> addresses = List.of(
                "75 Loris Way, Minigin, 6312 Australia",
                "76 Moores Drive, Olympic Dam, 5725 Australia",
                "94 Kopkes Road, Mount Emu, 3351 Australia",
                "65 Tennyson Road, Greenacre, 2190 Australia");
        List<Donor> donorList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Donor donor = new Donor();
            donor.setFirstName(firstNames.get(i));
            donor.setLastName(lastNames.get(i));
            donor.setDateOfBirth(dbos.get(i));
            donor.setGender(genders.get(i));
            donor.setBloodType(bloodTypes.get(i));
            donor.setEmail(emails.get(i));
            donor.setMobile(mobiles.get(i));
            donor.setAddress(addresses.get(i));
            donor.setNotes("No Remarks");
            donorList.add(donor);
        }
        return donorList;
    }
}
