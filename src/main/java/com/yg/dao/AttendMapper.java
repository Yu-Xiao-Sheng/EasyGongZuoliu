package com.yg.dao;

import com.yg.domain.Attend;
import com.yg.domain.AttendType;
import com.yg.domain.Employee;

import java.util.List;

public interface AttendMapper extends BaseMapper<Attend> {

    List<Attend> findByEmpAndMonth(Employee emp, String month);

    /**
     * 根据员工、日期查询该员工的打卡记录集合
     * @param emp 员工
     * @param dutyDay 日期
     * @return 该员工某天的打卡记录集合
     */
    List<Attend> findByEmpAndDutyDay(Employee emp, String dutyDay);

    /**
     * 根据员工、日期、上下班的情况查询该员工的打卡记录集合
     * @param emp 员工
     * @param dutyDay 日期
     * @param isCome 是否上班
     * @return 该员工某天上班或下班的打卡记录
     */
    Attend findByEmpAndDutyDayAndCome(Employee emp, String dutyDay, boolean isCome);

    /**
     * 查看员工三天的非正常打卡情况
     * @param emp 员工
     * @param type 考勤类型
     * @return 该员工前三天的非正常打卡情况
     */
    List<Attend> findByEmpUnAttend(Employee emp, AttendType type);
}
