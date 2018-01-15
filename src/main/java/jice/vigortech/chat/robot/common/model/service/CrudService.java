package jice.vigortech.chat.robot.common.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;

@Transactional(readOnly = true)
public abstract class CrudService<D,T> extends BaseService{
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;
	
	
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
			query.setSql(super.dataScopeFilter(SecurityUtils.getCurrentUser(), "oy", "uy"));
			/*list = dao.getRoleList(query);
			total = dao.getRoleCount(query);*/
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
			/*if(dao.getRoleById(id)==null){
				return ResultCode.ROLE_NOT_EXITS;
			}
			dao.deleteRoleById(id);*/
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
	/*public ResultCode saveOrUpdateRole(T t) {
		try {
			if(t.getId()==null){
				//添加
				t.setCreateBy(SecurityUtils.getCurrentUser());
				dao.InsertRole(t);
			}else{
				//修改
				dao.updateRole(t);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}*/

	/**
	 * 根据id获取角色详情
	 * @param id
	 * @return
	 */
	/*public Object getRoleDetailById(Integer id) {
		try {
			//Map<String,Object> temp = dao.get"+"T"+"ById(id);
			if(temp==null){
				return ResultCode.ROLE_NOT_EXITS;
			}
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}*/
}
