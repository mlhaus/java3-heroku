package com.hauschildt.ch3and4;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private double heightInInches;
    private double weightInPounds;

    public static final String DEFAULT_FIRST_NAME = "John";
    public static final String DEFAULT_LAST_NAME = "Doe";
    public static final int DEFAULT_HEIGHT = 1;
    public static final double DEFAULT_WEIGHT = 1;

    public Person() {
        this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName) {
        this(firstName, DEFAULT_LAST_NAME, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, DEFAULT_HEIGHT, DEFAULT_WEIGHT);
    }

    public Person(String firstName, String lastName, int heightInInches, double weightInPounds) {
        setFirstName(firstName);
        setLastName(lastName);
        setHeightInInches(heightInInches);
        setWeightInPounds(weightInPounds);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.equals("")) {
            throw new IllegalArgumentException("First name is required");
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.equals("")) {
            throw new IllegalArgumentException("Last name required");
        }
        this.lastName = lastName;
    }

    public double getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(double heightInInches) {
        if(heightInInches <= 0) {
            throw new IllegalArgumentException("Height must be 1 or greater");
        }
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        if(weightInPounds <= 0) {
            throw new IllegalArgumentException("Weight must be 1 or greater");
        }
        this.weightInPounds = weightInPounds;
    }

    public double getBMI() {
        // Use BigDecimal
        // Formula weight (lb) / [height (in)]2 x 703
        double heightSquared = Math.pow(heightInInches, 2);
        double weightDivHeightSq = weightInPounds / heightSquared;
        double result = weightDivHeightSq * 703;
        BigDecimal result2 = BigDecimal.valueOf(result).setScale(1, BigDecimal.ROUND_HALF_EVEN);
        return result2.doubleValue();
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heightInInches=" + heightInInches +
                ", weightInPounds=" + weightInPounds +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        int result = this.lastName.compareToIgnoreCase(o.lastName);
        if(result == 0) {
            result = this.firstName.compareToIgnoreCase(o.firstName);
        }
        return result;
    }
}
