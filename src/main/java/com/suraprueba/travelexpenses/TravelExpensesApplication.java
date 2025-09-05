package com.suraprueba.travelexpenses;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.domain.Expense;
import com.suraprueba.travelexpenses.repository.IEmployeeRepository;
import com.suraprueba.travelexpenses.repository.IExpenseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class TravelExpensesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelExpensesApplication.class, args);
	}

	@Bean
	CommandLineRunner loadData(IEmployeeRepository employeeRepository,
							   IExpenseRepository expenseRepository) {
		return args -> {
			if (employeeRepository.count() > 0 && expenseRepository.count() > 0) {
				return;
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");

			Employee adam = new Employee();
			adam.setName("Adam");

			Employee bolton = new Employee();
			bolton.setName("Bolton");

			Employee chelsea = new Employee();
			chelsea.setName("Chelsea");

			Employee elsy = new Employee();
			elsy.setName("Elsy");

			Employee vincent = new Employee();
			vincent.setName("Vincent");

			Employee warden = new Employee();
			warden.setName("Warden");

			employeeRepository.save(adam);
			employeeRepository.save(bolton);
			employeeRepository.save(chelsea);
			employeeRepository.save(elsy);
			employeeRepository.save(vincent);
			employeeRepository.save(warden);

			expenseRepository.save(new Expense(null, LocalDate.parse("1/01/21", formatter), new BigDecimal("2000000"), adam));
			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("1000000"), adam));
			expenseRepository.save(new Expense(null, LocalDate.parse("3/02/21", formatter), new BigDecimal("500000"), adam));

			expenseRepository.save(new Expense(null, LocalDate.parse("1/01/21", formatter), new BigDecimal("400000"), bolton));
			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("500000"), bolton));
			expenseRepository.save(new Expense(null, LocalDate.parse("3/02/21", formatter), new BigDecimal("1100000"), bolton));

			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("900000"), chelsea));
			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("59999"), chelsea));
			expenseRepository.save(new Expense(null, LocalDate.parse("3/02/21", formatter), new BigDecimal("1100000"), chelsea));

			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("4000000"), elsy));

			expenseRepository.save(new Expense(null, LocalDate.parse("3/02/21", formatter), new BigDecimal("899999"), vincent));

			expenseRepository.save(new Expense(null, LocalDate.parse("2/01/21", formatter), new BigDecimal("5100000"), warden));
			expenseRepository.save(new Expense(null, LocalDate.parse("3/02/21", formatter), new BigDecimal("1100000"), warden));
		};
	}

}
