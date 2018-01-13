package jice.vigortech.sys.process;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import jice.vigortech.chat.robot.modules.process.dao.ProcessDao;
import jice.vigortech.chat.robot.modules.process.entiry.ProcessBlock;
import jice.vigortech.chat.robot.modules.process.entiry.Processes;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProecssTest {
	@Autowired
	ProcessDao proDao;
	
	@Test
	public void addTest(){
		ProcessBlock prob1 = new ProcessBlock();
		ProcessBlock prob2 = new ProcessBlock();
		prob1.setAid(12);
		prob1.setItName("ksdh2");
		prob1.setLat("12232");
		prob1.setLng("646221");
		prob1.setSort(1);
		prob2.setAid(12);
		prob2.setItName("ksdh22");
		prob2.setLat("122322");
		prob2.setLng("64621222");
		prob2.setSort(2);
		List<ProcessBlock> list = new ArrayList<ProcessBlock>();
		list.add(prob2);
		list.add(prob1);
		
		Processes pro = new Processes();
		//pro.setCreateBy(1);
		pro.setDescribe("haohao22");
		pro.setName("haha22");
		pro.setContent(list);
		
		proDao.insertProcess(pro);
	}

	@Test
	public void getDetailTest(){
		Map<String, Object> temp = proDao.getProDetailById(3);
		System.out.println(JSON.toJSON(temp));
	}
}
