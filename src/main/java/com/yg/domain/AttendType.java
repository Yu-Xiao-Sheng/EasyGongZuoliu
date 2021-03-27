package com.yg.domain;

import java.io.Serializable;

//对应考勤类型，迟到、早退等名称
public class AttendType implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    private String name;
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public AttendType() {
    }

    public AttendType(Integer id, String name, Double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
