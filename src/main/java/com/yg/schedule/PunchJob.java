package com.yg.schedule;

import com.yg.service.EmpManagerService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;

public class PunchJob extends QuartzJobBean {
    private boolean isRunning = false;
    @Resource(name = "EmpManagerService")
    private EmpManagerService empManager;

    //定义任务体
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(!isRunning){
            System.out.println("开始调度自动打卡");
            isRunning = true;
            //调用业务逻辑方法
            empManager.autoPunch();
            isRunning = false;
        }
    }
}
