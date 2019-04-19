package com.ditto.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ditto.dto.TableHandlerEntity;
import com.ditto.entity.ActionRecordEntity;
import com.ditto.utils.Query;

/**
 * 
 */
@Mapper
public interface ActionRecordDao extends BaseDao<ActionRecordEntity> {

	void clearBasicTables_time();

	void clearBasicTables_day();

	void deleteTableData(TableHandlerEntity handlerEntity);
	
	void lockOperation(TableHandlerEntity handlerEntity);

	void unlockOperation();

	void createIBTable(@Param("tableName") String tableName);

	int queryIBTable(@Param("tableName") String tableName);

	void loadData(Map<String, Object> map);

	List<Map<String, Object>> listJqTestData(Query query);

	int listJqTestDataCount(Query query);

	void delJqTestData(Integer id);

	List<Map<String, Object>> queryData(Map<String, Object> map);

	Map<String, Object> compareData(Map<String, Object> mapParam);

	ActionRecordEntity queryExist(Map<String, Object> map);
}
