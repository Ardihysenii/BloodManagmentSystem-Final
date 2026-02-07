package com.bloodmanagement;

import com.bloodmanagement.ui.LoginUI;
import com.bloodmanagement.dao.DonationDAO;

public class Main {
    public static void main(String[] args) {

        LoginUI loginUI = new LoginUI();
        loginUI.showLogin();


        DonationDAO donationDAO = new DonationDAO();
        donationDAO.showDonorHistory(5);
    }
}