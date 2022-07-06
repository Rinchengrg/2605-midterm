package com.donor.donormanagement;

public class DonationDetail {
    private String donationType;
    private String donatedAt;

    public DonationDetail(String donationType, String donatedAt) {
        this.donationType = donationType;
        this.donatedAt = donatedAt;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }

    public String getDonatedAt() {
        return donatedAt;
    }

    public void setDonatedAt(String donatedAt) {
        this.donatedAt = donatedAt;
    }
}
