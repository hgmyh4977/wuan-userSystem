import cn.myh.system.domain.User;
import cn.myh.system.service.UserException;
import cn.myh.system.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.transform.Source;
import java.util.List;
import java.util.UUID;

/**
 * description: UserServiceTest <br>
 * date: 2020/8/29 20:36 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
public class UserServiceTest {
    private UserService userService;
    @Before
    public void before(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        userService = ac.getBean("userService",UserService.class);
    }

    @Test
    public void testLogin(){
        User form = new User();
        form.setUsername("admin");
        form.setPassword("admin");
        User user = null;
        try {
            user = userService.login(form);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(user);
    }

    // @Test
    public void testRegist(){
        User form = new User();
        String uuid = UUID.randomUUID().toString().replace("-","").toUpperCase();
        form.setUid(uuid);
        form.setUsername("admin123888");
        form.setPassword("admin");
        form.setEmail("admin123@qq.com");
        form.setGender("male");
        form.setAge(88);
        try {
            userService.regist(form);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testDelete(){
        String uid = "A0E73458DF2D48029123C64D73F282FD";
        userService.deleteUserById(uid);
    }
    @Test
    public void testUpdate(){
        User form = new User();
        form.setUid("777777");
        form.setUsername("zhangSan");
        form.setPassword("admin123");
        form.setEmail("admin@qq.com");
        form.setGender("male");
        form.setAge(88);
        try {
            userService.updateUser(form);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testFindUser(){
        String uname = "admin";
        User user = userService.findUser(uname);
        System.out.println(user);
    }
    @Test
    public void testFindAll(){
        List<User> users = userService.findAll();
        for (User user : users) {
            System.out.println(user);

        }
    }
}
