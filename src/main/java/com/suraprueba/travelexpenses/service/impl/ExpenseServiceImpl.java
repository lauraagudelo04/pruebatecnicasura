package com.suraprueba.travelexpenses.service.impl;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.domain.Expense;
import com.suraprueba.travelexpenses.dto.EmployeeMonthlyExpensesDTO;
import com.suraprueba.travelexpenses.dto.ExpenseDTO;
import com.suraprueba.travelexpenses.dto.MonthlyExpensesDTO;
import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;
import com.suraprueba.travelexpenses.repository.IEmployeeRepository;
import com.suraprueba.travelexpenses.repository.IExpenseRepository;
import com.suraprueba.travelexpenses.service.IExpenseService;
import com.suraprueba.travelexpenses.util.ExpenseCalculator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    private final IExpenseRepository expenseRepository;
    private final IEmployeeRepository employeeRepository;

    public ExpenseServiceImpl(IExpenseRepository expenseRepository, IEmployeeRepository employeeRepository) {
        this.expenseRepository = expenseRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public TravelExpensesResponseDTO getTravelExpensesSummary() {
        List<Expense> expenses = expenseRepository.findAllByOrderByEmployee_NameAscExpenseDateAsc();

        Map<YearMonth, List<Expense>> expensesByMonth = expenses.stream()
                .collect(Collectors.groupingBy(
                        e -> YearMonth.from(e.getExpenseDate()),
                        TreeMap::new,
                        Collectors.toList()
                ));

        List<MonthlyExpensesDTO> monthlySummaries = new ArrayList<>();
        BigDecimal totalAllMonths = BigDecimal.ZERO;

        for (Map.Entry<YearMonth, List<Expense>> entry : expensesByMonth.entrySet()) {
            YearMonth month = entry.getKey();
            List<Expense> monthlyExpenses = entry.getValue();

            Map<String, List<Expense>> expensesByEmployee = monthlyExpenses.stream()
                    .collect(Collectors.groupingBy(e -> e.getEmployee().getName()));

            List<EmployeeMonthlyExpensesDTO> employeeSummaries = expensesByEmployee.entrySet().stream()
                    .map(empEntry-> {
                        String employeeName = empEntry.getKey();
                        List<Expense> empExpenses = empEntry.getValue();

                        List<ExpenseDTO> expenseDTOs = empExpenses.stream()
                                .map(e -> new ExpenseDTO(e.getExpenseDate(), e.getAmount()))
                                .toList();

                        BigDecimal totalWithoutIva = empExpenses.stream()
                                .map(Expense::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                        BigDecimal totalWithIva = ExpenseCalculator.calculateTotalWithIva(totalWithoutIva);
                        BigDecimal ivaAmount = totalWithIva.subtract(totalWithoutIva);
                        String coveredBy = ExpenseCalculator.whoAssumes(totalWithIva);

                        return new EmployeeMonthlyExpensesDTO(employeeName, expenseDTOs,totalWithoutIva,ivaAmount, totalWithIva, coveredBy);
                    })
                    .sorted(Comparator.comparing(EmployeeMonthlyExpensesDTO::getEmployee))
                    .toList();

            BigDecimal totalMonth = employeeSummaries.stream()
                    .map(EmployeeMonthlyExpensesDTO::getTotalEmployeeWithIva)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            String monthLabel = String.format("%d-%02d", month.getYear(), month.getMonthValue());
            monthlySummaries.add(new MonthlyExpensesDTO(monthLabel, employeeSummaries, totalMonth));
            totalAllMonths = totalAllMonths.add(totalMonth);
        }
        return new TravelExpensesResponseDTO(monthlySummaries, totalAllMonths);
    }

    @Override
    public Page<Employee> findAllWithExpensesInDateRange(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        List<Employee> employees = employeeRepository.findAllWithExpensesInDateRange(startDate, endDate, pageable);
        return new PageImpl<>(employees, pageable, employees.size());
    }

}
