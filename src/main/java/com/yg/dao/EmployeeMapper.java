package com.yg.dao;

import com.yg.domain.Employee;

import java.util.List;

public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 根据用户名和密码查询员工
     * @param emp
     * @return 符合指定用户名和密码的所有员工集合
     */
    List<Employee> findByNameAndPass(Employee emp);

    /**
     * 根据用户名查询员工
     * @param name 员工的姓名
     * @return 符合用户名的员工
     */
    Employee findByName(String name);

    /**
     * 通过管理员id获得一批用户集合
     * @param id
     * @return
     */
    List<Employee> findByMgrId(Integer id);
}
