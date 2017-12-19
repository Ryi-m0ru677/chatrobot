package jice.vigortech.chat.robot.modules.application.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.application.entity.Application;

@Service
public class AppService {

	@Autowired
	AppDao appDao;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultCode delApp(Integer id) {
		Application app = appDao.findAppById(id);
		if(app==null){
			return ResultCode.APP_NOT_EXIST;
		}
		appDao.deleteApp(id);
		return ResultCode.OPERATION_SUCCESSED;
	}
	/**
	 * 添加或是修改
	 * @param app
	 * @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultCode addApp(Application app) {
		if(StringUtils.isNotEmpty(app.getId()+"")){
			//新增
			try {
				appDao.insertApp(app);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				return ResultCode.OPERATION_FAILED;
			}
		}
		
		//修改
		//TODO 加一个标识
		Application temp = appDao.findAppById(app.getId());
		temp.setClientToken(app.getClientToken());
		temp.setDefReply(app.getDefReply());
		temp.setDescribe(app.getDescribe());
		temp.setIsPrivate(app.getIsPrivate());
		temp.setLanguage(app.getLanguage());
		temp.setName(app.getName());
		temp.setLink(app.getLink());
		temp.setDevToken(app.getDevToken());
		temp.setUpdateDate(new Date(System.currentTimeMillis()));
		temp.setStorage(app.getStorage());
		try {
			appDao.updateApp(temp);
			return ResultCode.OPERATION_SUCCESSED;
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	/**
	 * 得到应用列表
	 * @return appList
	 */
	public Map<String,Object> getAppList(String name) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list  = appDao.getAllAppList(name);
		//总数，余数
		data.put("list", list);
		return data;
	}
	/**
	 * 得到应用明细
	 * @return
	 */
	public Object getAppDetail(Integer id) {
		//权限的校验
		Map<String,Object> detail = appDao.getAppById(id);
		return detail;
	}
}
