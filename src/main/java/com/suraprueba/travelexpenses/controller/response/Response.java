package com.suraprueba.travelexpenses.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private List<String> message = new ArrayList<>();
    private T data;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
