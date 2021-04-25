package com.example.demo.service;

import com.example.demo.respository.Status;
import com.example.demo.respository.UserEntity;
import com.example.demo.respository.UserJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private CurrencyExchangeService currencyExchangeService;
    private UserJpaRepository repository;
    private UserService userService;

    @BeforeEach
    void setUp() {
        repository = mock(UserJpaRepository.class);
        currencyExchangeService = mock(CurrencyExchangeService.class);
        userService = new UserService(repository, currencyExchangeService);
    }

    @Test
    void givenValidUser_WhenCreate_ShouldEnrichUserAndSave() {

        //given
        UserEntity user = validUser();
        when(repository.save(user)).thenReturn(user);

        //when
        UserEntity createdUser = userService.create(user);

        //then
        assertThat(createdUser.getStatus()).isEqualTo(Status.NEW);
        assertThat(createdUser.getBalance()).isEqualTo(BigDecimal.ZERO);
        assertThat(createdUser.getCurrency()).isEqualTo("USD");
        assertThat(createdUser.getUuid()).isNotEmpty();

        verify(repository).save(user);
    }


    private static UserEntity validUser() {
        UserEntity user = new UserEntity();
        user.setMobileNumber("2222222");
        user.setAccountNumber("3333333");
        return user;
    }
}