package jice.vigortech.chat.robot.modules.process.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.process.entiry.Processes;
import jice.vigortech.chat.robot.modules.process.service.ProcessService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/process",method={RequestMethod.POST})
public class ProcessController extends BaseController {

	@Autowired
	ProcessService proService;
	
	@RequestMapping("/list")
	public String getProcessList(PageQuery query){
		data=proService.getProcessList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getProDetailById(@RequestParam("id") Integer id){
		data = proService.getProDetail(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/save")
	public String save(Processes process){
		data = null;
		resCode = proService.saveOrUpdatePro(process);		
		return Result();
	}
	
	@RequestMapping("/delete")
	public String deleteProcessById(@RequestParam("id") Integer id){
		data = null;
		resCode = proService.deleteProcessById(id);
		return Result();
	}
	
}
