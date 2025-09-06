package com.suraprueba.travelexpenses.controller.rest;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/with-expenses")
    @Operation(summary = "Obtener empleados con gastos en un rango de fechas", description = "Devuelve una lista paginada de empleados que tienen gastos registrados dentro del rango de fechas especificado.")
    public ResponseEntity<Page<Employee>> getEmployeesWithExpensesInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.findAllWithExpensesInDateRange(startDate, endDate, pageable);

        return ResponseEntity.ok(employees);
    }
}
