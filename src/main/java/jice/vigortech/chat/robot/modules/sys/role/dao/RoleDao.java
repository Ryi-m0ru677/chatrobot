package jice.vigortech.chat.robot.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.role.entity.Role;

@Mapper
public interface RoleDao {
	
	@Select("<script>"
			+ "select id,`name`,enname,data_scope dateScope, create_by createBy, "
			+ "create_date createDate,update_date updateDate "
			+ "from sys_role "
			+ "where del_flag=0 "
			+ "<if test=\"name != null and name != ''\">"
			+ "and name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "order by update_date desc "
			+ "limit ${rowNo}, ${pageSize} "
			+ "</script>")
	List<Map<String, Object>> getRoleList(PageQuery query);
	
	@Select("<script>"
			+ "select count(1) "
			+ "from sys_role "
			+ "where del_flag=0 "
			+ "<if test=\"name != null and name != ''\">"
			+ "and name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "</script>")
	int getRoleCount(PageQuery query);
	
	@Select("select id,`name`,enname,data_scope dateScope, create_by createBy, "
			+ "create_date createDate,update_date updateDate "
			+ "from sys_role "
			+ "where del_flag=0 and id=${id}")
	Map<String, Object> getRoleById(@Param("id")Integer id);

	@Update("update sys_role set del_flag=1 where id=${id}")
	int deleteRoleById(@Param("id")Integer id);
	
	@Insert("insert into sys_role(`name`,enname,data_scope,create_by,create_date,update_date) "
			+ "values(#{name},#{enName},#{dateScope},#{createBy.username},#{updateDateString},#{updateDateString})")
	int InsertRole(Role role);

	@Update("update sys_role set name=#{name},enname=#{enName}, "
			+ "date_scope=#{dateScope},update_date=#{updateDateString} "
			+ "where id=#{id}")
	int updateRole(Role role);

}
