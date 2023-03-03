package com.hauschildt.data_access;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthenticationTest {
    public static byte[] hashPassword(String password) {
        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        byte[] passwordHash;
        try {
            passwordHash = MessageDigest.getInstance("SHA-256").digest(passwordBytes);
        } catch (NoSuchAlgorithmException e) {
            // It should be impossible to get here, since SHA-256 is
            // a standard algorithm supported by all Java runtimes.
            throw new RuntimeException(e);
        }
        return passwordHash;
    }

    public static void main(String[] args) {
        // https://stackoverflow.com/questions/70690430/setting-up-user-authentication-in-java-with-jdbc
//        byte[] hashedPassword = hashPassword("H@wk3yes");
        UserDAO_MySQL dao = new UserDAO_MySQL();
//        dao.addUser2("marc.hauschildt@gmail.com",hashedPassword);
        byte[] hashedPassword2 = hashPassword("H@wk3yes");
        dao.authenticateUser2("marc.hauschildt@gmail.com",hashedPassword2);
    }
}
