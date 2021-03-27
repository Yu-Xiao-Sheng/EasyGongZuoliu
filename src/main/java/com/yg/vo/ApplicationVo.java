package com.yg.vo;

/**
 * 用于保存员工的申请，使用于服务层的ov类
 * 保存员工原来的出勤类型，以及要申请的出勤类型，以及申请理由。
 */
public class ApplicationVo {
    private Integer id;
    private String employee;
    private String unAttend;
    private String toAttend;
    private String reason;

    public ApplicationVo() {
    }

    public ApplicationVo(Integer id, String employee, String unAttend, String toAttend, String reason) {
        this.id = id;
        this.employee = employee;
        this.unAttend = unAttend;
        this.toAttend = toAttend;
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getUnAttend() {
        return unAttend;
    }

    public void setUnAttend(String unAttend) {
        this.unAttend = unAttend;
    }

    public String getToAttend() {
        return toAttend;
    }

    public void setToAttend(String toAttend) {
        this.toAttend = toAttend;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
