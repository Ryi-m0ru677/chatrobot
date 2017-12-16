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
			//+ "and login_name=#{name} "
			)
	User getUserByUserName(@Param("name")String username);
	@Select("select name name, password password, role role, id id "
			+ "from sys_user where del_flag=0 "
			//+ "and login_name=#{name} "
			)
	User getUser();
	
	@Select("select distinct c.name "
			+ "from sys_user_role a "
			+ "join sys_role_group_detail b "
			+ "on a.role_group_id = b.group_id "
			+ "join sys_base_role c "
			+ "on b.role_id = c.id "
			+ "where a.user_id = ${userId} and c.name is not null "
			+ "order by c.sort ")
	List<String> getAuthRoleById(@Param("userId") Integer userId);
	
	@Select("select name "
			+ "from sys_role "
			+ "where name is not null "
			+ "order by sort ")
	List<String> getSuperAuthRole();
}
