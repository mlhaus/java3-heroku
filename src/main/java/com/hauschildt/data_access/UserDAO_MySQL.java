package com.hauschildt.data_access;

import com.hauschildt.ch5.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO_MySQL implements DAO_MySQL<User> {
    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection connection = getConnection()){
            if(connection.isValid(2)){
                Statement statement = connection.createStatement();
                ResultSet resultSet= statement.executeQuery("SELECT * FROM users;");
                while(resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String password = resultSet.getString("password");
                    String status = resultSet.getString("status");
                    User user = new User(id, firstName, lastName, email, phone, password.toCharArray(), status);
                    users.add(user);
                }
            }
        }catch (SQLException e){
            System.out.println("Website unavailable");
        }
        return users;
    }

    // https://medium.com/javarevisited/handling-passwords-in-java-swing-and-sql-f0e52002a04c
    public String encryptPass(String password) {
        try {
            //retrieve instance of the encryptor of SHA-256
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");//retrieve bytes to encrypt
            byte[] encodedhash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodedhash.length);//perform encryption
            for (int i = 0; i < encodedhash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodedhash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
            // return encrypted value
            return encryptionValue.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Invalid password encryption algorithm");
        }
    }

    public int addUser(User user) {
        int numRowsAffected = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (first_name, last_name, email, phone, password, status) " +
                    "values (?,?,?,?,?,?)");
            statement.setString(1, user.getFirst_name());
            statement.setString(2, user.getLast_name());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, encryptPass(user.getPassword().toString()));
            statement.execute();
            numRowsAffected = statement.getUpdateCount();
        } catch (SQLException e){
            System.out.println("Database error");
            System.out.println(e.getMessage());
        }
        return numRowsAffected;
    }
}
