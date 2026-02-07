package com.bloodmanagement.service;

import com.bloodmanagement.dao.BloodRequestDAO;

public class BloodRequestService {
    private BloodRequestDAO requestDAO = new BloodRequestDAO();

    public void submitRequest(int hospitalId, int groupId, int units) {
        if (units <= 0) return;
        requestDAO.createRequest(hospitalId, groupId, units);
    }

    public void addHospital(String name, String contact, String location) {
        if (name == null || name.isEmpty()) return;
        requestDAO.registerHospital(name, contact, location);
    }

    public void showHospitals() {
        requestDAO.listAllHospitals();
    }


    public void showAllRequestsForAdmin() {
        requestDAO.listAllRequestsForAdmin();
    }


    public void showPendingForGroup(int groupId) {
        requestDAO.listPendingRequests(groupId);
    }

    public void handleDonorAccept(int requestId) {
        requestDAO.acceptRequest(requestId);
    }
}