package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class EmployeeMonthlyExpensesDTO {

    private String employee;
    private List<ExpenseDTO> expenses;
    private BigDecimal totalEmployeeWithIva;
    private String coveredBy;

    public EmployeeMonthlyExpensesDTO(String employee, List<ExpenseDTO> expenses,
                                      BigDecimal totalEmployee, String coveredBy) {
        this.employee = employee;
        this.expenses = expenses;
        this.totalEmployeeWithIva = totalEmployee;
        this.coveredBy = coveredBy;
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
