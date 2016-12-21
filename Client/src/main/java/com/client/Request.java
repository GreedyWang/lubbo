package com.client;


public class Request {
    private String params;

    Request(String params) {
        this.params = params;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}
