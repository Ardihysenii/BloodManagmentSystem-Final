package com.bloodmanagement.dao;

import com.bloodmanagement.util.DatabaseConnection;
import java.sql.*;

public class BloodRequestDAO {


    public void createRequest(int hospitalId, int groupId, int units) {
        String sql = "{call sp_AddBloodRequest(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, hospitalId);
            stmt.setInt(2, groupId);
            stmt.setInt(3, units);
            stmt.execute();
            System.out.println("✅ Kërkesa u dërgua! Statusi: PENDING (Pret dhuruesin)");
        } catch (SQLException e) {
            System.out.println("❌ Gabim SQL: " + e.getMessage());
        }
    }


    public void listAllRequestsForAdmin() {
        String sql = "{call sp_GetAllRequests}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n--- 📋 MONITORIMI I KËRKESAVE (ADMIN) ---");
            System.out.println(String.format("%-5s %-18s %-10s %-7s %-12s", "ID", "Spitali", "Grupi", "Qese", "Statusi"));
            System.out.println("------------------------------------------------------------------");

            while (rs.next()) {
                String status = rs.getString("Status");
                String statusDisplay = status.equalsIgnoreCase("COMPLETED") ? "✅ Kryer" : "⏳ Në Pritje";

                System.out.println(String.format("%-5d %-18s %-10s %-7d %-12s",
                        rs.getInt("RequestID"),
                        rs.getString("HospitalName"),
                        rs.getString("GroupName"),
                        rs.getInt("RequestedUnits"),
                        statusDisplay));
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim SQL: " + e.getMessage());
        }
    }


    public void listPendingRequests(int bloodGroupId) {
        String sql = "SELECT r.RequestID, h.HospitalName, bg.GroupName, r.RequestedUnits " +
                "FROM BloodRequests r " +
                "JOIN Hospitals h ON r.HospitalID = h.HospitalID " +
                "JOIN BloodGroups bg ON r.BloodGroupID = bg.BloodGroupID " +
                "WHERE r.BloodGroupID = ? AND r.Status = 'PENDING'";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, bloodGroupId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- 📢 KËRKESA URGJENTE PËR GRUPIN TËND ---");
            System.out.println(String.format("%-5s %-20s %-10s %-10s", "ID", "Spitali", "Grupi", "Qese"));
            System.out.println("-----------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(String.format("%-5d %-20s %-10s %-10d",
                        rs.getInt("RequestID"),
                        rs.getString("HospitalName"),
                        rs.getString("GroupName"),
                        rs.getInt("RequestedUnits")));
            }
            if (!found) {
                System.out.println("Nuk ka kërkesa të reja për grupin tuaj.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim: " + e.getMessage());
        }
    }


    public void acceptRequest(int requestId) {
        String sql = "{call sp_AcceptBloodRequest(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, requestId);
            stmt.execute();
            System.out.println("❤️ Faleminderit! Kërkesa u pranua me sukses.");
        } catch (SQLException e) {
            System.out.println("❌ Gabim: " + e.getMessage());
        }
    }


    public void registerHospital(String name, String contact, String location) {
        String sql = "{call sp_RegisterHospital(?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, contact);
            stmt.setString(3, location);
            stmt.execute();
            System.out.println("✅ Spitali '" + name + "' u regjistrua me sukses!");
        } catch (SQLException e) {
            System.out.println("❌ Gabim SQL: " + e.getMessage());
        }
    }


    public void listAllHospitals() {
        String sql = "SELECT HospitalID, HospitalName, Location FROM Hospitals";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- LISTA E SPITALEVE ---");
            System.out.println(String.format("%-5s %-25s %-15s", "ID", "Emri", "Qyteti"));
            System.out.println("-----------------------------------------------------------");

            while (rs.next()) {
                System.out.println(String.format("%-5d %-25s %-15s",
                        rs.getInt("HospitalID"),
                        rs.getString("HospitalName"),
                        rs.getString("Location")));
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë listimit: " + e.getMessage());
        }
    }
}