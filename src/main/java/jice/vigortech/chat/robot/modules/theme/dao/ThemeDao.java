package jice.vigortech.chat.robot.modules.theme.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.theme.entity.Theme;

@Mapper
public interface ThemeDao {
	
	@Select("<script>"
			+ "select a.id,a.name,a.describe,a.create_by createBy,a.update_date updateDate, a.create_date createDate "
			+ "from sys_theme a "
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
	List<Map<String, Object>> getThemeList(PageQuery query);

	@Select("<script>"
			+ "select count(1) "
			+ "from sys_theme a "
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
	int getThemeCount(PageQuery query);
	
	@Update("update sys_theme set del_flag=0 where id=${id}")
	int deleteThemeById(@Param("id")Integer id);
	
	@Insert("insert into sys_theme(`name`,`describe`,create_by,update_date,create_date) "
			+ "values(#{name},#{describe},#{createBy.username},#{updateDateString},#{updateDateString})")
	int insertTheme(Theme theme);
	
	@Update("update sys_theme name=#{name},`describe`=#{describe},update_date=#{updateDateString} "
			+ "where id=#{id}")
	int updateTheme(Theme theme);

	@Select("select id,`name`,`describe`,create_by createBy,update_date updateDate,create_date createDate "
			+ "from sys_theme  "
			+ "where del_flag=0 and id=${id}")
	Map<String, Object> getThemeDetailById(@Param("id")Integer id);

	@Delete("delete from sys_role_theme where theme_id=${id}")
	int deleteRoleTheme(@Param("id")Integer id);

}
