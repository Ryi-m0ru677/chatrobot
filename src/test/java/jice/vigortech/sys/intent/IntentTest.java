package jice.vigortech.sys.intent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.intents.dao.IntentDao;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.Slot;
import jice.vigortech.chat.robot.modules.intents.service.IntentService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IntentTest {
	@Autowired
	IntentService intentService;
	@Autowired
	IntentDao intentDao;
	/*@Test
	public void insertIntentTest(){
		Intents itemp = new Intents();
		Slot atemp = new Slot();
		atemp.setIntentId(itemp.getId());
		atemp.setFlag(1);
		atemp.setMessage("adshfkjsdahf");
		
		itemp.setAppId(1);
		itemp.setName("上课");
		Assert.assertTrue(intentService.insertIntent(itemp, 1)==ResultCode.OPERATION_SUCCESSED);
	}
	
	@Test
	public void insertActionTest(){
		Slot atemp = new Slot();
		atemp.setIntentId(1);
		atemp.setFlag(1);
		atemp.setMessage("adshfkjsdahf");
		//intentDao.insertAction(atemp);
		Assert.assertTrue(intentDao.insertAction(atemp)>=0);
	}
	
	
	@Test
	public void getIntentList(){
		//System.out.println(intentDao.getIntentList("", 23));
	}
	
	@Test
	public void getIntentDetail(){
		
	}*/
	
}
