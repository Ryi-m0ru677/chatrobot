package jice.vigortech.chat.robot.modules.process.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.process.dao.ProcessDao;
import jice.vigortech.chat.robot.modules.process.entiry.Processes;
import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
@Transactional(readOnly=true)
public class ProcessService {
	
	@Autowired
	ProcessDao proDao;
	
	/**
	 * 获取流程列表
	 * @param query
	 * @return
	 */
	public Object getProcessList(PageQuery query) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		int total=0;
		try {
			list = proDao.getProcessList(query);
			total = proDao.getProcessCount(query);
			data.put("processList",list);
			data.put("total",total);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 获取流程详情
	 * @param id
	 * @return
	 */
	public Object getProDetail(Integer id) {
		try {
			Map<String, Object> temp = proDao.getProDetailById(id);
			if(temp==null){
				return ResultCode.PROCESS_NOT_EXIST;
			}
			
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 添加或是修改流程
	 * @param process
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode saveOrUpdatePro(Processes process) {
		if(process.getId()==null){
			//添加
			try {
				User user = SecurityUtils.getCurrentUser();
				if(user==null){
					return ResultCode.OPERATION_NOT_PERMITTED;
				}
				process.setCreateBy(user);
				proDao.insertProcess(process);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}else{
			//修改
			try {
				proDao.updateProcess(process);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}
	}
	
	/**
	 * 根据流程id删除流程
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode deleteProcessById(Integer id) {
		try {
			if(proDao.getProDetailById(id)==null){
				return ResultCode.PROCESS_NOT_EXIST;
			}
			proDao.deleteProcessById(id);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
