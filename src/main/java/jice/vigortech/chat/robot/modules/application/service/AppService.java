package jice.vigortech.chat.robot.modules.application.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.application.entity.Application;
import jice.vigortech.chat.robot.modules.dicts.dao.DictDao;
import jice.vigortech.chat.robot.modules.dicts.entity.Dicts;
import jice.vigortech.chat.robot.modules.intents.dao.IntentDao;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
@Transactional(readOnly=true)
public class AppService /*extends BaseService*/{

	@Autowired
	AppDao appDao;
	@Autowired
	IntentDao intentDao;
	@Autowired
	DictDao dictDao;

	/**
	 * 删除应用,级联删除 应用下的场景和词库
	 * 
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultCode delApp(Integer id) {
		Map<String, Object> app = appDao.getAppById(id);
		if (app == null) {
			return ResultCode.APP_NOT_EXIST;
		}
		if (appDao.deleteApp(id) > 0) {
			List<Intents> intentList = intentDao.getIntentByAppId(id);
			for (Intents intents : intentList) {
				List<Map<String, Object>> askList = intentDao.getAskListByIName(intents.getName());
				for (Map<String, Object> map : askList) {
					intentDao.deleteEntityListByAId((Integer) map.get("id"));
				}
				intentDao.deleteIntent(intents.getId());
				intentDao.deleteSlot(intents.getId());
			}
			List<Dicts> dictList = dictDao.getDictByAppId(id);
			for (Dicts dicts : dictList) {
				dictDao.deleteDictById(dicts.getId());
				dictDao.deleteWord(dicts.getId());
			}
			return ResultCode.OPERATION_SUCCESSED;
		}
		return ResultCode.OPERATION_FAILED;
	}

	/**
	 * 添加或是修改
	 * 
	 * @param app
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public ResultCode addApp(Application app) {
		if (app.getId() == null) {
			// 新增
			try {
				User user = SecurityUtils.getCurrentUser();
				if(user==null){
					return ResultCode.OPERATION_NOT_PERMITTED;
				}
				app.setCreateBy(user);
				appDao.insertApp(app);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		} else {
			// 修改
			try {
				appDao.updateApp(app);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}

	}

	/**
	 * 得到应用列表
	 * 
	 * @return appList
	 */
	public Object getAppList(PageQuery query) {
		
		//TODO 权限的校验
		/*User user = SecurityUtils.getCurrentUser();
		if(user==null){
			return ResultCode.OPERATION_NOT_PERMITTED;
		}
		if(user.getRole().equalsIgnoreCase(SysConstants.SYS_USER)){
			sql = String.format(SysConstants.SYS_SQL, user.getId());
		}*/
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> list = appDao.getAllAppList(query);
		// 总数，余数
		
		data.put("list", list);
		data.put("total",appDao.getAppCount(query));
		return data;
	}

	/**
	 * 得到应用明细
	 * 
	 * @return
	 */
	public Object getAppDetail(Integer id) {
		// 权限的校验
		Map<String, Object> detail = appDao.getAppById(id);
		return detail;
	}
}
