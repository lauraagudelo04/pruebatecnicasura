package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class TravelExpensesResponseDTO {

    private List<MonthlyExpensesDTO> months;
    private BigDecimal totalAllMonths;

    public TravelExpensesResponseDTO(List<MonthlyExpensesDTO> months, BigDecimal totalGeneral) {
        this.months = months;
        this.totalAllMonths = totalGeneral;
    }

    public TravelExpensesResponseDTO() {
    }

    public List<MonthlyExpensesDTO> getMonths() {
        return months;
    }

    public void setMonths(List<MonthlyExpensesDTO> months) {
        this.months = months;
    }

    public BigDecimal getTotalAllMonths() {
        return totalAllMonths;
    }

    public void setTotalAllMonths(BigDecimal totalAllMonths) {
        this.totalAllMonths = totalAllMonths;
    }
}
