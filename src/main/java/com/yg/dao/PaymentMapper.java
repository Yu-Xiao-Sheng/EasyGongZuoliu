package com.yg.dao;

import com.yg.domain.Employee;
import com.yg.domain.Payment;

import java.util.List;

public interface PaymentMapper extends BaseMapper<Payment> {
    /**
     * 根据员工查询月结工资
     * @param emp
     * @return 该员工对应的月结工资集合
     */
    List<Payment> findByEmp(Employee emp);

    /**
     * 根据员工和发工资月份来查询月结工资
     * @param payMonth 发工资月份
     * @param emp 领工资的员工
     * @return 指定员工、指定月份的月结工资
     */
    Payment findByMonthAndEmp(String payMonth, Employee emp);
}
