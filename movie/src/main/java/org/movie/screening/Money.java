package org.movie.screening;

import java.math.BigDecimal;

public class Money {
    private final BigDecimal amount;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money wons(double amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

    public static Money wons(long amount) {
        return new Money(BigDecimal.valueOf(amount));
    }

}
