package com.suraprueba.travelexpenses.dto;

import java.math.BigDecimal;
import java.util.List;

public class MonthlyExpensesDTO {
    private String month;
    private List<EmployeeMonthlyExpensesDTO> employees;
    private BigDecimal totalMonth;

    public MonthlyExpensesDTO(String month, List<EmployeeMonthlyExpensesDTO> employees,
                              BigDecimal totalMonth) {
        this.month = month;
        this.employees = employees;
        this.totalMonth = totalMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<EmployeeMonthlyExpensesDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeMonthlyExpensesDTO> employees) {
        this.employees = employees;
    }

    public BigDecimal getTotalMonth() {
        return totalMonth;
    }

    public void setTotalMonth(BigDecimal totalMonth) {
        this.totalMonth = totalMonth;
    }
}
