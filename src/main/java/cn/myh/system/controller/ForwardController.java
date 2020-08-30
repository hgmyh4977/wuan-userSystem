package cn.myh.system.controller;

import cn.myh.system.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: ForwardController <br>
 * date: 2020/8/29 22:50 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@Component
@RequestMapping("/forward")
public class ForwardController {
    @RequestMapping("/source")
    public void userSource(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User)request.getSession().getAttribute("session_user");
        if (sessionUser == null){
            request.setAttribute("msg","您还没有登录，无法访问该资源！");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/userSource/source.jsp").forward(request,response);
    }
    @RequestMapping("/userInfo")
    public void userInfo(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User)request.getSession().getAttribute("session_user");
        if (sessionUser == null){
            request.setAttribute("msg","您还没有登录，无法访问该资源！");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/userSource/user.jsp").forward(request,response);
    }

    @RequestMapping("/modify")
    public void modifyJsp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User)request.getSession().getAttribute("session_user");
        if (sessionUser == null){
            request.setAttribute("msg","您还没有登录，无法访问该资源！");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/userSource/modify.jsp").forward(request,response);
        return;
    }

    @RequestMapping("/adminSource")
    public void adminSource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User)request.getSession().getAttribute("session_admin");
        if (sessionUser == null){
            request.setAttribute("msg","您不是管理员，无法访问该资源！");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/adminSource/source.jsp").forward(request,response);
        return;
    }
    @RequestMapping("/userList")
    public void systemUserNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User sessionUser = (User)request.getSession().getAttribute("session_admin");
        if (sessionUser == null){
            request.setAttribute("msg","您不是管理员，无法访问该资源！");
            request.getRequestDispatcher("/jsps/msg.jsp").forward(request,response);
            return;
        }
        request.getRequestDispatcher("/WEB-INF/adminSource/userlist.jsp").forward(request,response);
        return;
    }

}
