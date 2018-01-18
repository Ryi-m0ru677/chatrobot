package jice.vigortech.chat.robot.modules.sys.role.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.sys.role.entity.Role;
import jice.vigortech.chat.robot.modules.sys.role.service.RoleService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/role", method={RequestMethod.POST})
public class RoleController extends BaseController {

	@Autowired
	RoleService roleService;
	
	@RequestMapping("/list")
	public String getRoleList(PageQuery query){
		data = roleService.getRoleList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/delete")
	public String deleteRoleById(Integer id){
		data = null;
		resCode = roleService.deleteRoleById(id);
		return Result();
	}
	
	@RequestMapping("/save")
	public String saveOrUpdateRole(Role role){
		data = null;
		resCode = roleService.saveOrUpdateRole(role);
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getRoleDetail(Integer id){
		data = roleService.getRoleDetailById(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/role_user_list")
	public String getRoleUserList(@RequestParam("id")Integer id){
		data = roleService.getRoleUserList(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/role_add_user")
	public String addRoleUser(@RequestParam("userIds")String userIds,@RequestParam("id") Integer id){
		data = null;
		resCode = roleService.addRoleUser(userIds,id);
		return Result();
	}
	
	@RequestMapping("/role_add_others")
	public String addRoleTheme(@RequestParam("themeIds")String themeIds,@RequestParam("secretIds")String secretIds,@RequestParam("id") Integer id){
		data = null;
		resCode = roleService.addRoleThemeAndSecret(themeIds,id,secretIds);
		return Result();
	}
	
	
}
