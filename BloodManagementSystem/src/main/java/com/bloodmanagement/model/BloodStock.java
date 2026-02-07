package com.bloodmanagement.model;

import java.time.LocalDateTime;

public class BloodStock {
    private String groupName;
    private double quantity;
    private LocalDateTime lastUpdated;


    public BloodStock(String groupName, double quantity, LocalDateTime lastUpdated) {
        this.groupName = groupName;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }


    public String getGroupName() { return groupName; }
    public double getQuantity() { return quantity; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
}