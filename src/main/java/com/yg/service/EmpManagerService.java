package com.yg.service;

import com.yg.dao.ApplicationMapper;
import com.yg.dao.AttendMapper;
import com.yg.domain.Attend;
import com.yg.domain.AttendType;
import com.yg.domain.Manager;
import com.yg.domain.Payment;
import com.yg.vo.AttendVo;
import com.yg.vo.PaymentVo;

import java.util.List;

public interface EmpManager {
    /**
     * 以经理身份来验证登录
     * @param mgr
     * @return
     */
    public int validLogin(Manager mgr);

    /**
     * 自动打卡，星期一到星期五，早上7:00为每个员工插入旷工记录
     */
    public void autoPunch();

    /**
     * 自动结算工资，每月3日，结算上个月的工资
     */
    public void autoPay();

    /**
     * 验证某个员工是否可打卡
     * @param user 员工用户名
     * @param dutyDay 日期
     * @return 可打卡类型
     */
    public int validPunch(String user, String dutyDay);

    /**
     * 打卡
     * @param user 员工姓名
     * @param dutyDay 打卡日期
     * @param isCome 是否是上班打卡
     * @return 打卡结果
     */
    public int punch(String user, String dutyDay, boolean isCome);

    /**
     * 根据该员工浏览对应的工资
     * @param empName 员工用户名
     * @return 该员工的工资列表
     */
    public List<PaymentVo> empSalary(String empName);

    /**
     * 员工查看自己最近三天的非正常打卡情况
     * @param empName 员工姓名
     * @return 该员工最近三天的非正常打卡记录
     */
    public List<AttendVo> unAttend(String empName);

    /**
     * 获取全部的考勤类型
     * @return
     */
    public List<AttendType> getAllType();

    /**
     * 添加申请
     * @param attId 申请的考勤ID
     * @param typeId 申请的类型ID
     * @param reason 申请的理由
     * @return 添加的结果
     */
    public boolean addApplication(int attId, int typeId, String reason);

}
