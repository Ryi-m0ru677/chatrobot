package jice.vigortech.chat.robot.modules.sys.office.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.office.dao.OfficeDao;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class OfficeService {

	@Autowired
	OfficeDao officeDao;

	/**
	 * 获取机构列表
	 * @param query
	 * @return
	 */
	public Object getOfficeList(PageQuery query) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		int total = 0;
		try {
			list = officeDao.getOfficeList(query);
			total = officeDao.getOfficeCount(query);
			data.put("officeList", list);
			data.put("total", total);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 根据id删除机构
	 * @param id
	 * @return
	 */
	public ResultCode deleteOfficeById(Integer id) {
		try {
			Map<String,Object> temp = officeDao.getOfficeDetailById(id);
			if(temp==null){
				return ResultCode.OFFICE_NOT_EXITS;
			}
			officeDao.deleteOfficeById(id);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 添加或是修改机构
	 * @param office
	 * @return
	 */
	public ResultCode saveOrUpdateOffice(Office office) {
		try {
			if(office.getId()==null){
				//添加
				office.setCreateBy(SecurityUtils.getCurrentUser());
				officeDao.insertOffice(office);
			}else{
				//修改
				officeDao.updateOffice(office);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 根据id获取机构详情
	 * @param id
	 * @return
	 */
	public Object getOfficeDetailById(Integer id) {
		try {
			Map<String,Object> temp = officeDao.getOfficeDetailById(id);
			if(temp==null){
				return ResultCode.OFFICE_NOT_EXITS;
			}
			return temp ;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
