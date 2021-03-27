package com.yg.schedule;

import com.yg.service.EmpManagerService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

public class PayJob extends QuartzJobBean {
    //判断作业是否执行的旗标
    private boolean isRunning = false;
    @Resource(name = "EmpManagerService")
    private EmpManagerService empMgr;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(!isRunning){
            System.out.println("开始调度自动结算工资");
            isRunning = true;
            empMgr.autoPay();
            isRunning = false;
        }
    }
}
