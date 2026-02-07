package com.bloodmanagement.service;

import com.bloodmanagement.dao.DonorDAO;
import com.bloodmanagement.model.Donor;
import java.util.List;

public class DonorService {
    private DonorDAO donorDAO = new DonorDAO();

    public void registerDonor(Donor donor) {

        if (donor != null) {
            donorDAO.addDonor(donor);
        } else {
            System.out.println("❌ Gabim: Dhuruesi nuk ka të dhëna!");
        }
    }

    public List<Donor> getAllDonors() {
        return donorDAO.getAllDonors();
    }

    public void removeDonor(int id) {
        donorDAO.deleteDonor(id);
    }

    public void updateExistingDonor(int id, String emri, String mbiemri, String tel) {
        if (id <= 0) {
            System.out.println("❌ ID Invalide!");
            return;
        }
        donorDAO.updateDonor(id, emri, mbiemri, tel);
    }
}