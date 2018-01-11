package jice.vigortech.chat.robot.modules.intents.web;

import org.apache.ibatis.annotations.Param;
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
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.service.IntentService;

@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/intent",method={RequestMethod.POST})
public class IntentController extends BaseController{
	
	@Autowired
	IntentService intentService;
	@RequestMapping("/add")
	public String addIntent(@Validated Intents intent, @Param("appId")Integer appId,BindingResult result){
		if(result.hasErrors()){
			setErrorResultCode(result);
		}else{
			try {
				data = intentService.insertIntent(intent,appId);
				if(data instanceof ResultCode){
					resCode = (ResultCode) data;
					data = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				data=null;
				resCode = ResultCode.OPERATION_FAILED;
			}
		}
		return Result();
	}
	
	@RequestMapping("/del")
	public String deleteIntent(@RequestParam("id") Integer id){
		resCode = intentService.deleteIntent(id);
		data = null;
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getIntentDetail(@RequestParam("id") Integer id){
		try {
			data  = intentService.getIntentDetail(id);
			if(data instanceof ResultCode){
				resCode = (ResultCode) data;
				data = null;
			}
			resCode = ResultCode.OPERATION_SUCCESSED;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			data=null;
			resCode = ResultCode.OPERATION_SUCCESSED;
		}	
		return Result();
	}
	
	@RequestMapping("/list")
	public String getIntentList(@RequestParam("name") String name,@RequestParam("appId") Integer appId){
		data = intentService.getIntentList(name,appId);
		resCode = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
}
