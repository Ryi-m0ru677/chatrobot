package jice.vigortech.chat.robot.modules.secret.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.secret.entity.Secret;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Mapper
public interface SecretDao {
	@Select("<script>"
			+ "select a.id,a.name,a.describe,a.create_by createBy,a.update_date updateDate, a.create_date createDate "
			+ "from sys_secret a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "${sql} "
			+ "<if test=\"name != null and name != ''\">"
			+ "and a.name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and a.update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "order by a.update_date desc "
			+ "limit ${rowNo}, ${pageSize} "
			+ "</script>")
	List<Map<String, Object>> getSecretList(PageQuery query);

	@Select("<script>"
			+ "select count(1) "
			+ "from sys_secret a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "${sql} "
			+ "<if test=\"name != null and name != ''\">"
			+ "and a.name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and a.update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "</script>")
	int getSecretCount(PageQuery query);
	
	@Update("update sys_secret set del_flag=0 where id=${id}")
	int deleteSecretById(@Param("id")Integer id);
	
	@Insert("insert into sys_secret(`name`,`describe`,create_by,update_date,create_date) "
			+ "values(#{name},#{describe},#{createBy.username},#{updateDateString},#{updateDateString})")
	int insertSecret(Secret secret);
	
	@Update("update sys_secret name=#{name},`describe`=#{describe},update_date=#{updateDateString} "
			+ "where id=#{id}")
	int updateSecret(Secret secret);

	@Select("select a.id,a.name,a.describe,a.create_by createBy,a.update_date updateDate, a.create_date createDate "
			+ "from sys_secret a "
			+ "where a.del_flag=0 and a.id=${id}")
	Map<String, Object> getSecretDetailById(@Param("id")Integer id);
	
	@Delete("delete from sys_role_secret where secret_id=${id}")
	int deleteRoleSecret(@Param("id")Integer id);

}
