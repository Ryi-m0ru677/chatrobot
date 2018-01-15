package jice.vigortech.chat.robot.modules.apptrain.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.web.BaseController;
import jice.vigortech.chat.robot.modules.apptrain.service.TrainService;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
@RestController
@RequestMapping(path=SysConstants.SYS_URL+"/train", method={RequestMethod.POST})
public class TrainContorller extends BaseController {

	@Autowired
	TrainService trainService;
	
	@RequestMapping("/do")
	public String train(@Param("id")Integer id){
		data = trainService.trainAppById(id);
		if(data==null){
			resCode = ResultCode.OPERATION_FAILED;
		}else{
			resCode = ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
	
	@RequestMapping("/list")
	public String getTrainList(PageQuery query,@Param("id")Integer id){
		data  = trainService.getTrainList(query,id);
		if(data instanceof ResultCode){
			resCode=(ResultCode) data;
			data=null;
		}else{
			resCode=ResultCode.OPERATION_SUCCESSED;
		}
		return Result();
	}
}
