package org.movie.screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AmountDiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();
    private Money discountAmount;
    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions){
        this.discountAmount = discountAmount;
        this.conditions = Arrays.asList(conditions);
    }
}
