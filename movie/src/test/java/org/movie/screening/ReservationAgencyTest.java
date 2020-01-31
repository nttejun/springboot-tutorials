package org.movie.screening;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class ReservationAgencyTest {

    @Test
    public void createReservationObj() {
        Assertions.assertNotNull(
                new Movie(
                        Duration.ofMinutes(120),
                        Money.wons(10000),
                        new AmountDiscountPolicy(Money.wons(1000),
                                new SequenceDiscountPolicy(1),
                                new SequenceDiscountPolicy(10),
                                new PeriodDiscountPolicy(DayOfWeek.THURSDAY, LocalTime.of(6, 00), LocalTime.of(8, 25))
                        )
                ));
    }

    @Test
    public void getFeeNoneDiscountCase() {
        Movie 백두산 = new Movie(
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(Money.wons(1000),
                        new SequenceDiscountPolicy(1),
                        new SequenceDiscountPolicy(10),
                        new PeriodDiscountPolicy(DayOfWeek.THURSDAY, LocalTime.of(6, 00), LocalTime.of(8, 25))
                )
        );

        백두산.getFee();

    }

    @Test
    public void bigDecimal() {
        double v1 = 30.42;
        double v2 = 20;
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        Assertions.assertEquals(BigDecimal.valueOf(608.4000000000000341060513164848089218139648437500), b1.multiply(b2));
    }
}