package com.suraprueba.travelexpenses.service;

import com.suraprueba.travelexpenses.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IEmployeeService {
    Page<Employee> findAllWithExpensesInDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
