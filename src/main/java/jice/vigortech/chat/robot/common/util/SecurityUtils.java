package jice.vigortech.chat.robot.common.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jice.vigortech.chat.robot.modules.sys.office.entity.Office;
import jice.vigortech.chat.robot.modules.sys.role.dao.RoleDao;
import jice.vigortech.chat.robot.modules.sys.role.entity.Role;
import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Component
public class SecurityUtils {

	static UserDao userDao;
	static RoleDao roleDao;
	
	@Autowired
	public SecurityUtils(UserDao userDao,RoleDao roleDao) {
		SecurityUtils.userDao = userDao;
		SecurityUtils.roleDao = roleDao;
	}
	
	public static User getCurrentUser() {
		
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal == null) {
			return null;
		}
		User user  = userDao.getUserByUserName(principal.getUsername());
		//user.setRoleList(roleDao.getRoleByUser(user.getId()));
		user.setCompany(new Office(user.getCompanyid()));
		user.setOffice(new Office(user.getOfficeid()));
		return user;
	}
	
	public static List<Role> getRoleListByUser(Integer id){
		//List
		System.out.println(roleDao.getRoleByUser(id));
		return roleDao.getRoleByUser(id);
	}
}
