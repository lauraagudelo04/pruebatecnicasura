package com.suraprueba.travelexpenses.dto;

import java.util.List;

public class ExpenseResponseDTO {
    private EmployeeDTO employee;
    private String month;
    private String total;
    private String totalWithIva;
    private String assumedBy;
    private List<ExpenseResponseDTO> expenses;

    public ExpenseResponseDTO(EmployeeDTO employee, String month, String total, String totalWithIva, String assumedBy, List<ExpenseResponseDTO> expenses) {
        this.employee = employee;
        this.month = month;
        this.total = total;
        this.totalWithIva = totalWithIva;
        this.assumedBy = assumedBy;
        this.expenses = expenses;
    }

    public ExpenseResponseDTO() {

    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalWithIva() {
        return totalWithIva;
    }

    public void setTotalWithIva(String totalWithIva) {
        this.totalWithIva = totalWithIva;
    }

    public String getAssumedBy() {
        return assumedBy;
    }

    public void setAssumedBy(String assumedBy) {
        this.assumedBy = assumedBy;
    }

    public List<ExpenseResponseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseResponseDTO> expenses) {
        this.expenses = expenses;
    }
}
