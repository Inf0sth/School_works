package Examen_2.Classes;

import java.util.regex.Pattern;

public class User {
    private String fullName;
    private String address;
    private String phoneNumber;
    private String email;

    // Constructor
    public User(String fullName, String address, String phoneNumber, String email) throws UserException {
        setFullName(fullName);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setEmail(email);
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    // Setters with validations
    public void setFullName(String fullName) throws UserException {
        if (fullName == null || fullName.isEmpty()) {
            throw new UserException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public void setAddress(String address) throws UserException {
        // Regex for the address
        String regex = ".*, [A-Z]{3,4}$";
        
        // Verify if the direction match with the regex
        if (!Pattern.matches(regex, address)) {
            throw new UserException("Invalid address format");
        }
        
        this.address = address;
    }
    
    public void setPhoneNumber(String phoneNumber) throws UserException {
        // Regex for validate the phone number
        String regex = "\\+52\\d{10}";
        if (!Pattern.matches(regex, phoneNumber)) {
            throw new UserException("Invalid phone number format");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) throws UserException {
        // Regex for valid the email
        String regex = "^[A-Za-z0-9._%+-]+@gmail\\.com$";
        if (!Pattern.matches(regex, email)) {
            throw new UserException("Invalid email format. Only Gmail accounts are allowed.");
        }
        this.email = email;
    }
}

