package com.nitesh.poetryapp.Response;

import com.nitesh.poetryapp.Models.Poetry_Model;

import java.util.List;

public class GetPoetryResponse {
    String status,message;
    List<Poetry_Model> data;

    public GetPoetryResponse(String status, String message, List<Poetry_Model> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Poetry_Model> getData() {
        return data;
    }

    public void setData(List<Poetry_Model> data) {
        this.data = data;
    }
}
