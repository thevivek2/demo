package com.example.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class CurrencyExchangeServiceTest {

    private final CurrencyExchangeService service = new CurrencyExchangeService();

    @Test
    void givenValidExchangeUnitWhenGetExchangeRateShouldReturnExchangeRate() {
        BigDecimal exchangeRate = service.getExchangeRate("USD", "NRS");
        Assertions.assertThat(exchangeRate).isEqualTo(BigDecimal.valueOf(111));
    }

    @Test
    void givenInValidExchangeUnitWhenGetExchangeRateShouldReturnExchangeRate() {
        Assertions.assertThatThrownBy(() -> service.getExchangeRate("EURO", "USD"));
    }
}