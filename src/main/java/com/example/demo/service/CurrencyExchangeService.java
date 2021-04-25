package com.example.demo.service;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CurrencyExchangeService {

    private final Map<ExchangeUnit, BigDecimal> exchangeRate;

    public CurrencyExchangeService() {
        exchangeRate = new HashMap<>();
        exchangeRate.put(ExchangeUnit.of("USD", "NRS"), BigDecimal.valueOf(111));
        exchangeRate.put(ExchangeUnit.of("IND", "NRS"), BigDecimal.valueOf(1.6));
    }

    public BigDecimal getExchangeRate(String from, String to) {
        ExchangeUnit exchangeUnit = ExchangeUnit.of(from, to);
        if (!exchangeRate.containsKey(exchangeUnit))
            throw new RuntimeException(String.format("There is not exchange rate defined from %s and to %s", from, to));
        return exchangeRate.get(exchangeUnit);
    }

    static class ExchangeUnit {
        String from;
        String to;

        public ExchangeUnit(String from, String to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ExchangeUnit that = (ExchangeUnit) o;
            return from.equals(that.from) && to.equals(that.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        public static ExchangeUnit of(String from, String to) {
            return new ExchangeUnit(from, to);
        }
    }

}
