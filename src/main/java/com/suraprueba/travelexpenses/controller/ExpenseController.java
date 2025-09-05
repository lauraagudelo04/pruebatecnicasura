package com.suraprueba.travelexpenses.controller;

import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;
import com.suraprueba.travelexpenses.service.IExpenseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public TravelExpensesResponseDTO getAllExpenses() {
        return expenseService.getTravelExpensesSummary();
    }

}