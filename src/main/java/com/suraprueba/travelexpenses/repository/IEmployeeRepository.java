package com.suraprueba.travelexpenses.repository;

import com.suraprueba.travelexpenses.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e " +
            "WHERE EXISTS (" +
            "   SELECT 1 FROM Expense ex " +
            "   WHERE ex.employee = e AND ex.expenseDate BETWEEN :startDate AND :endDate" +
            ")")
    Page<Employee> findAllWithExpensesInDateRange(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  Pageable pageable);

}
