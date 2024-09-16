package com.bootcamp.pragma.usermicroservice.domain.model;

import java.util.Date;

public class UserBuilder {
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Date dateBirth;
    private String password;
    private Role role;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserBuilder setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(id, firstname, lastname, email, phone, dateBirth, password, role);
    }
}
