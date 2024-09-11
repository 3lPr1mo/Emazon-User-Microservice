package com.bootcamp.pragma.usermicroservice.domain.spi;

public interface IAuthPersistencePort {
    String encodePassword(String password);
}
