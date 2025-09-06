package com.suraprueba.travelexpenses.repository;

import com.suraprueba.travelexpenses.domain.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT DISTINCT e FROM Employee e " +
            "LEFT JOIN FETCH e.expenses ex " +
            "WHERE ex.expenseDate BETWEEN :startDate AND :endDate")
    List<Employee> findAllWithExpensesInDateRange(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate") LocalDate endDate,
                                                  Pageable pageable);
}
