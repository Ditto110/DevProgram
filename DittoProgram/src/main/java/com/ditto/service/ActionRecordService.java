package com.ditto.service;

import com.ditto.dto.TableHandlerEntity;
import com.ditto.entity.ActionRecordEntity;
import com.ditto.utils.Query;

import java.util.List;
import java.util.Map;

public interface ActionRecordService {
	
	ActionRecordEntity queryObject(Integer id);
	
	List<ActionRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ActionRecordEntity actionRecord);
	
	void update(ActionRecordEntity actionRecord);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void clearBasicTables_time();

	void clearBasicTables_day();

	void deleteExpo(TableHandlerEntity handlerEntity);
	
	void lockOperation(TableHandlerEntity handlerEntity);

	void unlockOperation();

	String testTrasaction();

	void createIBTable(String tableName);

	int queryIBTable(String tableName);

	void loadData(Map<String, Object> map);

	List<Map<String, Object>> listJqTestData(Query query);

	int listJqTestDataCount(Query query);

	void delJqTestData(Integer id);

	List<Map<String, Object>> queryData(Map<String, Object> map);

	Map<String, Object> compareData(Map<String, Object> mapParam);

	void saveBatch(List<ActionRecordEntity> list);

	ActionRecordEntity queryExist(Map<String, Object> map);
}
