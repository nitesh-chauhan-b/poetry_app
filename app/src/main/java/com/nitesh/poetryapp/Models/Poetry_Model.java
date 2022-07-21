package com.nitesh.poetryapp.Models;

public class Poetry_Model {

    int id;
    String poetry_data,poet_name,time;

    public Poetry_Model(int id, String poetry_data, String poet_name, String time) {
        this.id = id;
        this.poetry_data = poetry_data;
        this.poet_name = poet_name;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetry_data() {
        return poetry_data;
    }

    public void setPoetry_data(String poetry_data) {
        this.poetry_data = poetry_data;
    }

    public String getPoet_name() {
        return poet_name;
    }

    public void setPoet_name(String poet_name) {
        this.poet_name = poet_name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
