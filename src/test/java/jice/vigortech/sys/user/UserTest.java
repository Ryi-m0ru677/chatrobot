package jice.vigortech.sys.user;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import jice.vigortech.chat.robot.modules.user.dao.UserDao;
import jice.vigortech.chat.robot.modules.user.entity.User;
import jice.vigortech.chat.robot.modules.user.service.UserService;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
	
	@Autowired
	UserDao userDao;
	@Autowired
	UserService userService;
	
	@Test
	public void testList(){
		System.out.println(userDao.getUser());
	}
	
	@Test
	public void testAdd(){
		User user = new User();
		user.setEmail("sdfsa");
		user.setPhone("23154416sdfa");
		user.setPassword("123456");
		user.setName("王五");
		userDao.insertUser(user);
	}
	
	@Test
	public void testInsert(){
		User user = new User();
		user.setEmail("sdfsa");
		user.setPhone("23154416sdfa");
		user.setPassword("123456");
		user.setName("王五");
		userService.addUser(user);
	}
	
	@Test
	public void testUpdate(){
		Map<String,Object> map = userDao.getUserById(1);
		
		System.out.println(JSON.toJSON(map));
		
		
	}
	
	@Test
	public void testUserConfig(){
		Map<String,Object> userConfig = new HashMap<String,Object>();
		userConfig.put("id", 1);
		userConfig.put("name", "zhanganr");
		userConfig.put("token", "sdkajfhksajdhfkj");
		userConfig.put("time", "30");
		
		userDao.insertUserConfig(userConfig);
		System.out.println(userDao.getTokenByUserName("zhangsan"));
	}
	
	
}
