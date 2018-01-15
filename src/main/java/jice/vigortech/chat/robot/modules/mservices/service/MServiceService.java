package jice.vigortech.chat.robot.modules.mservices.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.mservices.dao.MSerivceDao;
import jice.vigortech.chat.robot.modules.mservices.entity.Iattribute;
import jice.vigortech.chat.robot.modules.mservices.entity.MicService;
import jice.vigortech.chat.robot.modules.mservices.entity.Minterface;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
@Transactional(readOnly=true)
public class MServiceService /*extends BaseService*/{

	@Autowired
	MSerivceDao mServiceDao;
	/**
	 * 获取微服务列表
	 * @param query 
	 * @return
	 */
	public Object getMicServiceList(PageQuery query) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		int total = 0;
		try {
			list =  mServiceDao.getMicServiceList(query);
			total =  mServiceDao.getMicServiceCount(query);
			data.put("mServiceList", list);
			data.put("total", total);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	/**
	 *  添加或是修改微服务
	 * @param micService
	 * @return
	 */
	@Transactional(readOnly=false ,rollbackFor=Exception.class)
	public Object saveMicSerivice(MicService micService) {
		User user = SecurityUtils.getCurrentUser();
		if(user==null){
			return ResultCode.OPERATION_NOT_PERMITTED;
		}
		if(micService.getId()==null){
			//添加
			try{
				save(micService);
				return ResultCode.OPERATION_SUCCESSED;
			}catch(Exception e){
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
			
		}else{
			//修改
			if(mServiceDao.getMicServiceById(micService.getId())==null){
				return ResultCode.MIC_NOT_EXIST;
			}
			delete(micService.getId());
			save(micService);
		}
		return null;
	}
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false ,rollbackFor=Exception.class)
	public ResultCode deleteMicServiceById(Integer id) {
		if(mServiceDao.getMicServiceById(id)==null){
			return ResultCode.MIC_NOT_EXIST;
		}
		try {
			delete(id);
			mServiceDao.deleteMicService(id);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	
	private void save(MicService micService){
		User user = SecurityUtils.getCurrentUser();
		micService.setCreateBy(user);
		if(mServiceDao.saveMicService(micService)>0){
			if(micService.getInterList()!=null){
				for (Minterface temp1 : micService.getInterList()) {
					temp1.setMid(micService.getId());
					if(mServiceDao.saveMinterface(temp1)>0){
						if(temp1.getAttrList()!=null){
							for(Iattribute temp2:temp1.getAttrList()){
								temp2.setIid(temp1.getId());
								mServiceDao.saveArrt(temp2);
							}
						}
					}
				}
			}
		}
	}
	
	private void delete(Integer id) {
		List<Map<String,Object>> interList = mServiceDao.getMinterfaceByMid(id);
		for (Map<String, Object> map : interList) {
			mServiceDao.deleteArrt((Integer)map.get("id"));
		}
		mServiceDao.deleteMinterface(id);
	}
	
}
