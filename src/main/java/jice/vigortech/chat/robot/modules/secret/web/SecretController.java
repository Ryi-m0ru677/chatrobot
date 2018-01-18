package jice.vigortech.chat.robot.modules.secret.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.secret.entity.Secret;
import jice.vigortech.chat.robot.modules.secret.service.SecretService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/secret", method={RequestMethod.POST})
public class SecretController extends BaseController {

	@Autowired
	SecretService secretService;
	
	@RequestMapping("/list")
	public String getSecretList(PageQuery query){
		data = secretService.getSecretList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/delete")
	public String deleteSecretById(@RequestParam("id")Integer id){
		data = null;
		resCode = secretService.deleteSecretById(id);
		return Result();
	}
	
	@RequestMapping("/save")
	public String saveOrUpdateSecret(Secret secret){
		data = null;
		resCode = secretService.saveOrUpdateSecret(secret);
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getSecretDetail(@RequestParam("id")Integer id){
		data = secretService.getSecretDetailById(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
}
