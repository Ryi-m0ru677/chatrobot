package jice.vigortech.chat.robot.modules.theme.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.theme.entity.Theme;
import jice.vigortech.chat.robot.modules.theme.service.ThemeService;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/theme", method={RequestMethod.POST})
public class ThemeController extends BaseController {

	@Autowired
	ThemeService themeService;
	
	@RequestMapping("/list")
	public String getThemeList(PageQuery query){
		data = themeService.getThemeList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/delete")
	public String deleteThemeById(@RequestParam("id")Integer id){
		data = null;
		resCode = themeService.deleteThemeById(id);
		return Result();
	}
	
	@RequestMapping("/save")
	public String saveOrUpdateTheme(Theme theme){
		data = null;
		resCode = themeService.saveOrUpdateTheme(theme);
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getThemeDetail(@RequestParam("id")Integer id){
		data = themeService.getThemeDetailById(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode  = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
}
