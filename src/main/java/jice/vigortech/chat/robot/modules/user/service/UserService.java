package jice.vigortech.chat.robot.modules.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.Md5PasswordEncoderWithSalt;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
@Transactional(readOnly=true)
public class UserService{

	@Autowired
	UserDao userDao;
	
	public Object getUserList(String name) {
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Map<String,Object>> userList = userDao.getUserList(name);
		map.put("userList", userList);
		return map;
	}
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Object addUser(User user) {
		if(user.getId()==null){
			//添加
			try {
				user.setRole("sys_user");
				user.setPassword(Md5PasswordEncoderWithSalt.encodePassword(user.getPassword()));
				userDao.insertUser(user);
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}else{
			//修改
			if(userDao.updateUser(user)>0){
				return ResultCode.OPERATION_SUCCESSED;
			}else{
				return ResultCode.OPERATION_FAILED;
			}
			
		}
		
	}
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode chgPasswd(String password, String newPassword) {
		User user  = SecurityUtils.getCurrentUser();
		if(user==null){
			return ResultCode.OPERATION_NOT_PERMITTED;
		}
		
		try {
			userDao.chgPasswd(Md5PasswordEncoderWithSalt.encodePassword(newPassword),user.getId());
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
		
	}
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode deleteUserById(Integer id) {
		if(userDao.getUserById(id)==null){
			return ResultCode.USER_NOTEXIST;
		}
		try {
			userDao.deleteUserById(id);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	public Object getUserDetail(Integer id) {
		try {
			return userDao.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
