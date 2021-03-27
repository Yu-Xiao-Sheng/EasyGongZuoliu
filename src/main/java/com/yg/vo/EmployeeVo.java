package com.yg.vo;

public class EmployeeVo {
    private String employeeName;
    private String employeePassword;
    private Double amount;

    public EmployeeVo() {
    }


    public EmployeeVo(String employeeName, String employeePassword, Double amount) {
        this.employeeName = employeeName;
        this.employeePassword = employeePassword;
        this.amount = amount;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }



    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
