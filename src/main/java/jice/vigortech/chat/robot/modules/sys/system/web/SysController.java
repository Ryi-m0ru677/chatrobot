package jice.vigortech.chat.robot.modules.sys.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;

@Controller
@RequestMapping(path = SysConstants.SYS_URL, method = RequestMethod.POST )
public class SysController extends BaseController{
	/**
	 * 跳转登录页面
	 * @return
	 */
	@RequestMapping(value =("/a"),method=RequestMethod.GET)
	public String index(){
		data = null;
		resCode  =	ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
	
	/*@RequestMapping("/login")
	public String login(){
		
		return Result();
	}*/
	
	
}
