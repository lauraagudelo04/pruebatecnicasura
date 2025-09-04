package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class ExpenseGlobalResponseDTO {

    private BigDecimal total;
    private BigDecimal totalWithIva;
    private List<ExpenseResponseDTO> expenses;

    public ExpenseGlobalResponseDTO(BigDecimal total, BigDecimal totalWithIva, List<ExpenseResponseDTO> expenses) {
        this.total = total;
        this.totalWithIva = totalWithIva;
        this.expenses = expenses;
    }

    public ExpenseGlobalResponseDTO() {

    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTotalWithIva() {
        return totalWithIva;
    }

    public void setTotalWithIva(BigDecimal totalWithIva) {
        this.totalWithIva = totalWithIva;
    }

    public List<ExpenseResponseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseResponseDTO> expenses) {
        this.expenses = expenses;
    }
}
