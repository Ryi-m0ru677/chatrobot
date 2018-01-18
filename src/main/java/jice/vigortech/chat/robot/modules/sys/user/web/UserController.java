package jice.vigortech.chat.robot.modules.sys.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.sys.office.service.OfficeService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.user.entity.User;
import jice.vigortech.chat.robot.modules.sys.user.service.UserService;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/user", method=RequestMethod.POST)
public class UserController extends BaseController{

	@Autowired
	UserService userService;
	@Autowired
	OfficeService officeService;
	
	@RequestMapping("/list")
	public String getUserList(PageQuery query){
		data = userService.getUserList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/regist")
	public String addUser(User user){
		
		data = userService.addUser(user);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data=null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/del")
	public String deleteUser(@RequestParam("id") Integer id){
		data=null;
		resCode = userService.deleteUserById(id);
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getUserDetail(@RequestParam("id") Integer id){
		data = userService.getUserDetail(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/to_save")
	public String toSave(){
		data = officeService.getOfficeAllList();
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
}
