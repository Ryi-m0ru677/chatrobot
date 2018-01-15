package jice.vigortech.chat.robot.modules.mservices.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.mservices.entity.Iattribute;
import jice.vigortech.chat.robot.modules.mservices.entity.MicService;
import jice.vigortech.chat.robot.modules.mservices.entity.Minterface;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Mapper
public interface MSerivceDao {
	
	
	@Insert("insert into robot_mic_service ("
			+ "`name`,ename,ip,`describe`,create_by,create_date,update_date )"
			+ "values( #{name},#{ename},#{ip},#{describe},#{createBy.username},#{updateDateString},#{updateDateString}"
			+ ") ")
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	int saveMicService(MicService micService);
	
	@Insert("insert into robot_mic_interface(mid,`name`,mic_url,`describe`)"
			+ "values(#{mid},#{name},#{micUrl},#{describe})")
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	int saveMinterface(Minterface minterface);

	@Insert("insert into robot_mic_arrt(iid,`name`,type,`describe`)"
			+ "values(#{iid},#{name},#{type},#{describe})")
	int saveArrt(Iattribute iAttribute);
	@Select("<script> "
			+ "SELECT a.`name` serviceName, a.ename englishName, a.`describe` serviceDescribe, a.ip serviceIP, a.id, "
			+ "b.`describe` InterfaceDescribe, b.`name` InterfaceName, b.mic_url micUrl, "
			+ "c.`name` arrtName, c.type arrtType, c.`describe` arrtDescribe "
			+ "FROM robot_mic_service as a "
			+ "LEFT JOIN robot_mic_interface as  b ON b.mid = a.id "
			+ "LEFT JOIN robot_mic_arrt as c ON b.id = c.iid "
			+ "WHERE a.del_flag = 0  "
			+ "<if test=\"name!=null and name!=''\"> "
			+ "and a.name like concat('%',#{name},'%') "
			+ "</if>"
			+ "<if test=\"date != null and date != ''\">"
			+ "and a.update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "order by a.update_date "
			+ "limit ${rowNo}, ${pageSize} "
			+ "</script>")
	List<Map<String,Object>> getMicServiceList(PageQuery query);
	
	@Select("<script> "
			+ "SELECT count(1) "
			+ "FROM robot_mic_service as a "
			+ "LEFT JOIN robot_mic_interface as  b ON b.mid = a.id "
			+ "LEFT JOIN robot_mic_arrt as c ON b.id = c.iid "
			+ "WHERE a.del_flag = 0  "
			+ "<if test=\"name!=null and name!=''\"> "
			+ "and a.name like concat('%',#{name},'%') "
			+ "</if>"
			+ "<if test=\"date != null and date != ''\">"
			+ "and a.update_date like concat('%', #{date}, '%') "
			+ "</if> "
			+ "</script>")
	int getMicServiceCount(PageQuery query);
	
	@Select("select `name`,ename,`describe`,`ip`,id from robot_mic_service "
			+ "where del_flag=0 and id=${id}")
	Map<String,Object> getMicServiceById(@Param("id")Integer id);
	
	@Select("select id from robot_mic_interface where mid=${id}")
	List<Map<String, Object>> getMinterfaceByMid(@Param("id")Integer id);

	@Delete ("delete from robot_mic_arrt  where iid=${id}")
	int deleteArrt(@Param("id")Integer id);
	
	@Delete("delete from robot_mic_interface where mid=${id}")
	int deleteMinterface(@Param("id")Integer id);
	
	@Update("update robot_mic_service set del_flag=1 where id=${id}")
	int deleteMicService(@Param("id")Integer id);

}
