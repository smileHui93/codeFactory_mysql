package com.sqlserver.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity..TabOrder;

/**
* 描述：系统用户 Dao层
* @author linhuang
* @date 2019/09/20
*/
@Repository("tabOrderDao")
public interface ITabOrderDao {

	TabOrder getById(Integer id);
	
	Integer count(@Param("queryMap") Map<String,Object> queryMap);
	
	List<TabOrder> queryMap(@Param("queryMap") Map<String,Object> queryMap,
				@Param("start") Integer start,@Param("size") Integer size);
	
	Integer add(TabOrder tabOrder);
	
	Integer edit(TabOrder tabOrder);
	
	Integer batchDelete(@Param("ids") String ids);
}