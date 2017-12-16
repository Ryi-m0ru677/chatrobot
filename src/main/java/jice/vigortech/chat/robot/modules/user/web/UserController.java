package jice.vigortech.chat.robot.modules.user.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
@Controller
@RequestMapping(path=SysConstants.SYS_URL, method=RequestMethod.POST)
public class UserController extends BaseController{

	@RequestMapping("user_list")
	public String getUserList(Model model){
		resCode = null;
		data = null;
		model.addAttribute("result", Result());
		return "user_list";
	}
	
}
