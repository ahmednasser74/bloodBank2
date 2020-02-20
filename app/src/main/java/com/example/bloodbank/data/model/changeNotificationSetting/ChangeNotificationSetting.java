
package com.example.bloodbank.data.model.changeNotificationSetting;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChangeNotificationSetting {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("data")
    @Expose
    private ChangeNotificationSettingData data;

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

    public ChangeNotificationSettingData getData() {
        return data;
    }

    public void setData(ChangeNotificationSettingData data) {
        this.data = data;
    }

}
