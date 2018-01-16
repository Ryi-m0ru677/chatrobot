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

	@Select("<script>"
			+ "select DISTINCT(a.id) ,a.app_id appId,a.name,a.train_date trainDate,a.local, a.create_by createBy "
			+ "from robot_app_train a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "and app_id=${id} ${page.sql} "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and a.name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and a.update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "order by a.train_date desc "
			+ "limit ${page.rowNo}, ${page.pageSize} "
			+ "</script>")
	List<Map<String,Object>> getTrainAppList(@Param("id")Integer id,@Param("page")PageQuery query);

	@Select("<script>"
			+ "select DISTINCT(a.id),a.app_id appId,a.name,a.train_date trainDate,a.local, a.create_by createBy "
			+ "from robot_app_train a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "${page.sql} "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and a.name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and a.update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "order by a.train_date desc "
			+ "limit ${page.rowNo}, ${page.pageSize} "
			+ "</script>")
	List<Map<String, Object>> getAllTrainAppList(@Param("page")PageQuery query);
	
	@Insert("insert into robot_app_train (app_id,`name`,train_date,local,create_by) "
			+ "values(#{appId},#{name},#{trainDate},#{local},#{createBy.username}) ")
	int insertTrain(Train train);
	
	@Select("<script>"
			+ "select count(DISTINCT(a.id)) "
			+ "from robot_app_train a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "  ${page.sql} "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and a.name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and a.update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "</script>")
	int getAllCount(@Param("page")PageQuery query);
	
	@Select("<script>"
			+ "select count(DISTINCT(a.id)) "
			+ "from robot_app_train a "
			+ "LEFT JOIN sys_user uy ON uy.name=a.create_by "
			+ "LEFT JOIN sys_office oy ON oy.id = uy.office_id "
			+ "where a.del_flag=0 "
			+ "and app_id=${id} ${page.sql} "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and a.name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and a.update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "</script>")
	int getAllCountById(PageQuery query, Integer id);

}
