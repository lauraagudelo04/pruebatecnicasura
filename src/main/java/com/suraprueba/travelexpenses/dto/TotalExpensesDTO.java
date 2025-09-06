package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;

public class TotalExpensesDTO {

    private BigDecimal totalWithoutIva;
    private BigDecimal totalWithIva;

    public TotalExpensesDTO(BigDecimal totalWithoutIva, BigDecimal totalWithIva) {
        this.totalWithoutIva = totalWithoutIva;
        this.totalWithIva = totalWithIva;
    }

    public TotalExpensesDTO(){

    }
    public BigDecimal getTotalWithoutIva() {
        return totalWithoutIva;
    }

    public void setTotalWithoutIva(BigDecimal totalWithoutIva) {
        this.totalWithoutIva = totalWithoutIva;
    }

    public BigDecimal getTotalWithIva() {
        return totalWithIva;
    }

    public void setTotalWithIva(BigDecimal totalWithIva) {
        this.totalWithIva = totalWithIva;
    }
}
