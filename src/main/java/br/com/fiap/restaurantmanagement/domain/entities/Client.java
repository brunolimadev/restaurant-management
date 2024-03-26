package br.com.fiap.restaurantmanagement.domain.entities;

public class Client {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;

    public Client(String name, String email, String phoneNumber) {

        validateValues(name, email);

        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Client(Long id, String name, String email, String phoneNumber) {

        validateValues(name, email);

        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    private void validateValues(String name, String email) {
        validateMandatoryValues(name, email);
    }

    private void validateMandatoryValues(String name, String email) {
        if (name == null || email == null) {
            throw new IllegalArgumentException("os campos não podem ser nulos");
        }
        if (name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("todas as informações do cliente devem ser preenchidas");
        }
    }

}