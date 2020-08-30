package cn.myh.system.controller;

import cn.myh.system.domain.User;
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
import java.util.List;
import java.util.Map;

/**
 * description: AdminController <br>
 * date: 2020/8/30 9:16 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@Component
@RequestMapping("/admin")
public class AdminController {
    @Resource(name="userService")
    private UserService userService;

    @RequestMapping("/login")
    public void adminLogin(User form, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String,String> errors = new HashMap<>();
        if(form.getUsername() == null || form.getUsername().trim().isEmpty()){
            errors.put("username","用户名不能为空");
        }
        if(form.getPassword() == null || form.getPassword().trim().isEmpty()){
            errors.put("password","密码不能为空");
        }
        if (errors.size()>0){
            request.setAttribute("user",form);
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/adminjsps/adminLogin.jsp").forward(request,response);
            return;
        }
        if(form.getUsername().equals("hgmyh4977") && form.getPassword().equals("443177314")){
            request.getSession().setAttribute("session_admin",form);
            request.getRequestDispatcher("/forward/adminSource").forward(request,response);
            return;
        }else{
            request.setAttribute("msg","管理员用户名或密码错误！");
            request.getRequestDispatcher("/adminjsps/adminLogin.jsp").forward(request,response);
            return;
        }
    }
    @RequestMapping("/findAll")
    public void findAll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<User> userList = userService.findAll();
        request.setAttribute("userList",userList);
        request.getRequestDispatcher("/forward/userList").forward(request,response);
        return;
    }
}
