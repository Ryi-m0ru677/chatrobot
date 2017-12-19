package jice.vigortech.chat.robot.modules.intents.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.RobotAction;
import jice.vigortech.chat.robot.modules.intents.entity.Talk;

@Mapper
public interface IntentDao {

	@Insert("insert into robot_scene "
			+ "("
			+ "int_id, name, rank, act_name, create_by, create_date, update_by, update_date) "
			+ "values(#{appId}, #{name}, #{rank}, #{actionName}, #{createBy}, "
			+ "#{createDateString},#{updateBy},#{updateDateString}) "
			)
	Integer insertIntent(Intents intent);
	@Insert("insert into robot_scene_talk( "
			+ "int_id,ask,answer,) "
			+ "values( "
			+ "#{intentId},#{ask},#{answer})"
			)
			
	Integer insertTalk(Talk talk);
	@Insert("insert into robot_scene_action("
			+ "int_id,flag,type,type_name,values,message,def_value,) "
			+ "values(#{intentId}, #{flag}, #{type}, #{typeName}, #{values}, #{message},#{defValue} )")
	Integer insertAction(RobotAction action);

}
