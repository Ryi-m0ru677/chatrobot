package jice.vigortech.chat.robot.modules.sys.office.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.office.dao.OfficeDao;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.user.entity.User;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class OfficeService extends BaseService{

	@Autowired
	OfficeDao officeDao;

	/**
	 * 获取机构列表
	 * @param query
	 * @return
	 */
	public Object getOfficeList(PageQuery query) {
		
		
		try {
			List<Map<String,Object>> officeAllList = officeDao.getAllOffice();;
			list = officeDao.getOfficeList(query);
			total = officeDao.getOfficeCount(query);
			
			data.put("list", officeAllList);
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
	@Transactional(readOnly=false,rollbackFor=Exception.class)
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
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode saveOrUpdateOffice(Office office) {
		try {
			if(office.getId()==null){
				//添加
				User user = SecurityUtils.getCurrentUser();
				if(user==null){
					return ResultCode.SESSION_INVALID;
				}
				office.setCreateBy(user);
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
	
	/**
	 * 获取所有的office
	 * @return
	 */
	public Object getOfficeAllList() {
		try {
			list = officeDao.getAllOffice();
			data.put("officeAllList", list); 
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
