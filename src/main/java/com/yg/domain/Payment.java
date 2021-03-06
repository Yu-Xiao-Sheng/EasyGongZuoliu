package com.yg.domain;

import java.io.Serializable;


//对应每月工资信息，包括发工资的月份、领工资的员工及工资数等信息
public class Payment implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    private String payMonth;
    private Employee employee;
    private Double amount;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Payment() {
    }

    public Payment(Integer id, String payMonth, Employee employee, Double amount) {
        this.id = id;
        this.payMonth = payMonth;
        this.employee = employee;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
