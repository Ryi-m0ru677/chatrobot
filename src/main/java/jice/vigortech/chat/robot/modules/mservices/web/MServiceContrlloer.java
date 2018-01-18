package jice.vigortech.chat.robot.modules.mservices.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.mservices.entity.MicService;
import jice.vigortech.chat.robot.modules.mservices.service.MServiceService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/mic_service", method={RequestMethod.POST})
public class MServiceContrlloer extends BaseController {

	@Autowired
	MServiceService mServiceService;
	/**
	 * 获取微服务接口列表
	 * @param query
	 * @return
	 */
	@RequestMapping("/list")
	public String getMicServiceList(PageQuery query){
		data = mServiceService.getMicServiceList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data=null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	/**
	 * 添加/修改微服务
	 * @param micService
	 * @return
	 */
	@RequestMapping("/save")
	public String addAndUpdateMicService(MicService micService){
		data=null;
		resCode = mServiceService.saveMicSerivice(micService);
		return Result();
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	
	@RequestMapping("delete")
	public String deleteMicService(@RequestParam("id")Integer id){
		data = null;
		resCode = mServiceService.deleteMicServiceById(id);
		return Result();
	}
}
