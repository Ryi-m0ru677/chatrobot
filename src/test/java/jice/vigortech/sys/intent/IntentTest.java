package jice.vigortech.sys.intent;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.intents.dao.IntentDao;
import jice.vigortech.chat.robot.modules.intents.entity.Intents;
import jice.vigortech.chat.robot.modules.intents.entity.RobotAction;
import jice.vigortech.chat.robot.modules.intents.entity.Talk;
import jice.vigortech.chat.robot.modules.intents.service.IntentService;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class IntentTest {
	@Autowired
	IntentService intentService;
	@Autowired
	IntentDao intentDao;
	@Test
	public void insertIntentTest(){
		Intents itemp = new Intents();
		RobotAction atemp = new RobotAction();
		atemp.setIntentId(itemp.getId());
		atemp.setFlag(1);
		atemp.setMesssage("adshfkjsdahf");
		Talk ttemp = new Talk();
		ttemp.setAnswer("haha");
		ttemp.setAsk("xixi");
		ttemp.setIntentsId(itemp.getId());
		List<RobotAction> rlist = new ArrayList<RobotAction>();
		rlist.add(atemp);
		List<Talk> tlist = new ArrayList<Talk>();
		tlist.add(ttemp);
		itemp.setTalk(tlist);
		itemp.setAction(rlist);
		itemp.setAppId(1);
		itemp.setName("上课");
		Assert.assertTrue(intentService.insertIntent(itemp, 1)==ResultCode.OPERATION_FAILED);
	}
	
	@Test
	public void insertActionTest(){
		RobotAction atemp = new RobotAction();
		atemp.setIntentId(1);
		atemp.setFlag(1);
		atemp.setMesssage("adshfkjsdahf");
		intentDao.insertAction(atemp);
	}
}
