package cn.myh.system.service;

import cn.myh.system.dao.IUserDao;
import cn.myh.system.domain.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * description: UserService <br>
 * date: 2020/8/29 20:09 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@Service("userService")
public class UserService {
    @Resource(name="userDao")
    private IUserDao userDao;

    public User login(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if(user == null){
            throw new UserException("用户名或密码错误！登录失败");
        }else if(!user.getPassword().equals(form.getPassword())){
            throw new UserException("用户名或密码错误！登录失败");
        }
        return user;
    }
    public void regist(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        if(user != null){
            throw new UserException("用户名已存在，请重新注册！");
        }
        user = userDao.findByEmail(form.getEmail());
        if(user != null){
            throw new UserException("邮箱已存在，请重新注册！");
        }
        userDao.add(form);
    }

    public void deleteUserById(String uid){
        userDao.deleteById(uid);
    }

    public void updateUser(User form) throws UserException {
        User user = userDao.findByUsername(form.getUsername());
        String email = user.getEmail();
        //校验用户是否邮箱存在变更，若变更则验证新邮箱是否已被使用
        if(!form.getEmail().equalsIgnoreCase(email)){
            user = userDao.findByEmail(form.getEmail());
            if(user != null){
                throw new UserException("邮箱重复，更新用户信息失败！");
            }
        }
        userDao.update(form);
    }

    public User findUser(String uname){
        return userDao.findByUsername(uname);
    }
    public List<User> findAll(){
        return userDao.findAll();
    }
}
