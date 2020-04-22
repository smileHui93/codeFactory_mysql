package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.entity..SysOperationLog;

/**
* 描述：系统用户 Dao层
* @author linhuang
* @date 2019/09/20
*/
@Repository("sysOperationLogDao")
public interface ISysOperationLogDao {

	SysOperationLog getById(Integer id);
	
	Integer count(@Param("queryMap") Map<String,Object> queryMap);
	
	List<SysOperationLog> queryMap(@Param("queryMap") Map<String,Object> queryMap,
				@Param("start") Integer start,@Param("size") Integer size);
	
	Integer add(SysOperationLog sysOperationLog);
	
	Integer edit(SysOperationLog sysOperationLog);
	
	Integer batchDelete(@Param("ids") String ids);
}