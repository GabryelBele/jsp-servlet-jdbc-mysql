package com.makiesse.registration.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.makiesse.registration.config.ConnectionFactory;
import com.makiesse.registration.dao.UserDao;
import com.makiesse.registration.model.User;

public class UserDaoImpl implements UserDao {

    public boolean registerUser(User user) {
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pst = connection.prepareStatement(
                     "INSERT INTO users (username, useremail, userpassword, usermobile) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, user.getUsername());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getMobile());

            int rowCount = pst.executeUpdate();

            return rowCount > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User authenticateUser(String email, String password) {
        User user = null;

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement pst = connection
                     .prepareStatement("SELECT * FROM users WHERE useremail = ? AND userpassword = ?")) {

            pst.setString(1, email);
            pst.setString(2, password);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setEmail(rs.getString("useremail"));
                    user.setPassword(rs.getString("userpassword"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
