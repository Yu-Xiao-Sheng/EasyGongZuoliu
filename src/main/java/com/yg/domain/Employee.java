package com.yg.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


//对应员工信息
public class Employee implements Serializable {
    private static final long serialVersionUID = 48L;

    private Integer id;
    @NotBlank(message = "用户名不允许为空",groups = {AddEmployee.class,Login.class})
    @Length(min = 4, max = 25, message = "用户名长度必须在4~25个字符之间",groups = {AddEmployee.class, Login.class})
    private String name;
    @NotBlank(message = "密码不允许为空",groups = {AddEmployee.class, Login.class})
    @Length(min = 4, max = 25, message = "密码长度必须在4~25个字符之间",groups = {AddEmployee.class, Login.class})
    private String pass;

    @NotNull(message = "员工工资不能为空", groups = {AddEmployee.class})
    @Range(min = 3000, max = 6000, message = "员工工资必须在3000~6000之间",groups = AddEmployee.class)
    private double salary;

    /**
     * 上面定义的校验规则分为两组：
     * 1. 用户登录时的校验规则：需要对name和pass两个成员变量进行数据校验.
     * 2. 添加员工是的校验规则：需要对name、pass和salary进行数据校验。
     */

    //员工对应的经理
    private Manager manager;
    //员工对应的考勤记录
    private List<Attend> attends;
    //员工对应的工资支付记录
    private List<Payment> payments;

    public Employee() {
    }

    public Employee(Integer id, String name, String pass, double salary, Manager manager, List<Attend> attends) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.salary = salary;
        this.manager = manager;
        this.attends = attends;
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Attend> getAttends() {
        return attends;
    }

    public void setAttends(List<Attend> attends) {
        this.attends = attends;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    //根据name和pass的值来重写hashcode()
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((pass == null) ? 0 : pass.hashCode());
        return result;
    }

    //根据name、pass的值来重写equals()方法，只要是name、pass相同的员工即认为相等
    @Override
    public boolean equals(Object obj) {
        if(this == obj)return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass())return false;
        Employee other = (Employee)obj;
        if(name == null){
            if(other.name != null)return false;
        }else if(!name.equals(other.name))return false;
        if(pass == null){
            if(other.pass != null)return false;
        }else if(!pass.equals(other.pass))return false;
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", salary=" + salary +
                ", manager=" + manager +
                ", attends=" + attends +
                ", payments=" + payments +
                '}';
    }
}
