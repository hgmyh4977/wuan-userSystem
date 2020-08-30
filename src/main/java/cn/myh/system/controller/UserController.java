package cn.myh.system.controller;

import cn.myh.system.domain.User;
import cn.myh.system.service.UserException;
import cn.myh.system.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * description: UserController <br>
 * date: 2020/8/29 21:07 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@Component
@RequestMapping("/user")
public class UserController {
    @Resource(name="userService")
    private UserService userService;


    @RequestMapping("/login")
    public void login(User form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 表单校验
        Map<String,String> errors = new HashMap<>();
        if(form.getUsername() == null || form.getUsername().trim().isEmpty()){
            errors.put("username","用户名不能为空");
        }else if(form.getUsername().length()<3 || form.getUsername().length()>10){
            errors.put("username","用户名长度为3~10！");
        }
        if(form.getPassword() == null || form.getPassword().trim().isEmpty()){
            errors.put("password","密码不能为空");
        }else if(form.getPassword().length()<3 || form.getPassword().length()>10){
            errors.put("password","密码长度为3~10！");
        }
        if (errors.size()>0){
            request.setAttribute("user",form);
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
            return ;
        }
        // 数据库校验
        User user = null;
        try {
            user = userService.login(form);
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/jsps/login.jsp").forward(request,response);
            return;
        }
        request.getSession().setAttribute("session_user",user);
        request.getRequestDispatcher("/WEB-INF/userSource/source.jsp").forward(request,response);
        return ;
    }
    @RequestMapping("/regist")
    public void regist(User form,HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        // 表单校验
        Map<String,String> errors = new HashMap<>();
        if(form.getUsername() == null || form.getUsername().trim().isEmpty()){
            errors.put("username","用户名不能为空");
        }else if(form.getUsername().length()<3 || form.getUsername().length()>10){
            errors.put("username","用户名长度为3~10！");
        }
        if(form.getPassword() == null || form.getPassword().trim().isEmpty()){
            errors.put("password","密码不能为空");
        }else if(form.getPassword().length()<3 || form.getPassword().length()>10){
            errors.put("password","密码长度为3~10！");
        }
        if(form.getEmail() == null || form.getEmail().trim().isEmpty()){
            errors.put("email","邮箱不能为空");
        }else if(!form.getEmail().matches("\\w+@\\w+\\.\\w+")){
            errors.put("email","邮箱格式错误！");
        }
        if(form.getAge()<0 || form.getAge()>80){
            errors.put("age","您这什么年龄啊？重新输入吧！");
        }
        if (errors.size()>0){
            request.setAttribute("user",form);
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
            return ;
        }
        // 数据库注册
        String uid = UUID.randomUUID().toString().replace("-","").toUpperCase();
        form.setUid(uid);
        try {
            userService.regist(form);
        } catch (UserException e) {
            request.setAttribute("msg",e.getMessage());
            request.setAttribute("user",form);
            request.getRequestDispatcher("/jsps/regist.jsp").forward(request,response);
            return;
        }
        request.setAttribute("msg","注册成功，请重新登录！");
        request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
        return;
    }


    @RequestMapping("/modify")
    public ModelAndView modifyUser(User form,HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        Map<String, String> errors = new HashMap<>();
        if (form.getPassword() == null || form.getPassword().trim().isEmpty()) {
            errors.put("password", "密码不能为空");
        } else if (form.getPassword().length() < 3 || form.getPassword().length() > 10) {
            errors.put("password", "密码长度为3~10！");
        }
        if (form.getEmail() == null || form.getEmail().trim().isEmpty()) {
            errors.put("email", "邮箱不能为空");
        } else if (!form.getEmail().matches("\\w+@\\w+\\.\\w+")) {
            errors.put("email", "邮箱格式错误！");
        }
        if (form.getAge() < 0 || form.getAge() > 80) {
            errors.put("age", "您这什么年龄啊？重新输入吧！");
        }
        if (errors.size() > 0) {
            mv.addObject("user", form);
            mv.addObject("errors", errors);
            mv.setViewName("/forward/modify");
            return mv;
        }
        try {
            userService.updateUser(form);
        } catch (UserException e) {
            mv.addObject("msg",e.getMessage());
            mv.setViewName("/forward/modify");
            return mv;
        }
        request.getSession().setAttribute("session_user",form);
        mv.addObject("msg","修改成功");
        mv.setViewName("/jsps/msg.jsp");
        return mv;
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "/jsps/msg.jsp";
    }
}
