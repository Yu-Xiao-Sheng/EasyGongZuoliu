package com.yg.service.impl;

import com.exception.HrException;
import com.yg.dao.*;
import com.yg.domain.*;
import com.yg.service.MgrManagerService;
import com.yg.vo.ApplicationVo;
import com.yg.vo.EmployeeVo;
import com.yg.vo.SalaryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("MgrManagerService")
public class MgrManagerServiceImpl implements MgrManagerService {
    @Resource
    private ApplicationMapper applicationMapper;
    @Resource
    private AttendMapper attendMapper;
    @Resource
    private AttendTypeMapper attendTypeMapper;
    @Resource
    private CheckBackMapper checkBackMapper;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private ManagerMapper managerMapper;
    @Resource
    private PaymentMapper paymentMapper;

    private Manager findByName(String username){
        Employee tempEmployee = employeeMapper.findByName(username);
        if(tempEmployee != null && tempEmployee instanceof Manager){
            return (Manager)tempEmployee;
        }else{
            return null;
        }
    }

    @Override
    public void addEmployee(Employee employee, String username) throws HrException {
        Manager manager = findByName(username);
        System.out.println(manager);
        if(manager != null){
            employee.setManager(manager);
            employeeMapper.save(employee);
        }else{
            throw new HrException("您是经理吗？或您还没有登录？");
        }
    }

    //获取上个月的全部工资记录
    @Override
    public List<SalaryVo> getSalaryVoByManager(String username) {
        Manager manager = findByName(username);
        if(manager == null){
            throw new HrException("您是经理吗？或您还没有登录？");
        }
        List<Employee> employees = manager.getEmployees();
        if(employees == null || employees.size() < 1){
            throw new HrException("您的部门没有员工");
        }
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);//从当前时间的月份中减去1
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());
        List<SalaryVo> result = new ArrayList<>();
        for(Employee employee: employees){
            Payment payment = paymentMapper.findByMonthAndEmp(payMonth,employee);
            if(payment != null){
                result.add(new SalaryVo(employee.getName(),payment.getAmount()));
            }
        }
        return result;
    }

    @Override
    public List<ApplicationVo> getApplicationsByManager(String username) {
        Manager manager = findByName(username);
        if(manager == null) {
            throw new HrException("您是经理吗？或您还没有登录");
        }
        //获取改经理管理的所有员工
        List<Employee> employees = manager.getEmployees();
        if(employees == null || employees.size() < 1){
            throw new HrException("您的部门没有员工");
        }
        List<ApplicationVo> result = new ArrayList<>();
        for(Employee emp:employees){
            List<Application> applications = applicationMapper.findByEmp(emp);
            if(applications != null && applications.size() > 0){
                for(Application application:applications){
                    if(!application.getResult()){
                        Attend attend = application.getAttend();
                        result.add(new ApplicationVo(
                                application.getId(),
                                emp.getName(),
                                attend.getType().getName(),
                                application.getType().getName(),
                                application.getReason()));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public List<EmployeeVo> getEmployeesByManager(String username) {
        Manager manager = findByName(username);
        if(manager == null){
            throw new HrException("您是经理吗？或您未登录？");
        }
        List<Employee> employees = manager.getEmployees();
        if(employees == null || employees.size() < 1){
            throw new HrException("您的部门没有员工");
        }
        List<EmployeeVo> result = new ArrayList<>();
        for(Employee emp:employees){
            result.add(new EmployeeVo(
                    emp.getName(),
                    emp.getPass(),
                    emp.getSalary()
                    ));
        }
        return result;
    }

    //处理申请
    @Override
    public void check(Integer applicationId, String username, boolean result) {
        Application application = applicationMapper.get(applicationId);
        CheckBack checkBack = new CheckBack();
        checkBack.setApp(application);

        Manager manager = findByName(username);
        if(manager == null){
            throw new HrException("您是经理吗？或您还没有登录？");
        }
        checkBack.setManager(manager);
        if(result){
            //通过申请
            checkBack.setResult(true);
            //修改申请为已批复
            application.setResult(true);
            applicationMapper.update(application);
            //修改出勤的类型
            Attend attend = application.getAttend();
            attend.setType(application.getType());
            System.out.println("MgrManagerServiceImpl:");
            System.out.println(attend);
            attendMapper.update(attend);
        }else{
            //未通过申请
            checkBack.setResult(false);
            //申请已批复
            application.setResult(true);
            applicationMapper.update(application);
        }
        System.out.println(checkBack);
        checkBackMapper.save(checkBack);
    }
}
