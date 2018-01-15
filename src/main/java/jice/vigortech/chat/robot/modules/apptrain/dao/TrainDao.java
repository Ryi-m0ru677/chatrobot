package jice.vigortech.chat.robot.modules.apptrain.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import jice.vigortech.chat.robot.modules.apptrain.entity.Train;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Mapper
public interface TrainDao {

	@Select("select id ,app_id appId,`name`,train_date trainDate,local from robot_app_train where del_flag=0 "
			+ "and app_id=${id} ${page.sql} "
			+ "order by train_date desc "
			+ "limit ${page.rowNo}, ${page.pageSize}")
	List<Map<String,Object>> getTrainAppList(@Param("id")Integer id,@Param("page")PageQuery query);

	@Select("select id,app_id appId,`name`,train_date trainDate,local from robot_app_train where del_flag=0 "
			+ "${sql} order by train_date desc "
			+ "limit ${rowNo}, ${pageSize} ")
	List<Map<String, Object>> getAllTrainAppList(PageQuery query);
	
	@Insert("insert into robot_app_train (app_id,`name`,train_date,local,create_by) "
			+ "values(#{appId},#{name},#{trainDate},#{local},#{createBy.username}) ")
	int insertTrain(Train train);

}
