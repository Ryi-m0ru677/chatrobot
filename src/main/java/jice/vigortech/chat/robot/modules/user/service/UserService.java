package jice.vigortech.chat.robot.modules.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.common.constants.SysConstants;
import jice.vigortech.chat.robot.common.model.service.BaseService;
import jice.vigortech.chat.robot.common.util.AESUtil;
import jice.vigortech.chat.robot.common.util.Md5PasswordEncoderWithSalt;
import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.sys.system.entity.PageQuery;
import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Service
@Transactional(readOnly=true)
public class UserService extends BaseService{

	@Autowired
	UserDao userDao;
	/**
	 * 获取用户列表
	 * @param query
	 * @return
	 */
	public Object getUserList(PageQuery query) {
		Map<String,Object> data = new HashMap<String,Object>();
		int total = 0;
		try {
			query.setSql(super.dataScopeFilter(SecurityUtils.getCurrentUser(), "oy", null));
			List<Map<String,Object>> list = userDao.getUserList(query);
			total = userDao.getUserCount(query);
			data.put("userList", list);
			data.put("total", total);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	
	/**
	 * 注册用户或是修改用户
	 * @param user
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Object addUser(User user) {
		if(user.getId()==null){
			//添加
			try {
				if(userDao.getUserByUserName(user.getUsername())!=null){
					return ResultCode.USER_HAS_EXIST;
				}
				String password = Md5PasswordEncoderWithSalt.encodePassword(user.getPassword()); 
				user.setRole("sys_user");
				user.setPassword(password);
				if(userDao.insertUser(user)>0){//用户注册后生成token并保存
					Map<String,Object> userConfig = new HashMap<String,Object>();
					String token = AESUtil.encrypt(user.getUsername(), SysConstants.SYS_TOKEN_SALT);
					userConfig.put("id", user.getId());
					userConfig.put("name", user.getUsername());
					userConfig.put("token", token);
					userConfig.put("time", "30");
					userDao.insertUserConfig(userConfig);
				}
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
	
	/**
	 * 修改密码
	 * @param password
	 * @param newPassword
	 * @return
	 */
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
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
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
	
	/**
	 * 获取用户详情
	 * 
	 * @param id
	 * @return
	 */
	public Object getUserDetail(Integer id) {
		try {
			return userDao.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 根据用户名获取token
	 * @param username
	 * @return
	 */
	public Object getUserTokenByUserName(String username) {
		Map<String,Object> token = null; 
		try {
			token = userDao.getTokenByUserName(username);
			return token;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
}
