package jice.vigortech.sys.MService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.modules.mservices.dao.MSerivceDao;
import jice.vigortech.chat.robot.modules.mservices.entity.MicService;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMService {
	
	@Autowired
	MSerivceDao mdao; 
	/*@Test
	public void testSave(){
		MicService temp = new MicService();
		temp.setName("sdssd");
		temp.setIp("dsafsd");
		temp.setEname("sdfa");
		temp.setDescribe("kjklhjk");
		
		Assert.assertTrue("haha", mdao.saveMicService(temp)>0);
	}*/
}
