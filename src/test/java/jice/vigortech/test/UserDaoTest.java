package jice.vigortech.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.modules.user.dao.UserDao;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTest {

	@Autowired
	UserDao userDao;
	/*@Test
	public void testGetUserByName(){
		System.out.println(userDao.getUser());
		Assert.assertTrue(userDao.getUser().size()>0);
	}*/
}
