package com.suraprueba.travelexpenses.controller.rest;

import com.suraprueba.travelexpenses.controller.response.GetTravelExpensesResponse;
import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.service.IExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;

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
    public ResponseEntity<GetTravelExpensesResponse> getAllExpenses() {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var getTravelExpensesResponse = new GetTravelExpensesResponse();

        try {
          getTravelExpensesResponse.setData(expenseService.getTravelExpensesSummary());
          getTravelExpensesResponse.getMessage().add("Gastos de viaje consultados exitosamente");
        }catch (Exception e) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
          getTravelExpensesResponse.getMessage().add("Error al consultar los gastos de viaje: " + e.getMessage());
        }
        return new ResponseEntity<>(getTravelExpensesResponse, httpStatusCode);
    }

    @GetMapping("/with-expenses")
    public ResponseEntity<Page<Employee>> getEmployeesWithExpensesInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = expenseService.findAllWithExpensesInDateRange(startDate, endDate, pageable);

        return ResponseEntity.ok(employees);
    }
}
