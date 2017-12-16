package jice.vigortech.chat.robot.modules.application.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;

@RestController
@RequestMapping(path = SysConstants.SYS_URL+"/app")
public class ApplicationController extends BaseController{
	/**
	 * 获取应用列表
	 * @return
	 */
	@RequestMapping("list")
	public String getAppList(PageQuery query){
		
		data=null;
		
		return Result();
	}
}
