package com.example.food_project.payload.response;

public class DataResponse {

    private int status;
    private String desc;
    private Object data;
    private boolean isSuccess;

    public DataResponse(int status, String desc, Object data, boolean isSuccess) {
        this.status = status;
        this.desc = desc;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public DataResponse(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
