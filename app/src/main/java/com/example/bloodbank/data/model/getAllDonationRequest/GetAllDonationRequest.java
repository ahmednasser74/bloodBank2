
package com.example.bloodbank.data.model.getAllDonationRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllDonationRequest {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private GetAllDonationRequestPagination data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public GetAllDonationRequestPagination getData() {
        return data;
    }

    public void setData(GetAllDonationRequestPagination data) {
        this.data = data;
    }

}
