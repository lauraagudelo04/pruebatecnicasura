package com.suraprueba.travelexpenses.repository;

import com.suraprueba.travelexpenses.domain.Employee;
import com.suraprueba.travelexpenses.domain.Expense;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findAllByOrderByEmployee_NameAscExpenseDateAsc();

}
