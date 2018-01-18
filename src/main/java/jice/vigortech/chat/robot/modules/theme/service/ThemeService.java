package jice.vigortech.chat.robot.modules.theme.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.theme.dao.ThemeDao;
import jice.vigortech.chat.robot.modules.theme.entity.Theme;

@Service
@Transactional(readOnly=true)
public class ThemeService extends BaseService{

	@Autowired
	ThemeDao themeDao;
	/**
	 * 获取主题列表
	 * @param query
	 * @return
	 */
	public Object getThemeList(PageQuery query) {
		try {
			query.setSql(super.dataScopeFilter(SecurityUtils.getCurrentUser(), "oy", "uy"));
			list = themeDao.getThemeList(query);
			total = themeDao.getThemeCount(query);
			data.put("total",total);
			data.put("roleList",list);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode deleteThemeById(Integer id) {
		try {
			Map<String,Object> temp = themeDao.getThemeDetailById(id);
			if(temp==null){
				return ResultCode.THEME_NOT_EXITS;
			}
			themeDao.deleteThemeById(id);
			themeDao.deleteRoleTheme(id);//删除与角色之间的关系
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 保存或是修改主题
	 * @param theme
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode saveOrUpdateTheme(Theme theme) {
		try {
			if(theme.getId()==null){
				//添加
				theme.setCreateBy(SecurityUtils.getCurrentUser());
				themeDao.insertTheme(theme);
			}else{
				//修改
				themeDao.updateTheme(theme);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 获取主题详情
	 * @param id
	 * @return
	 */
	public Object getThemeDetailById(Integer id) {
		try {
			Map<String,Object> temp = themeDao.getThemeDetailById(id);
			if(temp==null){
				return ResultCode.THEME_NOT_EXITS;
			}
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
