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

@Mapper
public interface DictDao {
	
	//删除词库
	@Update("update robot_dict set del_flag = 1 where id = ${id}")
	int deleteDictById(@Param("id")Integer id);
	
	@Update("update robot_dict_word set del_flag=1 where dict_id=${id}")
	int deleteWord(@Param("id")Integer id);
	
	
	//添加
	@Insert("insert into robot_dict (app_id,name,pinyin,syno_flag,update_date) "
			+ "values(#{appId},#{name},#{pinyin},#{synonymyFlag},#{updateDateString})")
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
			+ "select id id ,`name` `name`,pinyin from robot_dict where del_flag=0 and app_id=${appId}  "
			+ "<if test=\"name!=null and name=''\">"
			+ "and name like concat('%', #{name},'%')"
			+ "</if> "
			+ "order by id "
			+ "</script>")
	List<Map<String, Object>> getDicList(@Param("name")String name,@Param("appId") Integer appId);
	
	@Select("select id id ,`name` `name`, app_id appId,syno_flag synonymyFlag from robot_dict where del_flag = 0 and id=${id}")
	Map<String,Object> getDictById(@Param("id")Integer id);
	
	@Select("select id, keyword keyword,synonymy synonymy from robot_dict_word where del_flag=0 and dict_id=${id}")
	List<Map<String, Object>> getWordList(@Param("id")Integer id);
	
	@Select("select id from robot_dict where del_flag=0 and app_id=${appId} ")
	List<Dicts> getDictByAppId(@Param("appId")Integer appId);

}
