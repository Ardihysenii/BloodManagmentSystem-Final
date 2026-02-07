package com.bloodmanagement.service;

import com.bloodmanagement.dao.DonationDAO;

public class DonationService {
    private DonationDAO donationDAO = new DonationDAO();

    public void registerNewDonation(int donorId) {

        donationDAO.addDonation(donorId);
    }
}