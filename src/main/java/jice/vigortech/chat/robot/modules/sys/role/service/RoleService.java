package jice.vigortech.chat.robot.modules.sys.role.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.office.entity.Office;
import jice.vigortech.chat.robot.modules.sys.role.dao.RoleDao;
import jice.vigortech.chat.robot.modules.sys.role.entity.Role;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Service
@Transactional(readOnly=true,rollbackFor=Exception.class)
public class RoleService extends BaseService{

	@Autowired
	RoleDao roleDao;
	
	
	/**
	 * 获取角色列表
	 * @param query
	 * @return
	 */
	public Object getRoleList(PageQuery query) {
		try {
			query.setSql(super.dataScopeFilter(SecurityUtils.getCurrentUser(), "oy", "uy"));
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
			roleDao.deleteOfficeByRoleId(id);
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
				if(roleDao.InsertRole(role)>0){
					save(role);
				}
			}else{
				//修改
				if(roleDao.updateRole(role)>=0){
					roleDao.deleteOfficeByRoleId(role.getId());
					save(role);
				}
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
	
	/**
	 * 保存角色与office的关系
	 * 
	 */
	private void save(Role role){
		if(role.getOfficeList()!=null){
			for (Office temp : role.getOfficeList()) {
				roleDao.insert(temp.getId(),role.getId());//添加office
				}
			}
	}
	/**
	 * 获取角色用户列表
	 * @param id 角色id
	 * @return
	 */
	public Object getRoleUserList(Integer id) {
		try {
			//list = roleDao.getRoleUserList(id);
			//total = roleDao.getRoleUserCount(id);
			data.put("total", total);
			data.put("userList", list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
		return null;
	}
	
	
	
}
