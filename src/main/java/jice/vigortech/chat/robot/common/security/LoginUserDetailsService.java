package jice.vigortech.chat.robot.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jice.vigortech.chat.robot.modules.user.dao.UserDao;


@Service
public class LoginUserDetailsService implements UserDetailsService{

	@Autowired
	UserDao userDao ;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		jice.vigortech.chat.robot.modules.user.entity.User user = userDao.getUserByUserName(username);
		if(user != null){
			return new User(user.getName(),user.getPassword(),true, true,true,true,getAuthority(user.getId(),user.getRole()));
		}else{
			throw new UsernameNotFoundException(username);
		}
	}
	
	private List<GrantedAuthority> getAuthority(Integer id,String userRole){
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		List<String> authKeys = null;
		if(userRole.equalsIgnoreCase("sys_admin")){
			authKeys = userDao.getSuperAuthRole();
		}else{
			authKeys = userDao.getAuthRoleById(id);
		}
		for (String keys : authKeys) {
			auths.add(new SimpleGrantedAuthority(keys));
		}
		return auths;
	}
}
