package jice.vigortech.chat.robot.modules.dicts.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import jice.vigortech.chat.robot.modules.dicts.entity.Dicts;
import jice.vigortech.chat.robot.modules.dicts.entity.Synonymy;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Mapper
public interface DictDao {
	
	//删除词库
	@Update("update robot_dict set del_flag = 1 where id = ${id}")
	int deleteDictById(@Param("id")Integer id);
	
	@Update("update robot_dict_word set del_flag=1 where dict_id=${id}")
	int deleteWord(@Param("id")Integer id);
	
	
	//添加
	@Insert("insert into robot_dict (app_id,name,pinyin,syno_flag,create_date,update_date) "
			+ "values(#{appId},#{name},#{pinyin},#{synonymyFlag},#{updateDateString},#{updateDateString})")
	@SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
	int insertDict(Dicts dict);
	@Insert("insert into robot_dict_word (dict_id,keyword,synonymy) "
			+ "values(#{dictId},#{keyword},#{synonymy})")
	int insertDictWord(Synonymy syn);
	
	//更新
	@Update("update robot_dict set name=#{name},syno_flag=#{synonymyFlag},update_date=#{updateDateString} where id=#{id}")
	int updateDict(Dicts dict);
	@Update("update robot_dict_word set keyword=#{keyword},synonymy=#{synonymy} where id=#{id}")
	int updateDictWord(Synonymy syn);
	//查询词库信息
	@Select("<script>"
			+ "select id id ,`name`,create_date createDate,update_date updateDate,pinyin from robot_dict where del_flag=0 and app_id=${appId}  "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "order by id "
			+ "limit ${page.rowNo}, ${page.pageSize} "
			+ "</script>")
	List<Map<String, Object>> getDicList(@Param("appId")Integer appId,@Param("page") PageQuery query);
	
	@Select("<script>"
			+ "select count(1) from robot_dict where del_flag=0 and app_id=${appId}  "
			+ "<if test=\"page.name != null and page.name != ''\">"
			+ "and name like concat('%', #{page.name}, '%') "
			+ "</if> "
			+ "<if test=\"page.date != null and page.date != ''\">"
			+ "and update_date like concat('%', #{page.date}, '%') "
			+ "</if> "
			+ "</script>")
	int getDicCount(@Param("page")PageQuery query,@Param("appId") Integer appId);
	
	@Select("select id id ,`name` `name`, app_id appId,syno_flag synonymyFlag from robot_dict where del_flag = 0 and id=${id}")
	Map<String,Object> getDictById(@Param("id")Integer id);
	
	@Select("select id, keyword keyword,synonymy synonymy from robot_dict_word where del_flag=0 and dict_id=${id}")
	List<Map<String, Object>> getWordList(@Param("id")Integer id);
	
	@Select("select id from robot_dict where del_flag=0 and app_id=${appId} ")
	List<Dicts> getDictByAppId(@Param("appId")Integer appId);


}
