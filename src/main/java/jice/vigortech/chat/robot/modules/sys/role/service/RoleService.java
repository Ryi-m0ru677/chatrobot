package jice.vigortech.chat.robot.modules.sys.role.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.role.dao.RoleDao;
import jice.vigortech.chat.robot.modules.sys.role.entity.Role;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class RoleService {

	@Autowired
	RoleDao roleDao;
	
	/**
	 * 获取角色列表
	 * @param query
	 * @return
	 */
	public Object getRoleList(PageQuery query) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> list = null;
		int total = 0;
		try {
			list = roleDao.getRoleList(query);
			total = roleDao.getRoleCount(query);
			data.put("roleList", list);
			data.put("total", total);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 根据角色id删除角色
	 * @param id
	 * @return
	 */
	public ResultCode deleteRoleById(Integer id) {
		try {
			if(roleDao.getRoleById(id)==null){
				return ResultCode.ROLE_NOT_EXITS;
			}
			roleDao.deleteRoleById(id);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 添加或是修改角色
	 * @param role
	 * @return
	 */
	public ResultCode saveOrUpdateRole(Role role) {
		try {
			if(role.getId()==null){
				//添加
				role.setCreateBy(SecurityUtils.getCurrentUser());
				roleDao.InsertRole(role);
			}else{
				//修改
				roleDao.updateRole(role);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 根据id获取角色详情
	 * @param id
	 * @return
	 */
	public Object getRoleDetailById(Integer id) {
		try {
			Map<String,Object> temp = roleDao.getRoleById(id);
			if(temp==null){
				return ResultCode.ROLE_NOT_EXITS;
			}
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
