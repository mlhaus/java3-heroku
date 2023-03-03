package com.hauschildt.data_access;

import com.hauschildt.ch5.User;
import com.hauschildt.utilities.MyTwilio;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
            System.out.println("Database error - Get all users");
            System.out.println(e.getMessage());
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
    public void addUser2(String email, byte[] password) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "INSERT INTO users2 (email, password) VALUES (?, ?)")) {

                statement.setString(1, email);
                statement.setBytes(2, password);
                statement.executeUpdate();
            }
        } catch(SQLException e) {
            System.out.println("Database error - Add user 2");
            System.out.println(e.getMessage());
        }
    }

    public void authenticateUser2(String email, byte[] password) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "SELECT email FROM users2"
                                         + " WHERE email = ? AND password = ?")) {

                statement.setString(1, email);
                statement.setBytes(2, password);
                ResultSet results = statement.executeQuery();
                if (!results.next()) {
                    throw new RuntimeException("No matching login found.");
                }

                String theemail = results.getString("email");
                System.out.println("Found " + theemail + "!");
            }
        } catch(SQLException e) {
            System.out.println("Database error - Authenticate user 2");
            System.out.println(e.getMessage());
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
            statement.setString(6, "inactive");
            statement.execute();
            numRowsAffected = statement.getUpdateCount();
        } catch (SQLException e){
            System.out.println("Database error - Add user");
            System.out.println(e.getMessage());
        }
        return numRowsAffected;
    }

    public int getUserId(User user) {
        int userId = 0;
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            statement.setString(1, user.getEmail());
//            System.out.println(statement.toString());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
//            Statement statement = connection.createStatement();
//            String sql = String.format("SELECT * FROM users WHERE email = \"%s\";", user.getEmail());
//            System.out.println(sql);
//            ResultSet resultSet= statement.executeQuery(sql);
            resultSet.next();
            userId = resultSet.getInt("id");
            statement.close();
            resultSet.close();
        } catch (SQLException e){
            System.out.println("Database error - Get user by id");
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
        }
        return userId;
    }

    public int generate2FA(User user) {
        int numRowsAffected = 0;
        int userId = getUserId(user);
        System.out.println(userId);
        if(userId != 0) {
            try (Connection connection = getConnection()) {
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO 2fa_codes (user_id, code, method) values (?,?,?)");
                statement.setInt(1, userId);
                // https://stackoverflow.com/questions/51322750/generate-6-digit-random-number
                String randomCode = String.format("%06d", ThreadLocalRandom.current().nextInt(100000, 1000000));
                statement.setString(2, randomCode);
                statement.setString(3, "sms");
                statement.execute();
                numRowsAffected = statement.getUpdateCount();
                if(numRowsAffected == 1) {
                    MyTwilio myTwillio = new MyTwilio();
                    myTwillio.sendSMS(user.getPhone(), "Your verification code is " + randomCode);
                }
                statement.close();
            } catch (SQLException e){
                System.out.println("Database error - Add 2FA");
                System.out.println(e.getMessage());
            }
        }
        return numRowsAffected;
    }
}
