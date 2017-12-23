package jice.vigortech.chat.robot.modules.intents.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.intents.entity.Ask;
import jice.vigortech.chat.robot.modules.intents.entity.Entity;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.Slot;

@Mapper
public interface IntentDao {
	//添加场景
	@Insert("insert into robot_scene "
			+ "("
			+ "app_id, name, rank,answer, act_name, create_by, create_date,update_date) "
			+ "values(#{appId}, #{name}, #{rank},#{answer}, #{actionName}, #{createBy}, "
			+ "#{createDateString},#{updateDateString}) "
			)
	Integer insertIntent(Intents intent);
	@Insert("insert into robot_scene_ask( "
			+ "text,intent) "
			+ "values( "
			+ "#{text},#{intent})"
			)
	int insertAsk(Ask ask);
	@Insert("insert into robot_scene_ask_entitys ("
			+ "ask_id,start,end,value,entity) "
			+ "values(#{askId},#{start},#{end},#{value},#{entity})"
			)
	int insertEntity(Entity entity);
	
	@Insert("insert into robot_scene_slot("
			+ "int_id,flag,type,type_name,`message`,def_value) "
			+ "values(#{intentId}, #{flag}, #{type}, #{typeName}, #{message},#{defValue} )")
	Integer insertAction(Slot action);
	
	
	//删除场景
	@Update("update robot_scene set del_flag = 1 where id = ${id}")
	Integer deleteIntent(@Param("id")Integer id);
	@Update("update robot_scene_slot set del_flag = 1 where int_id = ${id}")
	Integer deleteSlot(@Param("id")Integer id);
	@Update("update robot_scene_ask_entitys set del_flag=1 where ask_id=${id}")
	int deleteEntityListByAId(@Param("id")Integer aid);
	@Update("update robot_scene_ask set del_flag=1 where intent=#{name}")
	int deleteAskByName(@Param("name")String iname);
	
	//更新场景
	@Update("update robot_scene set name=#{name},rank=#{rank},action_name=#{actionName}, "
			+ "update_date=#{updateDateString} where id=#{id}"
			)
	int updateIntent(Intents intent);
	@Update(
			"update robot_scene_ask set text=#{text} "
			+ "where id = #{id}"
			)
	int updateAsk(Ask ask);
	@Update("update robot_scene_ask_entitys set start=#{start}, "
			+ "end=#{end},value=#{value},entity=#{entity} "
			+ "where id=#{id}")
	int updateEntity(Entity entity);
	@Update(
			 "update robot_scene_slot set type=#{tyep},type_name=#{typeName}, "
			+ "`message`=#{message},flag=#{flag},del_value=#{delValue} "
			+ "where id = #{id}"
			)
	int updateAction(Slot slot);
	
	
	//查询场景信息
	@Select("select id id,app_id appId,name name,rank rank,act_name actionName, "
			+ "update_date updateDate "
			+ "from robot_scene "
			+ "where del_flag=0 and id=${id} "
			)
	Map<String,Object> getIntentById(@Param("id")Integer id);
	
	@Select("<script>"
			+ "select id id,name name,update_date updateDate from robot_scene where del_flag=0 and app_id=${id} "
			+ "<if test=\"name != null and name != ''\">"
			+ "and name like concat('%', #{name}, '%')  "
			+ "</if>"
			+ "order by update_date desc "
			+ "</script>")
	List<Map<String, Object>> getIntentList(@Param("name")String name,@Param("id") Integer id);
	
	@Select("select id id ,text text,intent intent from robot_scene_ask where intent=#{name} and del_flag=0")
	List<Map<String, Object>> getAskListByIName(@Param("name") String name);
	@Select("select `start` ,`end`,`value`,entity from robot_scene_ask_entitys where del_flag=0 "
			+ "and ask_id=${id} order by id")
	List<Map<String, Object>> getEntityListByAId(@Param("id")Integer id);
	
	@Select(" select id id ,flag flag,dict_name dictName,type_name typeName,`message` message, "
			+ "def_value defValue from robot_scene_slot where del_flag=0 and int_id=${id} "
			+ "order by id")
	List<Map<String, Object>> getActionListByIid(@Param("id")Integer id);
	
	@Select("select id ,name from robot_scene where del_flag=0 and app_id=${appId}")
	List<Intents> getIntentByAppId(@Param("appId")Integer appId);
	
	@Select("select id ,name from robot_scene where del_flag=0 and name=#{name}")
	Map<String,Object> checkByName(@Param("name")String name);
	
}
