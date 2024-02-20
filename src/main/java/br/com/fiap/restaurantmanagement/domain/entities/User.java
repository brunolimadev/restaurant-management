package br.com.fiap.restaurantmanagement.domain.entities;

import br.com.fiap.restaurantmanagement.domain.enumerators.AccessRole;

public class User {

    private String name;
    private String email;
    private AccessRole accessRole;

    public User(String name, String email, AccessRole accessRole) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || accessRole == null)
            throw new IllegalArgumentException("Name, email and access role are required");

        this.name = name;
        this.email = email;
        this.accessRole = accessRole;
    }
}
