package jice.vigortech.chat.robot.modules.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.util.Md5PasswordEncoderWithSalt;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
public class UserService{

	@Autowired
	UserDao userDao;
	
	public Object getUserList(String name) {
		
		return null;
	}

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

	public ResultCode chgPasswd(String password, String newPassword) {
		User user  = SecurityUtils.getCurrentUser();
		if(user==null){
			return ResultCode.OPERATION_NOT_PERMITTED;
		}
		//TODO 优化
		try {
			userDao.chgPasswd(newPassword);
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
		
	}
	
}
