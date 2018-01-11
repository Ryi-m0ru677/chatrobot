package jice.vigortech.chat.robot.common.model.service;

import jice.vigortech.chat.robot.common.util.SecurityUtils;
import jice.vigortech.chat.robot.modules.user.entity.User;


public class BaseService {
	protected User user = SecurityUtils.getCurrentUser();
	
}
