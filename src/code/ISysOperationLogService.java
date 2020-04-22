package com.service.;

import java.util.List;
import java.util.Map;

import com.entity..SysOperationLog;

/**
* 描述：系统用户服务实现层接口
* @author linhuang
* @date 2019/09/20
*/
public interface ISysOperationLogService{

	SysOperationLog getById(Integer id);
	
	Integer count(Map<String,Object> queryMap);
	
	List<SysOperationLog> queryMap(Map<String,Object> queryMap, Integer start, Integer end);
	
	Integer add(SysOperationLog sysOperationLog);
	
	Integer edit(SysOperationLog sysOperationLog);
}