package jice.vigortech.chat.robot.modules.dicts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jice.vigortech.chat.robot.common.constants.ResultCode;
import jice.vigortech.chat.robot.modules.dicts.dao.DictDao;
import jice.vigortech.chat.robot.modules.dicts.entity.Dicts;
import jice.vigortech.chat.robot.modules.dicts.entity.Synonymy;

@Service
@Transactional(readOnly=true)
public class DictService {

	@Autowired
	DictDao dictDao;
	/**
	 * 根据应用id获取词库
	 * @param name
	 * @param appId 应用id
	 * @return
	 */
	public Object getDicList(String name, Integer appId) {
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> dictList = null;
		try {
			dictList = dictDao.getDicList(name,appId);
		} catch (Exception e) {
			e.printStackTrace();
			dictList = null;
		}
		data.put("dictList", dictList);
		return data;
	}
	/**
	 * 删除词库
	 * @param id
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public ResultCode deleteDictById(Integer id) {
		if(dictDao.getDictById(id)==null){
			return ResultCode.DICT_NOT_EXIST;
		}
		try {
			if(dictDao.deleteDictById(id)>0){
				dictDao.deleteWord(id);
			}
			return ResultCode.OPERATION_SUCCESSED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultCode.OPERATION_FAILED;
		}
	}
	/**
	 *  添加/修改
	 * @param dict
	 * @param appId
	 * @return
	 */
	@Transactional(readOnly=false,rollbackFor=Exception.class)
	public Object insertDict(Dicts dict, Integer appId) {
		dict.setAppId(appId);
		if(dict.getId()==null){
			//添加
			try {
				if(dictDao.insertDict(dict)>0){
					if(dict.getWordList()!=null){
						for (Synonymy syn : dict.getWordList()) {
							syn.setDictId(dict.getId());
							dictDao.insertDictWord(syn);
						}
					}
				}
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}else{
			//修改
			if(dictDao.getDictById(dict.getId())==null){
				return ResultCode.DICT_NOT_EXIST;
			}
			try {
				if(dictDao.updateDict(dict)>0){
					if(dict.getWordList()!=null){
						for (Synonymy syn : dict.getWordList()) {
							syn.setDictId(dict.getId());
							if(syn.getId()!=null){
								dictDao.updateDictWord(syn);
							}else{
								dictDao.insertDictWord(syn);
							}
						}
					}
				}	
				return ResultCode.OPERATION_SUCCESSED;
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCode.OPERATION_FAILED;
			}
		}
	}
	/**
	 * 词库明细
	 * @param id
	 * @return
	 */
	public Object getDictDetail(Integer id) {
		if(dictDao.getDictById(id)==null){
			return ResultCode.DICT_NOT_EXIST;
		}
		Map<String,Object> data = new HashMap<String,Object>();
		Map<String,Object> detail = dictDao.getDictById(id);
		List<Map<String,Object>> wordList = dictDao.getWordList(id);
		data.put("detail",detail);
		detail.put("wordList",wordList);
		return data;
	}
}
