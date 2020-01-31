package org.movie.screening;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class PeriodDiscountPolicy implements DiscountCondition {
    public PeriodDiscountPolicy(DayOfWeek thursday, LocalTime startTime, LocalTime endTime) {
    }
}
