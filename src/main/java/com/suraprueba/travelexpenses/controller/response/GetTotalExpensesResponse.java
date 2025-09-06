package com.suraprueba.travelexpenses.controller.response;

import com.suraprueba.travelexpenses.dto.TotalExpensesDTO;

import java.util.ArrayList;

public class GetTotalExpensesResponse extends Response<TotalExpensesDTO> {

    public GetTotalExpensesResponse() {
        setMessage(new ArrayList<String>());
        setData(new TotalExpensesDTO());
    }
}
