package com.yg.domain;

import java.io.Serializable;

//对应普通员工的考勤申请，包括申请理由、是否被批复及申请改变的类型等属性
public class Application implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    private String reason;
    private Attend attend;
    private Boolean result;
    private AttendType type;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Application(Integer id, String reason, Attend attend, Boolean result, AttendType type) {
        this.id = id;
        this.reason = reason;
        this.attend = attend;
        this.result = result;
        this.type = type;
    }



    public Application() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Attend getAttend() {
        return attend;
    }

    public void setAttend(Attend attend) {
        this.attend = attend;
    }

    public AttendType getType() {
        return type;
    }

    public void setType(AttendType type) {
        this.type = type;
    }
}
