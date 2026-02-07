package com.bloodmanagement.dao;

import com.bloodmanagement.util.DatabaseConnection;
import com.bloodmanagement.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String username, String password) {
        String sql = "SELECT * FROM AppUsers WHERE Username = ? AND Password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getObject("BloodGroupID") != null ? rs.getInt("BloodGroupID") : null
                );
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim ne Login: " + e.getMessage());
        }
        return null;
    }

    public void registerUser(String username, String password, int roleId, Integer bloodGroupId) {
        String sql = "INSERT INTO AppUsers (Username, Password, RoleID, BloodGroupID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, roleId);

            if (bloodGroupId != null) {
                pstmt.setInt(4, bloodGroupId);
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("✅ Perdoruesi u regjistrua!");
        } catch (SQLException e) {
            System.out.println("❌ Gabim ne regjistrim: " + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM AppUsers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getInt("RoleID"),
                        rs.getObject("BloodGroupID") != null ? rs.getInt("BloodGroupID") : null
                ));
            }
        } catch (SQLException e) {
            System.out.println("❌ Gabim ne listim: " + e.getMessage());
        }
        return users;
    }

    public void deleteUser(int id) {
        String sql = "DELETE FROM AppUsers WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Perdoruesi u fshi!");
        } catch (SQLException e) {
            System.out.println("❌ Gabim ne fshirje: " + e.getMessage());
        }
    }
}