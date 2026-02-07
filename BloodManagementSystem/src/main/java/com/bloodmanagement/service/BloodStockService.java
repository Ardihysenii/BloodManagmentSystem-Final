package com.bloodmanagement.service;

import com.bloodmanagement.dao.BloodStockDAO;
import com.bloodmanagement.model.BloodStock;
import java.util.List;

public class BloodStockService {
    private BloodStockDAO stockDAO = new BloodStockDAO();

    public List<BloodStock> getFullStock() {
        return stockDAO.getStockStatus();
    }
}