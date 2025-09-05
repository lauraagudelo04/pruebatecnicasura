package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class TravelExpensesResponseDTO {

    private List<MonthlyExpensesDTO> months;
    private BigDecimal totalGeneral;

    public TravelExpensesResponseDTO(List<MonthlyExpensesDTO> months, BigDecimal totalGeneral) {
        this.months = months;
        this.totalGeneral = totalGeneral;
    }

    public List<MonthlyExpensesDTO> getMonths() {
        return months;
    }

    public void setMonths(List<MonthlyExpensesDTO> months) {
        this.months = months;
    }

    public BigDecimal getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(BigDecimal totalGeneral) {
        this.totalGeneral = totalGeneral;
    }
}
