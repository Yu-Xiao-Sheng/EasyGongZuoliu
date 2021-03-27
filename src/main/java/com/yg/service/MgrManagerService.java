package com.yg.service;

import com.exception.HrException;
import com.yg.domain.CheckBack;
import com.yg.domain.Employee;
import com.yg.domain.Payment;
import com.yg.vo.ApplicationVo;
import com.yg.vo.EmployeeVo;
import com.yg.vo.SalaryVo;

import java.util.List;

public interface MgrManagerService {

    /**
     * 新增员工
     * @param employee
     * @param manager
     * @throws HrException
     */
    void addEmployee(Employee employee, String manager) throws HrException;

    /**
     * 根据经理返回所有的部门上个月的工资
     * @param manager
     * @return
     */
    List<SalaryVo> getSalaryVoByManager(String manager);

    /**
     * 返回该经理未完成的批复
     * @param manager
     * @return
     */
    List<ApplicationVo> getApplicationsByManager(String manager);

    /**
     * 根据经理返回该部门的全部员工
     * @param manager
     * @return
     */
    List<EmployeeVo> getEmployeesByManager(String manager);

    /**
     * 处理申请
     * @param applicationId
     * @param manager
     * @param result
     */
    void check(Integer applicationId, String manager, boolean result);
}
