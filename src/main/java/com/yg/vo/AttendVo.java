package com.yg.vo;

import java.io.Serializable;
import java.util.Date;

public class AttendVo implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    private String dutyDay;
    private String UnType;
    private Date time;

    public AttendVo() {
    }

    public AttendVo(Integer id, String dutyDay, String unType, Date time) {
        this.id = id;
        this.dutyDay = dutyDay;
        UnType = unType;
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
        this.dutyDay = dutyDay;
    }

    public String getUnType() {
        return UnType;
    }

    public void setUnType(String unType) {
        UnType = unType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
