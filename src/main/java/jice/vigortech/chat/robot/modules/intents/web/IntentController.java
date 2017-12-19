package jice.vigortech.chat.robot.modules.intents.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	public String addIntent(@Validated Intents intent, @Param("id")Integer id,BindingResult result){
		if(result.hasErrors()){
			setErrorResultCode(result);
		}else{
			try {
				data = intentService.insertIntent(intent,id);
				if(data instanceof ResultCode){
					data = null;
					resCode = (ResultCode) data;
				}else{
					resCode = ResultCode.OPERATION_SUCCESSED;
				}
			} catch (Exception e) {
				e.printStackTrace();
				data=null;
				resCode = ResultCode.OPERATION_FAILED;
			}
		}
		return Result();
	}
	
}
