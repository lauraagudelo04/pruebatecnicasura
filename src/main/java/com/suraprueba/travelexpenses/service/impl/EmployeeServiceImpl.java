package com.suraprueba.travelexpenses.service.impl;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.repository.IEmployeeRepository;
import com.suraprueba.travelexpenses.service.IEmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    public EmployeeServiceImpl(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Page<Employee> findAllWithExpensesInDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        List<Employee> employees = employeeRepository.findAllWithExpensesInDateRange(startDate, endDate, pageable);
        if (employees.isEmpty()) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404), "No se encontraron empleados con gastos en el rango de fechas proporcionado.");
        }
        return new PageImpl<>(employees, pageable, employees.size());
    }
}
