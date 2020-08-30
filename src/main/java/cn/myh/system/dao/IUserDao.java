package cn.myh.system.dao;

import cn.myh.system.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: IUserDao <br>
 * date: 2020/8/29 20:07 <br>
 * author: myh <br>
 * version: 1.0 <br>
 */
@Repository("userDao")
public interface IUserDao {

    @Insert("insert into users values(#{uid},#{username},#{password},#{email},#{age},#{gender})")
    public void add(User user);

    @Delete("delete from users where uid=#{uid}")
    public void deleteById(String uid);

    @Update("update users set username=#{username},password=#{password},email=#{email}," +
            "age=#{age},gender=#{gender} where uid=#{uid} ")
    public void update(User user);

    @Select("select * from users")
    public List<User> findAll();

    @Select("select * from users where username=#{name}")
    public User findByUsername(String name);

    @Select("select * from users where email=#{email}")
    public User findByEmail(String email);
}
