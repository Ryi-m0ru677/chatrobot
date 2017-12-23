package jice.vigortech.chat.robot.modules.dicts.web;

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
import jice.vigortech.chat.robot.modules.dicts.entity.Dicts;
import jice.vigortech.chat.robot.modules.dicts.service.DictService;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/dict", method={RequestMethod.GET})
public class DictController extends BaseController{

	@Autowired
	DictService dictService;
	@RequestMapping("/list")
	public String getDicList(@RequestParam("name") String name,@RequestParam("appId") Integer appId){
		data = dictService.getDicList(name,appId);
		resCode = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
	
	@RequestMapping("/del")
	public String deleteDict(@RequestParam("id") Integer id){
		data=null;
		resCode = dictService.deleteDictById(id);
		return Result();
	}
	
	@RequestMapping("/add")
	public String addDict(@Validated Dicts dict, @RequestParam("appId") Integer appId,BindingResult result){
		if(result.hasErrors()){
			setErrorResultCode(result);
		}else{
			try {
				data = dictService.insertDict(dict,appId);
				if(data instanceof ResultCode){
					resCode = (ResultCode) data;
					data=null;
				}
			} catch (Exception e) {
				resCode = ResultCode.OPERATION_FAILED;
				data = null;
			}
		}
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getDictDetail(@RequestParam("id")Integer id){
		try {
			data = dictService.getDictDetail(id);
			if(data instanceof ResultCode){
				resCode = (ResultCode) data;
				data=null;
			}else{
				resCode = ResultCode.OPERATION_SUCCESSED;
			}
		} catch (Exception e) {
			e.printStackTrace();
			data=null;
		}
		return Result();
	}
}
