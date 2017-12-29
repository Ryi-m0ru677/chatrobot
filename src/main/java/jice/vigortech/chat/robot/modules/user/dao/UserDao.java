package jice.vigortech.chat.robot.modules.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.user.entity.User;
@Mapper
public interface UserDao {
	@Select("select `name` username, `password` password, role role, id id "
			+ "from sys_user where del_flag=0 "
			+ "and name=#{name} "
			)
	User getUserByUserName(@Param("name")String username);
	@Select("select name username, password password, role role, id id "
			+ "from sys_user where del_flag=0 "
			//+ "and login_name=#{name} "
			)
	List<User> getUser();
	
	@Select("SELECT DISTINCT a.`name` FROM sys_menu a "
			+ "JOIN sys_role_menu b ON a.id = b.menu_id "
			+ "JOIN sys_user_role c ON c.role_id = b.role_id "
			+ "WHERE c.user_id = 1 AND a.`name` IS NOT NULL ORDER BY a.sort "
			)
	List<String> getAuthRoleById(@Param("userId") Integer userId);
	
	@Select("select name "
			+ "from sys_menu "
			+ "where name is not null "
			+ "order by sort ")
	List<String> getSuperAuthRole();
	
	@Select("select token from sys_user_config where name=#{name}")
	Map<String, Object> getTokenByUserName(@Param("name")String name);
	
	@Insert("insert into sys_user(`name`,`password`,phone,email,role,create_date,update_date ) "
			+ "values(#{username},#{password},#{phone},#{email},#{role},#{createDateString},#{updateDateString})")
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	int insertUser(User user);
	
	@Insert("insert into sys_user_config(user_id,`name`,token,time) "
			+ "values(#{id},#{name},#{token},#{time})")
	int insertUserConfig(Map<String, Object> userConfig);
	
	@Update("update sys_user set name=#{username},password=#{password},phone=#{phone},update_date=#{updateDateString} "
			+ "email=#{email} where id = #{id}")
	int updateUser(User user);
	
	@Update("update sys_user set password=#{password} where id=${id}")
	int chgPasswd(@Param("password")String newPassword,@Param("id") Integer id);
	
	@Select("select id ,`name` username,phone,email,role,update_date updateDate, create_date createDate from sys_user where del_flag=0 and id=${id}")
	Map<String,Object> getUserById(@Param("id")Integer id);
	
	@Update("update sys_user set del_flag=1 where id=${id}")
	int deleteUserById(@Param("id")Integer id);
	
	@Select("<script>"
			+ "select id, name username,phone,email,update_date updateDate, create_date createDate from sys_user where del_flag=0 "
			+ "<if test=\"name!=null and name!=''\">"
			+ "and name like concat('%',#{name},'%') "
			+ "</if>"
			+ "order by id"
			+ "</script>")
	List<Map<String, Object>> getUserList(@Param("name")String name);
}
