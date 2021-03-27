package com.yg.dao;

import com.yg.domain.Application;
import com.yg.domain.Employee;
import sun.reflect.generics.tree.BaseType;

import java.util.List;

public interface ApplicationMapper extends BaseMapper<Application> {
    /**
     * 根据员工查询未处理的异动申请
     * @param emp 需要查询的员工
     * @return 该员工对应的未处理的异动申请
     */
    List<Application> findByEmp(Employee emp);
}
