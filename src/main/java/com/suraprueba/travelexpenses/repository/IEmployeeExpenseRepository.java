package com.suraprueba.travelexpenses.repository;

import com.suraprueba.travelexpenses.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmployeeExpenseRepository extends JpaRepository<Expense, UUID> {
}
