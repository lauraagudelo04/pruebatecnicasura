package com.suraprueba.travelexpenses.util;

import java.math.BigDecimal;
import java.util.Optional;

public class ExpenseCalculator {

    private static final BigDecimal IVA_RATE = new BigDecimal("0.19"); // 19% IVA
    private static final BigDecimal LIMIT = new BigDecimal("1000000"); //Limit to know who assumes the expense

    public static BigDecimal calculateTotalWithIva(BigDecimal amount){
        return Optional.ofNullable(amount)
                .filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
                .map(a-> a.add(a.multiply(IVA_RATE)))
                .orElseThrow(()->new IllegalArgumentException("El valor a agregar IVA no puede ser nulo"));
    }

    public static String whoAssumes(BigDecimal totalAmount){
        return Optional.ofNullable(totalAmount)
                .filter(a -> a.compareTo(BigDecimal.ZERO) > 0)
                .map(amount -> amount.compareTo(LIMIT) > 0 ? "Company" : "Employee")
                .orElseThrow(()->new IllegalArgumentException("El valor para determinar quien asume el gasto no puede ser nulo ni cero"));
    }
}
