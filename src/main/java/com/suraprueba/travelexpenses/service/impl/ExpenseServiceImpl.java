package com.suraprueba.travelexpenses.service.impl;

import com.suraprueba.travelexpenses.domain.Expense;
import com.suraprueba.travelexpenses.dto.EmployeeMonthlyExpensesDTO;
import com.suraprueba.travelexpenses.dto.ExpenseDTO;
import com.suraprueba.travelexpenses.dto.MonthlyExpensesDTO;
import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;
import com.suraprueba.travelexpenses.repository.IEmployeeRepository;
import com.suraprueba.travelexpenses.repository.IExpenseRepository;
import com.suraprueba.travelexpenses.service.IExpenseService;
import com.suraprueba.travelexpenses.util.ExpenseCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements IExpenseService {

    private final IEmployeeRepository employeeExpenseRepository;
    private final IExpenseRepository expenseRepository;

    public ExpenseServiceImpl(IEmployeeRepository employeeExpenseRepository, IExpenseRepository expenseRepository) {
        this.employeeExpenseRepository = employeeExpenseRepository;
        this.expenseRepository = expenseRepository;

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
                                .collect(Collectors.toList());

                        BigDecimal total = empExpenses.stream()
                                .map(Expense::getAmount)
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

                        BigDecimal totalWithVat = ExpenseCalculator.calculateTotalWithIva(total);
                        String coveredBy = ExpenseCalculator.whoAssumes(totalWithVat);

                        return new EmployeeMonthlyExpensesDTO(employeeName, expenseDTOs, totalWithVat, coveredBy);
                    })
                    .sorted(Comparator.comparing(EmployeeMonthlyExpensesDTO::getEmployee))
                    .collect(Collectors.toList());

            BigDecimal totalMonth = employeeSummaries.stream()
                    .map(EmployeeMonthlyExpensesDTO::getTotalEmployeeWithIva)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            String monthLabel = String.format("%d-%02d", month.getYear(), month.getMonthValue());
            monthlySummaries.add(new MonthlyExpensesDTO(monthLabel, employeeSummaries, totalMonth));
            totalAllMonths = totalAllMonths.add(totalMonth);
        }
        return new TravelExpensesResponseDTO(monthlySummaries, totalAllMonths);
    }
}
