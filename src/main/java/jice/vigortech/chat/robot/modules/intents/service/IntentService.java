package jice.vigortech.chat.robot.modules.intents.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.intents.dao.IntentDao;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.RobotAction;
import jice.vigortech.chat.robot.modules.intents.entity.Talk;

@Service
public class IntentService {
	@Autowired
	AppDao appDao;
	@Autowired
	IntentDao intentDao;
	/**
	 * 
	 * @param intent
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultCode insertIntent(Intents intent, Integer id) {
		 
		if(appDao.findAppById(id)==null){
			return ResultCode.APP_NOT_EXIST;
		}
		try {
			intent.setAppId(id);
			for (Talk talk : intent.getTalk()) {
				talk.setIntentsId(intent.getId());
				intentDao.insertTalk(talk);
			}
			for(RobotAction action :intent.getAction()){
				action.setIntentId(intent.getId());
				intentDao.insertAction(action);
			}
			intentDao.insertIntent(intent);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	
}
