package jice.vigortech.chat.robot.modules.apptrain.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.HttpHelper;
import jice.vigortech.chat.robot.common.util.MySubstringUtil;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.apptrain.dao.TrainDao;
import jice.vigortech.chat.robot.modules.apptrain.entity.Train;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.user.entity.User;

@Service
@Transactional(readOnly=true)
public class TrainService extends BaseService{
	
	@Autowired
	AppDao appDao;
	@Autowired
	TrainDao trainDao;
	
	
	@Transactional(readOnly=false,rollbackFor=Exception.class)		
	public Object trainAppById(Integer id) {
		String appName = (String) appDao.getAppById(id).get("name");
		Map<String,Object> data = new HashMap<String,Object>();
		Map<String,Object> rasa_nlu_data = new HashMap<String,Object>();
		List<Map<String,Object>> list = appDao.getAppSlot(id);
		rasa_nlu_data.put("common_examples", list);
		data.put("rasa_nlu_data", rasa_nlu_data);
		String json = JSON.toJSONString(data);
		try {
			appName = URLEncoder.encode(appName, "utf-8");
			json = URLEncoder.encode(json, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url = String.format(SysConstants.TRAIN_URL, appName, json);
		try {
			String resp = HttpHelper.httpRequest(url, "GET", null);
			String date = MySubstringUtil.getDate(resp);
			Train train = new Train();
			train.setName((String) appDao.getAppById(id).get("name"));
			train.setLocal(resp);
			train.setTrainDate(date);
			train.setAppId(id);
			train.setCreateBy(SecurityUtils.getCurrentUser());
			trainDao.insertTrain(train);
			return resp;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public Object getTrainList(PageQuery query, Integer id) {
		User user = SecurityUtils.getCurrentUser();
		if(user==null){
			return ResultCode.SESSION_INVALID;
		}
		query.setSql(super.dataScopeFilter(user, "oy", "uy"));
		try {
			if(id==null){
				list = trainDao.getAllTrainAppList(query);
				total = trainDao.getAllCount(query);
			}else{
				list = trainDao.getTrainAppList(id,query);
				total = trainDao.getAllCountById(query,id);
			}
			 data.put("trainList", list);
			 data.put("total", total);
			 return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
