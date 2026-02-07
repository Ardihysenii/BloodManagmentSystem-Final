package com.bloodmanagement.dao;

import com.bloodmanagement.util.DatabaseConnection;
import com.bloodmanagement.model.Donor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonorDAO {


    public void addDonor(Donor donor) {
        // Sigurohu që kjo procedurë ekziston në SQL (e kemi krijuar më herët)
        String sql = "{call sp_RegisterDonor(?, ?, ?, ?, ?, ?, ?, ?)}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, donor.getFirstName());
            stmt.setString(2, donor.getLastName());
            stmt.setString(3, donor.getGender());
            stmt.setDate(4, Date.valueOf(donor.getDateOfBirth()));
            stmt.setInt(5, donor.getBloodGroupID());
            stmt.setString(6, donor.getEmail());
            stmt.setString(7, donor.getPhoneNumber());
            stmt.setString(8, donor.getAddress());

            stmt.execute();
            System.out.println("✅ Dhuruesi u regjistrua me sukses në databazë!");

        } catch (SQLException e) {
            System.out.println("❌ Gabim SQL gjatë regjistrimit: " + e.getMessage());
        }
    }


    public List<Donor> getAllDonors() {
        List<Donor> donors = new ArrayList<>();
        String sql = "{call sp_GetAllDonors()}";

        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                donors.add(new Donor(
                        rs.getInt("DonorID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Gender"),
                        rs.getDate("DateOfBirth").toLocalDate(),
                        rs.getInt("BloodGroupID"),
                        rs.getString("Email"),
                        rs.getString("PhoneNumber"),
                        rs.getString("Address")
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë listimit: " + e.getMessage());
        }
        return donors;
    }


    public void deleteDonor(int donorId) {
        String sql = "{call sp_DeleteDonor(?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, donorId);
            stmt.execute();
            System.out.println("✅ Dhuruesi me ID " + donorId + " u fshi me sukses!");

        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë fshirjes: " + e.getMessage());
        }
    }


    public void updateDonor(int id, String emri, String mbiemri, String tel) {
        String sql = "{call sp_UpdateDonor(?, ?, ?, ?)}";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, id);
            stmt.setString(2, emri);
            stmt.setString(3, mbiemri);
            stmt.setString(4, tel);

            stmt.execute();
            System.out.println("✅ Të dhënat e dhuruesit u përditësuan me sukses!");

        } catch (SQLException e) {
            System.out.println("❌ Gabim gjatë përditësimit: " + e.getMessage());
        }
    }
}