package com.bloodmanagement.model;

import java.time.LocalDateTime;

public class BloodRequest {
    private int requestId;
    private int hospitalId;
    private int bloodGroupId;
    private int requestedUnits;
    private String urgencyLevel;
    private LocalDateTime requestDate;
    private String status;


    public BloodRequest() {}


    public BloodRequest(int hospitalId, int bloodGroupId, int requestedUnits, String urgencyLevel) {
        this.hospitalId = hospitalId;
        this.bloodGroupId = bloodGroupId;
        this.requestedUnits = requestedUnits;
        this.urgencyLevel = urgencyLevel;
        this.status = "PENDING";
    }


    public int getRequestId() { return requestId; }
    public int getHospitalId() { return hospitalId; }
    public int getBloodGroupId() { return bloodGroupId; }
    public int getRequestedUnits() { return requestedUnits; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public LocalDateTime getRequestDate() { return requestDate; }
    public String getStatus() { return status; }


    public void setRequestId(int requestId) { this.requestId = requestId; }
    public void setHospitalId(int hospitalId) { this.hospitalId = hospitalId; }
    public void setBloodGroupId(int bloodGroupId) { this.bloodGroupId = bloodGroupId; }
    public void setRequestedUnits(int requestedUnits) { this.requestedUnits = requestedUnits; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }
    public void setRequestDate(LocalDateTime requestDate) { this.requestDate = requestDate; }
    public void setStatus(String status) { this.status = status; }
}