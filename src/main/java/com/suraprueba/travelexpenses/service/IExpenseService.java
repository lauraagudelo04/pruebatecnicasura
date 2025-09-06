package com.suraprueba.travelexpenses.service;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface IExpenseService {

    TravelExpensesResponseDTO getTravelExpensesSummary();
    Page<Employee> findAllWithExpensesInDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
