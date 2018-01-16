package jice.vigortech.chat.robot.modules.sys.role.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.sys.role.entity.Role;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Mapper
public interface RoleDao {
	
	@Select("<script>"
			+ "select a.id,a.name,a.enname,a.data_scope dateScope, a.create_by createBy, "
			+ "a.create_date createDate,a.update_date updateDate "
			+ "from sys_role a"
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where del_flag=0 ${sql} "
			+ "<if test=\"name != null and name != ''\">"
			+ "and a.name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and a.update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "order by a.update_date desc "
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
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	int InsertRole(Role role);

	@Insert("insert into sys_role_office(role_id,office_id) "
			+ "values(${roleId},${officeId})")
	int insert(@Param("officeId")Integer id,@Param("roleId") Integer id2);

	@Update("update sys_role set name=#{name},enname=#{enName}, "
			+ "date_scope=#{dateScope},update_date=#{updateDateString} "
			+ "where id=#{id}")
	int updateRole(Role role);
	
	@Select("select id,name,enname enName,data_scope dataScope "
			+ "from sys_role where del_flag=0 and id in "
			+ "(SELECT role_id rid from sys_user_role where user_id =${id}) ")
	List<Role> getRoleByUser(@Param("id")Integer id);

	@Delete("delete from sys_role_office where role_id=${id}")
	int deleteOfficeByRoleId(@Param("id")Integer id);
	

}
