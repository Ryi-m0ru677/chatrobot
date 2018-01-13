package jice.vigortech.chat.robot.modules.sys.office.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;

@Mapper
public interface OfficeDao {
	
	@Select("<script>"
			+ "select id,`name`,type,parent_id parent,parent_ids parentId, create_by createBy, "
			+ "create_date createDate,update_date updateDate "
			+ "from sys_office "
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
	List<Map<String, Object>> getOfficeList(PageQuery query);

	@Select("<script>"
			+ "select count(1) "
			+ "from sys_office "
			+ "where del_flag=0 "
			+ "<if test=\"name != null and name != ''\">"
			+ "and name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "<if test=\"date != null and date != ''\">"
			+ "and update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "</script>")
	int getOfficeCount(PageQuery query);

	@Select("select id,`name`,type,parent_id parent,parent_ids parentIds,create_by createBy, "
			+ "create_date createDate,update_date updateDate "
			+ "from sys_office where del_flag=0 and id=${id}")
	Map<String, Object> getOfficeDetailById(@Param("id")Integer id);

	@Update("update sys_office set del_flag=0 and id=${id}")
	int deleteOfficeById(@Param("id")Integer id);

	@Update("update sys_office set name=#{name},type=#{type},parent_id=#{parent} "
			+ "parent_ids=#{parentIds},update_date=#{updateDateString} where id=#{id}")
	int updateOffice(Office office);

	@Insert("insert into sys_office (`name`,type,parent_id,parent_ids,create_by,create_date,update_date) "
			+ "#{name},#{type},#{parent},#{parentIds},#{createBy.username}, "
			+ "#{updateDateString},#{updateDateString}")
	int insertOffice(Office office);

}
