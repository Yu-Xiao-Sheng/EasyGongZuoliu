package com.yg.controller;

import com.yg.constant.WebConstant;
import com.yg.domain.Application;
import com.yg.domain.Employee;
import com.yg.service.MgrManagerService;
import com.yg.vo.ApplicationVo;
import com.yg.vo.EmployeeVo;
import com.yg.vo.SalaryVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.annotation.Resource;
import javax.jws.WebResult;
import java.util.HashMap;
import java.util.List;

@Controller
public class ManagerController {
    @Resource
    private MgrManagerService mgr;

    //签核申请
    @GetMapping("/viewApp")
    public String viewApp(Model model, WebRequest webRequest){
        String user = (String)webRequest.getAttribute(WebConstant.USER,WebRequest.SCOPE_SESSION);
        List<ApplicationVo> apps = mgr.getApplicationsByManager(user);
        System.out.println("manager:viewAPP：apps:" + apps.size());
        if(apps.size() > 0){
            System.out.println(apps.get(0));
        }
        model.addAttribute("apps",apps);
        return "manager/viewApp";
    }

    //管理部门员工
    @GetMapping("/viewEmp")
    public String viewEmp(Model model, WebRequest webRequest){
        String user = (String)webRequest.getAttribute(WebConstant.USER, WebRequest.SCOPE_SESSION);
        List<EmployeeVo> emps = mgr.getEmployeesByManager(user);
        model.addAttribute("emps", emps);
        return "manager/viewEmp";
    }

    //新增员工
    @GetMapping("/addEmp")
    public String addEmp(){
        return "manager/addEmp";
    }

    @PostMapping("/processAddEmployee")
    public String processAddEmployee(Employee employee, WebRequest webRequest, Model model){
        String user = (String)webRequest.getAttribute(WebConstant.USER,WebRequest.SCOPE_SESSION);
        mgr.addEmployee(employee,user);
        return "manager/index";
    }

    //查看上月部门发薪
    @GetMapping("/viewDeptSal")
    public String viewDeptSal(Model model, WebRequest webRequest){
        String user = (String)webRequest.getAttribute(WebConstant.USER, WebRequest.SCOPE_SESSION);
        List<SalaryVo> salarys = mgr.getSalaryVoByManager(user);
        model.addAttribute("salarys",salarys);
        return "manager/viewDeptSal";
    }

    //这里应该是返回json
    //进行审批
    @GetMapping("checkApp")
    @ResponseBody
    public HashMap<String, String> checkApp(@RequestParam(value = "result") String result,
                                            @RequestParam(value = "appId") Integer appId,
                                            WebRequest webRequest){
        System.out.println("checkApp");
        System.out.println(result);
        System.out.println(appId);

        HashMap<String, String> checkResult = new HashMap<>();
        String user = (String) webRequest.getAttribute(WebConstant.USER, WebRequest.SCOPE_SESSION);

        if(result.equals("pass")){
            mgr.check(appId, user, true);
            checkResult.put("message", "pass");
        }else if(result.equals("deny")){
            mgr.check(appId, user, false);
            checkResult.put("message", "deny");
        }else{
            checkResult.put("message","参数丢失");
        }
        return checkResult;
    }
}
