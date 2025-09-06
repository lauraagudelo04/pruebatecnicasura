package com.suraprueba.travelexpenses.controller.response;

import com.suraprueba.travelexpenses.dto.TravelExpensesResponseDTO;

import java.util.ArrayList;

public class GetTravelExpensesResponse extends Response<TravelExpensesResponseDTO> {

    public GetTravelExpensesResponse() {
        setMessage(new ArrayList<String>());
        setData(new TravelExpensesResponseDTO());
    }
}
