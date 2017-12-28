package jice.vigortech.chat.robot.modules.application.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.application.entity.Application;

@Mapper
public interface AppDao {
	
	@Select("select id id ,`name` `name`, is_private isPrivate, `describe` `describe`,  "
			+ "link link, `language` `language`, zone zone, client_token clientToken, "
			+ "dev_token devToken, def_reply defReply, create_by createBy, "
			+ "create_date createDate, update_date updateDate ,storage storage "
			+ "from robot_app where del_flag = 0 "
			+ "and id=${id} "
			)
	Map<String,Object> getAppById(@Param("id")Integer id);
	@Select("select id id ,`name` `name`, is_private isPrivate, `describe` `describe`,  "
			+ "link link, `language` `language`, zone zone, client_token clientToken, "
			+ "dev_token devToken, def_reply defReply, create_by createBy, "
			+ "create_date createDate, update_date updateDate ,storage storage "
			+ "from robot_app where del_flag = 0 "
			+ "and id=${id} "
			)
	Application findAppById(@Param("id")Integer id);

	@Select("<script>"
			+ "select id id, name `name`,update_date "
			+ "from robot_app where del_flag=0 ${sql} "
			+ "<if test=\"name != null and name != ''\">"
			+ "and name like concat('%', #{name}, '%') "
			+ "</if> "
			+ "order by update_date "
			+ "</script>"
			)
	List<Map<String, Object>> getAllAppList(@Param("name") String name,@Param("sql") String sql);
	
	@Update("update robot_app set del_flag=1 where id = ${id}")
	Integer deleteApp(@Param("id")Integer id);

	@Insert("insert into robot_app ( "
			+ "`name`,is_private,`describe`,link,`language`,zone,client_token,dev_token, "
			+ "def_reply,storage,create_by,create_date,update_date ) "
			+ "values( "
			+ "#{name},#{isPrivate},#{describe},#{link},#{language},#{zone}, "
			+ "#{clientToken},#{devToken},#{defReply},#{storage},#{createBy},#{createDateString}, "
			+ "#{updateDateString} ) "
			)
	Integer insertApp(Application app);

	@Update("update robot_app "
			+ "set `name`=#{app.name},is_private=#{app.isPrivate} , "
			+ "`describe`=#{app.describe},link=#{app.link},`language`=#{app.language},zone=#{app.zone}, "
			+ "client_token=#{app.clientToken},dev_token=#{app.devToken}, def_reply=#{app.defReply}, "
			+ "storage=#{app.storage}, update_date = #{app.updateDateString} "
			+ "where id=#{app.id}")
	Integer updateApp(@Param("app")Application app);
	
	@Select("SELECT a.text, a.intent, b.`start`, b.`end`, b.entity, b.`value` "
			+ "FROM robot_scene_ask a JOIN robot_scene_ask_entitys b ON b.ask_id = a.id "
			+ "WHERE a.del_flag = 0 AND b.del_flag = 0 AND a.id IN "
			+ "( SELECT id FROM robot_scene WHERE del_flag = 0 AND app_id = ${id} ) ")
	List<Map<String,Object>> getAppSlot(@Param("id")Integer id);
	
}
