package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDetailDTO {

    private LocalDate expenseDate;
    private BigDecimal amount;

    public ExpenseDetailDTO(BigDecimal amount, LocalDate expenseDate) {
        this.amount = amount;
        this.expenseDate = expenseDate;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
