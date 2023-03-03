package com.hauschildt.ch5;

import org.apache.commons.text.StringEscapeUtils;

public class User {
    private int id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private char[] password;
    private String status;

    public User() {
        this(0,"John","Doe","john@example.com","555-555-5555", "Passw0rd".toCharArray(), "inactive");
    }

    public User(int id, String first_name, String last_name, String email, String phone, char[] password, String status) {
        setId(id);
        setFirst_name(first_name);
        setLast_name(last_name);
        setEmail(email);
        setPhone(phone);
        setPassword(password);
        setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if(id < 0) {
            throw new IllegalArgumentException("Invalid User ID");
        }
        if(this.id > 0) {
            throw new IllegalArgumentException("User ID cannot be modified");
        }
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        // https://stackoverflow.com/a/2385811/6629315
        if(first_name.length() == 0) {
            throw new IllegalArgumentException("First name required");
        }
        if(first_name.length() > 50) {
            throw new IllegalArgumentException("First name cannot have more than 50 characters");
        }

        this.first_name = StringEscapeUtils.escapeHtml4(first_name);
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        // https://stackoverflow.com/a/2385811/6629315
        if(last_name.length() == 0) {
            throw new IllegalArgumentException("Last name required");
        }
        if(last_name.length() > 50) {
            throw new IllegalArgumentException("Last name cannot have more than 50 characters");
        }
        this.last_name = StringEscapeUtils.escapeHtml4(last_name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        // https://stackoverflow.com/a/202528/6629315
        // https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input/email#basic_validation
        if(!email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$")) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        // https://regex101.com/library/cFEhad
        if(!phone.matches("^(1\\s?)?(\\d{3}|\\(\\d{3}\\))[\\s\\-]?\\d{3}[\\s\\-]?\\d{4}$")) {
            throw new IllegalArgumentException("Invalid US phone number");
        }
        // https://stackoverflow.com/questions/10372862/java-string-remove-all-non-numeric-characters-but-keep-the-decimal-separator
        phone = phone.charAt(0) == '1' ? phone.substring(1,phone.length()) : phone;
        this.phone = phone.replaceAll("[^\\d.]", "");
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        // https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
        // https://stackoverflow.com/questions/7655127/how-to-convert-a-char-array-back-to-a-string
        // https://stackoverflow.com/questions/5317320/regex-to-check-string-contains-only-hex-characters
        String passwordStr = String.valueOf(password);
        if(passwordStr.length() != 64 && !passwordStr.matches("^[0-9A-F]+$")) {
            if(!passwordStr.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$")) {
                throw new IllegalArgumentException("Password must include a minimum of eight characters, at least one uppercase letter, one lowercase letter, and one number");
            }
        }
        this.password = passwordStr.toCharArray();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if(!status.equals("active") && !status.equals("inactive") && !status.equals("locked")) {
            throw new IllegalArgumentException("Invalid status");
        }
        this.status = status;
    }
}