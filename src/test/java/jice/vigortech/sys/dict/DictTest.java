package jice.vigortech.sys.dict;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import jice.vigortech.chat.robot.modules.dicts.dao.DictDao;
import jice.vigortech.chat.robot.modules.dicts.entity.Dicts;
import jice.vigortech.chat.robot.modules.dicts.entity.Synonymy;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DictTest {

	@Autowired
	DictDao dictDao;
	@Test
	public void getDictList(){
		System.out.println(dictDao.getDicList("", 23));
	}
	@Test
	public void addDict(){
		Dicts dict = new Dicts();
		dict.setAppId(23);
		dict.setSynonymyFlag(1);
		dict.setName("跑鞋12");
		
		
		if(dictDao.insertDict(dict)>0){
			Synonymy synon = new Synonymy();
			synon.setDictId(dict.getId());
			synon.setKeyword("跑12");
			synon.setSynonymy("跑步12");
			
			dictDao.insertDictWord(synon);
		}
		System.out.println(dictDao.getDicList("", 23));
	}
}
