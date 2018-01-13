package jice.vigortech.chat.robot.modules.sys.office.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;
import jice.vigortech.chat.robot.modules.sys.office.service.OfficeService;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/office", method={RequestMethod.POST})
public class OfficeController extends BaseController {

	@Autowired
	OfficeService officeService;
	
	@RequestMapping("/list")
	public String getOfficeList(PageQuery query){
		data = officeService.getOfficeList(query);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}
		resCode = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
	
	@RequestMapping("/delete")
	public String deleteOfficeById(Integer id){
		data = null;
		resCode = officeService.deleteOfficeById(id);
		return Result();
	}
	
	@RequestMapping("/save")
	public String saveOrUpdateOffice(Office office){
		data = null;
		resCode = officeService.saveOrUpdateOffice(office);
		return Result();
	}
	
	@RequestMapping("/detail")
	public String getOfficeDetail(Integer id){
		data = officeService.getOfficeDetailById(id);
		if(data instanceof ResultCode){
			resCode = (ResultCode) data;
			data = null;
		}
		resCode  = ResultCode.OPERATION_SUCCESSED;
		return Result();
	}
}
