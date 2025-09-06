package com.suraprueba.travelexpenses.service;

import com.suraprueba.travelexpenses.dto.TotalExpensesDTO;
import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;

public interface IExpenseService {

    TravelExpensesResponseDTO getTravelExpensesSummary();
    TotalExpensesDTO getTotalExpenses();
}
