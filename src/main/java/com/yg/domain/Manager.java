package com.yg.domain;

import java.io.Serializable;
import java.util.List;

public class Manager extends Employee implements Serializable {
    private static final long serialVersionUID = 48L;
    //该经理管理的部门；
    private String dept;
    //该经理对应的所有员工:
    private List<Employee> employees;
    //该经理对应的所有批复
    private List<CheckBack> checks;

    public Manager() {
    }

    public Manager(Integer id, String name, String pass, double salary, Manager manager, List<Attend> attends, String dept, List<Employee> employees, List<CheckBack> checks) {
        super(id, name, pass, salary, manager, attends);
        this.dept = dept;
        this.employees = employees;
        this.checks = checks;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<CheckBack> getChecks() {
        return checks;
    }

    public void setChecks(List<CheckBack> checks) {
        this.checks = checks;
    }

    @Override
    public String toString() {

        return "Manager{" +
                "dept='" + dept + '\'' +
                ", employees=" + employees +
                ", checks=" + checks +
                '}';
    }
}
