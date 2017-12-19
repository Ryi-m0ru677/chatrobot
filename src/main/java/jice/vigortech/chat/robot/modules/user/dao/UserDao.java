package jice.vigortech.chat.robot.modules.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jice.vigortech.chat.robot.modules.user.entity.User;
@Mapper
public interface UserDao {
	@Select("select name name, password password, role role, id id "
			+ "from sys_user where del_flag=0 "
			+ "and name=#{name} "
			)
	User getUserByUserName(@Param("name")String username);
	@Select("select name name, password password, role role, id id "
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
}
