package com.suraprueba.travelexpenses.dto;

import java.util.List;

public class EmployeeDTO {

    private Long id;
    private String name;
    private List<ExpenseDTO> expenses;

    public EmployeeDTO(Long id, String name, List<ExpenseDTO> expenses) {
        this.id = id;
        this.name = name;
        this.expenses = expenses;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExpenseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseDTO> expenses) {
        this.expenses = expenses;
    }
}
