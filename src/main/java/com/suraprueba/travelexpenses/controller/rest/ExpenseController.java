package com.suraprueba.travelexpenses.controller.rest;

import com.suraprueba.travelexpenses.controller.response.GetTotalExpensesResponse;
import com.suraprueba.travelexpenses.controller.response.GetTravelExpensesResponse;
import com.suraprueba.travelexpenses.service.IExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/total")
    @Operation(summary = "Obtener total de gastos de viaje", description = "Devuelve el total acumulado de todos los gastos de viaje registrados de todos los empleados con iva y con iva.")
    public ResponseEntity<GetTotalExpensesResponse> getTotalExpenses() {
        var httpStatusCode = HttpStatus.ACCEPTED;
        var getTotalExpensesResponse = new GetTotalExpensesResponse();

        try {
            getTotalExpensesResponse.setData(expenseService.getTotalExpenses());
            getTotalExpensesResponse.getMessage().add("Gastos totales de viajes consultados exitosamente");
        }catch (Exception e) {
            httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            getTotalExpensesResponse.getMessage().add("Error al consultar los gastos totales de viajes: " + e.getMessage());
        }
        return new ResponseEntity<>(getTotalExpensesResponse, httpStatusCode);
    }

}
