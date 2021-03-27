package com.yg.domain;

import java.io.Serializable;

//对应的管理层批复，包括该批复对应的申请、是否通过申请、由那个经理完成批复
public class CheckBack implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    private boolean result;
    private String reason;
    private Application app;
    private Manager manager;

    public CheckBack() {
    }

    public String getReason() {
        return reason;
    }

    public CheckBack(Integer id, boolean result, String reason, Application app, Manager manager) {
        this.id = id;
        this.result = result;
        this.reason = reason;
        this.app = app;
        this.manager = manager;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
