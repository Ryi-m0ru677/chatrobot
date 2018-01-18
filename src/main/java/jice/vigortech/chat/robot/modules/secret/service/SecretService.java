package jice.vigortech.chat.robot.modules.secret.service;



import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.secret.dao.SecretDao;
import jice.vigortech.chat.robot.modules.secret.entity.Secret;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.sys.user.entity.User;

@Service
@Transactional(readOnly=true)
public class SecretService extends BaseService{

	@Autowired
	SecretDao secretDao;

	/**
	 * 获取密级列表
	 * @param query
	 * @return
	 */
	public Object getSecretList(PageQuery query) {
		try {
			User user = SecurityUtils.getCurrentUser();
			if(user==null){
				return ResultCode.SESSION_INVALID;
			}
			query.setSql(super.dataScopeFilter(user, "oy", "uy"));
			list = secretDao.getSecretList(query);
			total = secretDao.getSecretCount(query);
			data.put("total",total);
			data.put("roleList",list);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode deleteSecretById(Integer id) {
		try {
			Map<String,Object> temp = secretDao.getSecretDetailById(id);
			if(temp==null){
				return ResultCode.SECRET_NOT_EXITS;
			}
			secretDao.deleteSecretById(id);
			secretDao.deleteRoleSecret(id);//删除与角色的关系
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 修改或是保存密级
	 * @param Secret
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode saveOrUpdateSecret(Secret secret) {
		try {
			if(secret.getId()==null){
				//添加
				User user = SecurityUtils.getCurrentUser();
				if(user==null){
					return ResultCode.SESSION_INVALID;
				}
				secret.setCreateBy(user);
				secretDao.insertSecret(secret);
			}else{
				//修改
				secretDao.updateSecret(secret);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}

	/**
	 * 获取密级详情
	 * @param id
	 * @return
	 */
	public Object getSecretDetailById(Integer id) {
		try {
			Map<String,Object> temp = secretDao.getSecretDetailById(id);
			if(temp==null){
				return ResultCode.SECRET_NOT_EXITS;
			}
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
