package jice.vigortech.chat.robot.modules.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.user.entity.User;
import jice.vigortech.chat.robot.modules.user.service.UserService;
@Controller
@RequestMapping(path=SysConstants.SYS_URL+"user", method=RequestMethod.GET)
public class UserController extends BaseController{

	@Autowired
	UserService userService;
	
	@RequestMapping("list")
	public String getUserList(@RequestParam("name")String name){
		
		data = userService.getUserList(name);
		resCode = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
	
	public String addUser(@Validated User user, BindingResult result){
		if(result.hasErrors()){
			setErrorResultCode(result);
		}else{
			data = userService.addUser(user);
			if(data instanceof ResultCode){
				resCode = (ResultCode) data;
				data=null;
			}
		}
		return Result();
	}
}
