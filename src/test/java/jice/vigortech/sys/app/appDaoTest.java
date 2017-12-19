package jice.vigortech.sys.app;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.modules.application.dao.AppDao;
import jice.vigortech.chat.robot.modules.application.entity.Application;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class appDaoTest {

	@Autowired
	AppDao appDao;

	@Test
	public void testUpdateApp() {
		Application app = appDao.findAppById(1);
		System.out.println(app.getName());
		app.setDescribe(app.getDescribe() + "haha");
		Assert.assertTrue(appDao.updateApp(app) >= 0);
	}

	@Test
	public void testInsertApp() {
		Application app = new Application();
		app.setName("聊天");
		app.setIsPrivate(1);
		app.setDefReply("哈哈");
		Assert.assertTrue(appDao.insertApp(app) >= 0);
	}
}
