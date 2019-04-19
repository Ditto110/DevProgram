package com.ditto.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.ditto.dao.ActionRecordDao;
import com.ditto.dto.TableHandlerEntity;
import com.ditto.entity.ActionRecordEntity;
import com.ditto.service.ActionRecordService;
import com.ditto.utils.Query;



@Service("actionRecordService")
public class ActionRecordServiceImpl implements ActionRecordService {
	@Autowired
	private ActionRecordDao actionRecordDao;
	
	@Override
	public ActionRecordEntity queryObject(Integer id){
		return actionRecordDao.queryObject(id);
	}
	
	@Override
	public List<ActionRecordEntity> queryList(Map<String, Object> map){
		return actionRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return actionRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(ActionRecordEntity actionRecord){
		actionRecordDao.save(actionRecord);
	}
	
	@Override
	public void update(ActionRecordEntity actionRecord){
		actionRecordDao.update(actionRecord);
	}
	
	@Override
	public void delete(Integer id){
		actionRecordDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		actionRecordDao.deleteBatch(ids);
	}

	@Override
	public void clearBasicTables_time() {
		actionRecordDao.clearBasicTables_time();
	}

	@Override
	public void clearBasicTables_day() {
		// TODO Auto-generated method stub
		actionRecordDao.clearBasicTables_day();
	}

	@Override
	public void deleteExpo(TableHandlerEntity handlerEntity) {
		// TODO Auto-generated method stub
		actionRecordDao.deleteTableData(handlerEntity);
	}

	@Override
	public void lockOperation(TableHandlerEntity handlerEntity) {
		// TODO Auto-generated method stub
		actionRecordDao.lockOperation(handlerEntity);
	}

	@Override
	public void unlockOperation() {
		// TODO Auto-generated method stub
		actionRecordDao.unlockOperation();
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public String testTrasaction() {
		ActionRecordEntity recordEntity = new ActionRecordEntity();
		recordEntity.setActions("transaction 1");
		recordEntity.setActionTime(new Date());
		recordEntity.setActionTime(new Date());
		recordEntity.setActions("transaction 2");
		recordEntity.setId(4);
		try {
			actionRecordDao.save(recordEntity);
			actionRecordDao.save(recordEntity);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
		}
		return "";
	}

	@Override
	public void createIBTable(String tableName) {
		actionRecordDao.createIBTable(tableName);
	}

	@Override
	public int queryIBTable(String tableName) {
		return actionRecordDao.queryIBTable(tableName);
	}

	@Override
	public void loadData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		actionRecordDao.loadData(map);
	}

	@Override
	public List<Map<String, Object>> listJqTestData(Query query) {
		// TODO Auto-generated method stub
		return actionRecordDao.listJqTestData(query);
	}

	@Override
	public int listJqTestDataCount(Query query) {
		return actionRecordDao.listJqTestDataCount(query);
	}

	@Override
	public void delJqTestData(Integer id) {
		actionRecordDao.delJqTestData(id);
	}

	@Override
	public List<Map<String, Object>> queryData(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return actionRecordDao.queryData(map);
	}

	@Override
	public Map<String, Object> compareData(Map<String, Object> mapParam) {
		// TODO Auto-generated method stub
		return actionRecordDao.compareData(mapParam);
	}

	@Override
	public void saveBatch(List<ActionRecordEntity> list) {
		actionRecordDao.saveBatch(list);	
	}

	@Override
	public ActionRecordEntity queryExist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return actionRecordDao.queryExist(map);
	}
}
