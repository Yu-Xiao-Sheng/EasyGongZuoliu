package com.yg.vo;

public class SalaryVo {
    private String employeeName;
    private double amount;

    public SalaryVo(){}

    public SalaryVo(String employeeName, double amount) {
        this.employeeName = employeeName;
        this.amount = amount;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
