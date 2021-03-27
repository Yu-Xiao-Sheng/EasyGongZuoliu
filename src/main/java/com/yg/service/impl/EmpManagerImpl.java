package com.yg.service.impl;

import com.yg.dao.*;
import com.yg.domain.*;
import com.yg.service.EmpManagerService;
import com.yg.vo.AttendVo;
import com.yg.vo.PaymentVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static com.yg.constant.ConstantManager.*;

@Service("EmpManagerService")
public class EmpManagerImpl implements EmpManagerService {
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


    @Override
    public int validLogin(Manager mgr) {
        //如果找到一个经理，以经理身份登录
        if(managerMapper.findByNameAndPass(mgr).size() >= 1){
            return LOGIN_MGR;
        }
        //如果找到普通员工，以员工身份登录
        else if(employeeMapper.findByNameAndPass(mgr).size() >= 1){
            return LOGIN_EMP;
        }else{
            return LOGIN_FALT;
        }
    }

    @Override
    public void autoPunch() {
        System.out.println("自动插入旷工记录");
        List<Employee> emps = employeeMapper.findAll();
        //当前时间
        String dutyDay = new Date(System.currentTimeMillis()).toString();
        //获取旷工对应的考勤类型
        AttendType atype = attendTypeMapper.get(6);
        for(Employee e : emps){
            Attend a = new Attend();
            a.setDutyDay(dutyDay);
            a.setType(atype);
            //如果当前时间是早上，对应于上班打卡，否则就是下班打卡
            a.setCome(Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < AM_LIMIT);
            a.setEmployee(e);
            attendMapper.save(a);
        }
    }

    @Override
    public void autoPay() {
        System.out.println("自动插入工资结算");
        List<Employee> emps = employeeMapper.findAll();
        //获取上个月时间
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,-15);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String payMonth = sdf.format(c.getTime());
        //为每个员工计算上个月的工资
        for(Employee e : emps){
            Payment pay = new Payment();
            //获取员工的工资
            Double amount = e.getSalary();
            //获取该员工上个月的考勤记录
            List<Attend> attends = attendMapper.findByEmpAndMonth(e, payMonth);
            //用工资累计其考勤记录的工资
            for(Attend a:attends){
                amount += a.getType().getAmount();
            }
            //添加工资结算
            pay.setPayMonth(payMonth);
            pay.setEmployee(e);
            pay.setAmount(amount);
            paymentMapper.save(pay);
        }
    }

    @Override
    public int validPunch(String user, String dutyDay) {
        //不能找到对应用户，返回无法打卡
        Employee emp = employeeMapper.findByName(user);
        if(emp == null) return NO_PUNCH;
        //找到员工当前的考勤记录
        List<Attend> attends = attendMapper.findByEmpAndDutyDay(emp,dutyDay);
        //系统没有为用户在当天插入空打卡记录，无法打卡
        if(attends == null || attends.size() <= 0){
            return NO_PUNCH;
        }else if(attends.size() == 1 &&
        attends.get(0).isCome() &&
        attends.get(0).getPunchTime() == null){
            return COME_PUNCH;//只能上班打卡
        }else if(attends.size() == 1 &&
        attends.get(0).getPunchTime() == null){
            return LEAVE_PUNCH;//只能下班打卡
        }else if(attends.size() == 2){
            if(attends.get(0).getPunchTime() == null &&
            attends.get(1).getPunchTime() == null){
                return BOTH_PUNCH;//可以上、下班打卡
            }else if(attends.get(1).getPunchTime() == null){
                return LEAVE_PUNCH;//可以下班打卡
            }else{
                return NO_PUNCH;//不能打卡
            }
        }
        return NO_PUNCH;
    }

    @Override
    public int punch(String user, String dutyDay, boolean isCome) {
        Employee emp = employeeMapper.findByName(user);
        if(emp == null){
            return PUNCH_FALT;
        }
        //找到员工本次打卡对应的考勤记录
        Attend attend = attendMapper.findByEmpAndDutyDayAndCome(emp, dutyDay, isCome);
        if(attend == null){
            return PUNCH_FALT;
        }
        //已经打卡
        if(attend.getPunchTime() != null){
            return PUNCHED;
        }
        System.out.println("===========打卡===========");
        //获取打卡时间
        int punchHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        attend.setPunchTime(new Date());
        //上班打卡
        if(isCome){
            //9:00之前算正常
            if(punchHour < COME_LIMIT){
                attend.setType(attendTypeMapper.get(1));
            }
            //9:00-11:00算迟到
            else if (punchHour < LATE_LIMIT){
                attend.setType(attendTypeMapper.get(4));
            }
            //11：00之后算旷工，不用理会
        }//下班打卡
        else{
            //18:00之后算正常
            if(punchHour >= LEAVE_LIMIT){
                attend.setType(attendTypeMapper.get(1));
            }
            //16:00 - 18:00算早退
            else if(punchHour >= EARLY_LIMIT){
                attend.setType(attendTypeMapper.get(5));
            }
        }
        attendMapper.update(attend);
        return PUNCH_SUCC;

    }

    @Override
    public List<PaymentVo> empSalary(String empName) {
        Employee emp = employeeMapper.findByName(empName);
        List<Payment> pays = paymentMapper.findByEmp(emp);
        List<PaymentVo> result = new ArrayList<>();
        for(Payment p : pays){
            result.add(new PaymentVo(p.getPayMonth(),p.getAmount()));
        }
        return result;
    }


    @Override
    public List<AttendVo> unAttend(String empName) {
        Employee emp = employeeMapper.findByName(empName);
        AttendType type = attendTypeMapper.get(1);
        List<Attend> attends = attendMapper.findByEmpUnAttend(emp,type);
        List<AttendVo> result = new ArrayList<>();
        for(Attend att: attends){
            result.add(new AttendVo(att.getId(),att.getDutyDay(),att.getType().getName(),att.getPunchTime()));
        }
        return result;
    }

    @Override
    public List<AttendType> getAllType() {
        return attendTypeMapper.findAll();
    }

    @Override
    public boolean addApplication(int attId, int typeId, String reason) {
        //创建一个申请
        Application app = new Application();
        Attend attend = attendMapper.get(attId);
        AttendType type = attendTypeMapper.get(typeId);
        app.setAttend(attend);
        app.setType(type);
        if(reason != null){
            app.setReason(reason);
        }
        applicationMapper.save(app);
        return true;
    }
}
