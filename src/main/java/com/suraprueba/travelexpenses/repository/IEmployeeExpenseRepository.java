package com.suraprueba.travelexpenses.repository;

import com.suraprueba.travelexpenses.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IEmployeeExpenseRepository extends JpaRepository<Employee, UUID> {
}
