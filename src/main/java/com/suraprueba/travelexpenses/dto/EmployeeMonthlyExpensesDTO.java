package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeMonthlyExpensesDTO {

    private String employee;
    private List<ExpenseDTO> expenses;
    private BigDecimal totalWithoutIva;
    private BigDecimal ivaAmount;
    private BigDecimal totalEmployeeWithIva;
    private String coveredBy;

    public EmployeeMonthlyExpensesDTO(String employee, List<ExpenseDTO> expenses, BigDecimal totalWithoutIva, BigDecimal ivaAmount, BigDecimal totalEmployeeWithIva, String coveredBy) {
        this.employee = employee;
        this.expenses = expenses;
        this.totalWithoutIva = totalWithoutIva;
        this.ivaAmount = ivaAmount;
        this.totalEmployeeWithIva = totalEmployeeWithIva;
        this.coveredBy = coveredBy;
    }

    public BigDecimal getTotalWithoutIva() {
        return totalWithoutIva;
    }

    public void setTotalWithoutIva(BigDecimal totalWithoutIva) {
        this.totalWithoutIva = totalWithoutIva;
    }

    public BigDecimal getIvaAmount() {
        return ivaAmount;
    }

    public void setIvaAmount(BigDecimal ivaAmount) {
        this.ivaAmount = ivaAmount;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }

    public BigDecimal getTotalEmployeeWithIva() {
        return totalEmployeeWithIva;
    }

    public void setTotalEmployeeWithIva(BigDecimal totalEmployeeWithIva) {
        this.totalEmployeeWithIva = totalEmployeeWithIva;
    }

    public String getCoveredBy() {
        return coveredBy;
    }

    public void setCoveredBy(String coveredBy) {
        this.coveredBy = coveredBy;
    }
}
