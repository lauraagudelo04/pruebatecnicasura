package com.suraprueba.travelexpenses.controller.rest;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.dto.EmployeeDTO;
import com.suraprueba.travelexpenses.dto.ExpenseDTO;
import com.suraprueba.travelexpenses.dto.PageResponseDTO;
import com.suraprueba.travelexpenses.service.IEmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employess", description = "Gestión de empleados")
public class EmployeeController {

    private final IEmployeeService employeeService;

    public EmployeeController(IEmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/with-expenses")
    @Operation(summary = "Obtener empleados con gastos en un rango de fechas", description = "Devuelve una lista paginada de empleados que tienen gastos registrados dentro de un rango de fechas especificado.")
    public ResponseEntity<PageResponseDTO<EmployeeDTO>> getEmployeesWithExpensesInDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employeesPage = employeeService.findAllWithExpensesInDateRange(startDate, endDate, pageable);

        List<EmployeeDTO> dtos = employeesPage.getContent().stream()
                .map(e -> new EmployeeDTO(
                        e.getId(),
                        e.getName(),
                        e.getExpenses().stream()
                                .map(ex -> new ExpenseDTO(ex.getExpenseDate(), ex.getAmount()))
                                .toList()
                ))
                .toList();

        PageResponseDTO<EmployeeDTO> response = new PageResponseDTO<>(
                dtos,
                employeesPage.getNumber(),
                employeesPage.getSize(),
                employeesPage.getTotalElements()
        );

        return ResponseEntity.ok(response);
    }


}
