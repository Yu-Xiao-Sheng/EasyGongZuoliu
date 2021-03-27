package com.yg.controller;

import com.yg.constant.WebConstant;
import com.yg.domain.Employee;
import com.yg.domain.Login;
import com.yg.domain.Manager;
import com.yg.service.EmpManagerService;
import com.yg.service.MgrManagerService;
import com.yg.service.impl.EmpManagerImpl;
import com.yg.vo.AttendVo;
import com.yg.vo.PaymentVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.yg.constant.ConstantManager.*;

@Controller
public class EmployeeController {
    @Resource
    private EmpManagerService emp;

    //获取三天之内的非正常打卡记录
    @GetMapping("/viewUnPunch")
    public String view(Model model, WebRequest webRequest){
        String user = (String)webRequest.getAttribute(WebConstant.USER,WebRequest.SCOPE_SESSION);
        //调用业务层方法，返回三天之内的非正常打卡记录
        List<AttendVo> unAttends = emp.unAttend(user);
        model.addAttribute("unAttends",unAttends);
        return "employee/viewUnAttend";
    }

    @PostMapping("/processLogin")
    public String processLogin(@Validated(Login.class) Manager manager,
                               BindingResult bindingResult, String vercode,
                               RedirectAttributes attrs, WebRequest webRequest){
        /*
         * 这里的Manager参数使用了@Validated进行修饰，其中指定的Value值"Login.class"，该属性的值就是分组校验的组名。
         */

        if(bindingResult.getErrorCount() > 0){
            return "login";
        }
        //获取HttpSession中的rand属性
        String ver2 = (String) webRequest.getAttribute("rand",WebRequest.SCOPE_SESSION);
        if(vercode.equalsIgnoreCase(ver2)){
            //调用业务逻辑方法来处理登录请求
            int result = emp.validLogin(manager);
            //登录结果为普通员工
            if(result == LOGIN_EMP){
                webRequest.setAttribute(WebConstant.USER,
                        manager.getName(),WebRequest.SCOPE_SESSION);
                webRequest.setAttribute(WebConstant.LEVEL,
                        WebConstant.EMP_LEVEL,WebRequest.SCOPE_SESSION);
                attrs.addFlashAttribute("tip","您已经成功登录系统");
                return "employee/index";
            }else if(result == LOGIN_MGR){
             //登录结果为经理
                webRequest.setAttribute(WebConstant.USER,
                        manager.getName(),WebRequest.SCOPE_SESSION);
                webRequest.setAttribute(WebConstant.LEVEL,
                        WebConstant.MGR_LEVEL,WebRequest.SCOPE_SESSION);
                attrs.addFlashAttribute("tip","您已成功登录系统");
                return "manager/index";
            }else{
                //用户名和密码不正确
                attrs.addFlashAttribute("error","用户名/密码不匹配");
                return "redirect:loginPage";
            }
        }
        //验证码不正确
        attrs.addFlashAttribute("error","验证码不匹配，请重新输入");
        return "redirect:loginPage";
    }

    //进入打卡的处理方法
    @GetMapping("/{category}Punch")
    public String punch(@PathVariable String category,
                        Model model, WebRequest webRequest){
        //获取HttpSession中的user属性
        String user = (String)webRequest.getAttribute(WebConstant.USER,WebRequest.SCOPE_SESSION);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //格式化当前时间
        String dutyDay = sdf.format(new Date());
        //调用业务逻辑方法处理用户请求
        int result = emp.validPunch(user, dutyDay);
        model.addAttribute("punchIsValid",result);
        return StringUtils.uncapitalize(category) + "/punch";
    }

    //上班打卡的处理方法
    @GetMapping("/{category}Come")
    public String come(@PathVariable String category,
                       Model model, WebRequest webRequest){
        return process(category, true, model,webRequest);
    }
    //下班打卡的处理方法
    @GetMapping("/{category}Leave")
    public String leave(@PathVariable String category,
                        Model model, WebRequest webRequest){
        return process(category, false, model, webRequest);
    }
    //处理打卡的工具方法
    private String process(String category, boolean isCome,
                           Model model, WebRequest webRequest){
        //通过WebRequest的getAttribute方法，可以获取指定属性名称，指定空间中的属性值
        String user = (String)webRequest.getAttribute(WebConstant.USER,WebRequest.SCOPE_SESSION);
        String dutyDay = new java.sql.Date(System.currentTimeMillis()).toString();
        int result = emp.punch(user,dutyDay,isCome);
        switch(result){
            case PUNCH_FALT:
                model.addAttribute("tip","打卡失败");
                break;
            case PUNCHED:
                model.addAttribute("tip", "您已经打过卡了，不要重复打卡");
                break;
            case PUNCH_SUCC:
                model.addAttribute("tip","打卡成功");
                break;
        }
        //将首字母小写，拼接为视图名称返回
        return StringUtils.uncapitalize(category) + "/index";
    }

    //进入申请的处理方法
    @GetMapping("/appChange-{attId}")
    public String appChange(@PathVariable String attId,
                            Model model, WebRequest webRequest){
        model.addAttribute("attId", attId);
        model.addAttribute("types",emp.getAllType());
        return "employee/appChange";
    }

    //申请请求的处理方法
    @PostMapping("/processApp")
    public String processApp(Integer attId, Integer typeId,
                             String reason, Model model){
        //处理申请
        boolean result = emp.addApplication(attId, typeId, reason);
        if(result){
            model.addAttribute("tip","您已经申请成功，等待经理审阅");
        }else{
            model.addAttribute("tip","申请失败，请注意不要重复申请");
        }
        return "employee/index";
    }

    //进入登录页面
    @GetMapping("/loginPage")
    public String showLoginPage(Model model, WebRequest webRequest){
        model.addAttribute("employee", new Employee());
        String vcode = "" + (int)(Math.random() * 100000);
        model.addAttribute("vcode", vcode);
        webRequest.setAttribute("rand", vcode , WebRequest.SCOPE_SESSION);
        return "login";
    }

    //查看本人历史工资
    @GetMapping("/view{category}Salary")
    public String salary(@PathVariable String category,
                         WebRequest webRequest, Model model){
        String user = (String)webRequest.getAttribute(WebConstant.USER, WebRequest.SCOPE_SESSION);
        List<PaymentVo> salarys = emp.empSalary(user);
        model.addAttribute("salarys", salarys);
        return StringUtils.uncapitalize(category) + "/viewSalary";
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        //初始化session无效化
        request.getSession().invalidate();
        return "main";
    }

}
