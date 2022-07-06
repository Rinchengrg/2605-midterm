package com.donor.donormanagement.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum BloodType {
    O_POSITIVE("O+"),
    O_NEGATIVE("O-"),
    A_POSITIVE("A+"),
    A_NEGATIVE("A-"),
    B_POSITIVE("B+"),
    B_NEGATIVE("B-"),
    AB_POSITIVE("AB+"),
    AB_NEGATIVE("AB-");

    private static final Map<String, BloodType> lookup = new HashMap<>();

    static {
        for (BloodType bloodType : BloodType.values()) {
            lookup.put(bloodType.getBloodGroup(), bloodType);
        }
    }

    BloodType(String bloodGroup) {
        this.bloodGroup=bloodGroup;
    }

    private String bloodGroup;

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public static List<String> listOfBloodGroups(){
        return Arrays.stream(BloodType.values()).map(bloodType -> bloodType.getBloodGroup()).toList();
    }

    public static BloodType get(String bloodGroup) {
        return lookup.get(bloodGroup);
    }
}
