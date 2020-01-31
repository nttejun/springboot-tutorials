package org.movie.screening;

import lombok.Getter;
import java.time.Duration;

@Getter
public class Movie {
    private int fee;

    public Movie(Duration movieTime, Money startTime, AmountDiscountPolicy endTime) {
    }

}
