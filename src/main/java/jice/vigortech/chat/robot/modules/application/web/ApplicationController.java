package jice.vigortech.chat.robot.modules.application.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.application.entity.Application;
import jice.vigortech.chat.robot.modules.application.service.AppService;

@RestController
@RequestMapping(path = SysConstants.SYS_URL+"/app",method={RequestMethod.POST})
public class ApplicationController extends BaseController{
	/**
	 * 获取应用列表
	 * @return
	 */
	@Autowired 
	AppService appService;
	@RequestMapping("/list")
	public String getAppList(/*PageQuery query,*/@RequestParam("name") String name){
		//TODO加上user当参数做数据权限
		data=appService.getAppList(name);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data=null;
		}
		resCode = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
	@RequestMapping("/del")
	public String delApp(@RequestParam("id") Integer id){
		resCode = appService.delApp(id); 
		data = null;
		return Result();
	}
	@RequestMapping("/add")
	public String addApp(@Validated Application app , BindingResult result){
		if(result.hasErrors()){
			setErrorResultCode(result);
		}else{
			try {
				data = appService.addApp(app);
				if(data instanceof ResultCode) {
					resCode = (ResultCode) data;
					data = null;
				} else {
					resCode = ResultCode.OPERATION_SUCCESSED;
				}
			} catch (Exception e) {
				e.printStackTrace();
				data = null;
				resCode = ResultCode.OPERATION_FAILED;
			}
		}
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getAppDetail(@RequestParam("id") Integer id){
		try{
			
			data = appService.getAppDetail(id);
			if(data instanceof ResultCode){
				data =null;
				resCode = (ResultCode) data;
			}else{
				resCode = ResultCode.OPERATION_SUCCESSED;
			}
		}catch(Exception e){
			e.printStackTrace();
			data =null;
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
}
