package com.suraprueba.travelexpenses.controller;

import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;
import com.suraprueba.travelexpenses.service.IExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expenses")
@Tag(name = "Expenses", description = "Gestión de gastos de viaje")
public class ExpenseController {

    private final IExpenseService expenseService;

    public ExpenseController(IExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    @Operation(summary = "Obtener resumen de gastos de viaje empleados sura", description = "Devuelve el total por mes,por todos los meses, por empleado y quién cubre los gastos.")
    public TravelExpensesResponseDTO getAllExpenses() {
        return expenseService.getTravelExpensesSummary();
    }

}