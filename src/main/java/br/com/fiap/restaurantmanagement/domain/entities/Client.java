package br.com.fiap.restaurantmanagement.domain.entities;

public class Client {

    private String name;
    private String email;
    private String phoneNumber;

    public Client(String name, String email, String phoneNumber) {

        validateValues(name, email, phoneNumber);

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void validateValues(String name, String email, String phoneNumber) {
        validateMandatoryValues(name, email, phoneNumber);
    }

    private void validateMandatoryValues(String name, String email, String phoneNumber) {
        if (name == null || email == null || phoneNumber == null) {
            throw new IllegalArgumentException("os campos não podem ser nulos");
        }
        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("todas as informações do cliente devem ser preenchidas");
        }
    }

    private void validateName(String name) {
        
    }

    private void validateEmail(String email) {

    }

    private void validatePhoneNumber(String phoneNumber) {

    }
}