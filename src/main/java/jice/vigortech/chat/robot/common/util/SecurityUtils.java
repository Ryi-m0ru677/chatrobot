package jice.vigortech.chat.robot.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;

@Component
public class SecurityUtils {

	static UserDao userDao;

	@Autowired
	public SecurityUtils(UserDao userDao) {
		SecurityUtils.userDao = userDao;
	}
	
	public static User getCurrentUser() {
		UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal == null) {
			return null;
		}
		return userDao.getUserByUserName(principal.getUsername());
	}
}
