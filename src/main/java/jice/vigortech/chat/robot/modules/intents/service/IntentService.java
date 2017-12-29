package jice.vigortech.chat.robot.modules.intents.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.intents.dao.IntentDao;
import jice.vigortech.chat.robot.modules.intents.entity.Ask;
import jice.vigortech.chat.robot.modules.intents.entity.Entity;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.Slot;

@Service
@Transactional(readOnly=true)
public class IntentService {
	@Autowired
	AppDao appDao;
	@Autowired
	IntentDao intentDao;
	/**
	 * 添加/修改 场景
	 * @param intent
	 * @param id 应用id
	 * @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public Object insertIntent(Intents intent, Integer id) {
		intent.setAppId(id);
		if(appDao.getAppById(id)==null){
			return ResultCode.APP_NOT_EXIST;
		}
		
		if(intent.getId()==null){
			//添加
			try {
				if(intentDao.checkByName(intent.getName())!=null){
					return ResultCode.INTENT_HAS_EXIST;
				}
				if(intentDao.insertIntent(intent)>0){
					if(intent.getAskList()!=null){
						for (Ask ask : intent.getAskList()) {
							ask.setIntent(intent.getName());
							String text = ask.getText();
							if(intentDao.insertAsk(ask)>0){
								if(ask.getEntitys()!=null){
									for(Entity entity:ask.getEntitys()){
										entity.setAskId(ask.getId());
										String value = entity.getValue();
										int start = text.indexOf(value);
										int end = start+value.length();
										entity.setStart(start);
										entity.setEnd(end);
										intentDao.insertEntity(entity);
									}
								}
							}
						}
					}
					if(intent.getSlotList()!=null){
						for(Slot Slot :intent.getSlotList()){
							Slot.setIntentId(intent.getId());
							intentDao.insertAction(Slot);
						}
					}
				}
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}
		//修改
		Map<String,Object> old = intentDao.getIntentById(intent.getId());
		if(old==null){
			return ResultCode.INTENT_NOT_EXIST;
		}
		if(intentDao.updateIntent(intent)>=0 ){
			//修改或添加ask
			if(intent.getAskList()!=null){
				for (Ask ask : intent.getAskList()) {
					//添加
					ask.setIntent((String)intentDao.getIntentById(intent.getId()).get("name"));
					if(ask.getId()==null){
						String text = ask.getText();
						if(intentDao.insertAsk(ask)>0){
							
							intentDao.deleteEntityListByAId(ask.getId());
							if(ask.getEntitys()!=null){
								/*for(Entity entity:ask.getEntitys()){
									//entity.getId();
									if(entity.getId()==null){
										entity.setAskId(ask.getId());
										String value = entity.getValue();
										int start = text.indexOf(value);
										int end = start+value.length();
										entity.setStart(start);
										entity.setEnd(end);
										intentDao.insertEntity(entity);
									}else{
										intentDao.updateEntity(entity);
									}
								}*/
								for(Entity entity:ask.getEntitys()){
									entity.setAskId(ask.getId());
									String value = entity.getValue();
									int start = text.indexOf(value);
									int end = start+value.length();
									entity.setStart(start);
									entity.setEnd(end);
									intentDao.insertEntity(entity);
								}
								
							}
						}
					}else{
						//修改
						intentDao.updateAsk(ask);
						if(ask.getEntitys()!=null){
							for(Entity entity:ask.getEntitys()){
								intentDao.updateEntity(entity);
							}
						}
					}
				}
			}
			//添加或修改slot
			if(intent.getSlotList()!=null){
				for(Slot slot :intent.getSlotList()){
					
					if(slot.getId()==null){
						//添加
						slot.setIntentId(intent.getId());
						intentDao.insertAction(slot);
					}
					//修改
					intentDao.updateAction(slot);
				}
			}
			return ResultCode.OPERATION_SUCCESSED;
		}
		return ResultCode.OPERATION_FAILED;
		
	}
	/**
	 * 删除 场景
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public ResultCode deleteIntent(Integer id) {
		//权限验证
		Map<String,Object> intent  = intentDao.getIntentById(id);
		if(intent==null){
			return ResultCode.INTENT_NOT_EXIST;
		}
		if(intentDao.deleteIntent(id)>0){
			String iname = (String) intent.get("name");
			List<Map<String,Object>> askList = intentDao.getAskListByIName(iname);
			for (Map<String, Object> map : askList) {
				Integer aid =  (Integer) map.get("id");
				intentDao.deleteEntityListByAId(aid);
			}
			intentDao.deleteAskByName(iname);
			intentDao.deleteSlot(id);
			return ResultCode.OPERATION_SUCCESSED;
		}
		return ResultCode.OPERATION_FAILED;
	}
	/**
	 * 获取场景列表
	 * @param name 场景的名称
	 * @param id 应用的id
	 * @return
	 */
	public Object getIntentList(String name,Integer id) {
		//TODO 优化
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list = intentDao.getIntentList(name,id);
		data.put("list", list);
		return data;
	}
	/**
	 * 获取场景明细
	 * @param id 场景id
	 * @return
	 */
	public Object getIntentDetail(Integer id) {
		Map<String,Object> intent  = intentDao.getIntentById(id);
		if(intent==null){
			return ResultCode.INTENT_NOT_EXIST;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		String intentName = (String) intent.get("name");
		List<Map<String,Object>> askList = intentDao.getAskListByIName(intentName);
		for (Map<String, Object> map : askList) {
			Integer aid =  (Integer) map.get("id");
			List<Map<String,Object>> entityList = intentDao.getEntityListByAId(aid);
			map.put("entitys", entityList);
		}
		List<Map<String,Object>> slot = intentDao.getActionListByIid(id);
		intent.put("askList", askList);
		intent.put("slotList", slot);
		data.put("intent", intent);
		return data;
	}
}
