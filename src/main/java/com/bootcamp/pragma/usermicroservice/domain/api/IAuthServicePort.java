package com.bootcamp.pragma.usermicroservice.domain.api;

import com.bootcamp.pragma.usermicroservice.domain.model.User;

public interface IAuthServicePort {
    void registerAuxWarehouse(User user);
    String login(String email, String password);
    void registerAdmin(User user);
    void registerClient(User user);
}
