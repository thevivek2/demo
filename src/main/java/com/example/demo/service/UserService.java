package com.example.demo.service;

import com.example.demo.respository.Status;
import com.example.demo.respository.UserEntity;
import com.example.demo.respository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class UserService {

    private final UserJpaRepository repository;
    private final CurrencyExchangeService currencyExchangeService;

    @Autowired
    public UserService(UserJpaRepository repository, CurrencyExchangeService currencyExchangeService) {
        this.repository = repository;
        this.currencyExchangeService = currencyExchangeService;
    }

    public UserEntity create(UserEntity user) {
        enrich(user);
        return repository.save(user);
    }

    private void enrich(UserEntity user) {
        if (user.getBalance() == null)
            user.setBalance(BigDecimal.ZERO);
        if (user.getCurrency() == null)
            user.setCurrency("USD");
        user.setStatus(Status.NEW);
        user.setUuid(UUID.randomUUID().toString());
    }
}
