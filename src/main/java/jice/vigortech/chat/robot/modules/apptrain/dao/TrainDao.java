package jice.vigortech.chat.robot.modules.apptrain.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jice.vigortech.chat.robot.modules.apptrain.entity.Train;

@Mapper
public interface TrainDao {

	@Select("select id ,app_id appId,`name`,train_date trainDate,local from robot_app_train where del_flag=0 "
			+ "and app_id=${id} ${sql} "
			+ "order by train_date desc")
	List<Map<String,Object>> getTrainAppList(@Param("id")Integer id,@Param("sql")String sql);

	@Select("select id,app_id appId,`name`,train_date trainDate,local from robot_app_train where del_flag=0 "
			+ "${sql} order by train_date desc ")
	List<Map<String, Object>> getAllTrainAppList(@Param("sql")String sql);
	
	@Insert("insert into robot_app_train (app_id,`name`,train_date,local) "
			+ "values(#{appId},#{name},#{trainDate},#{local}) ")
	int insertTrain(Train train);

}
