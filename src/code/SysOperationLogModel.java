package com.model;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

/**
* 描述：系统用户Model层
* @author linhuang
* @date 2019/09/20
*/
@Component("sysOperationLogModel")
public class SysOperationLogModel{

	@Resource(name = "sysOperationLogDao")
	private ISysOperationLogDao sysOperationLogDao;

	/**
     * 总数
     * @param queryMap
     * @return
     */
	public Integer count(Map<String,Object> queryMap){
		return sysOperationLogDao.count(queryMap);
	}

    /**
     * 分页查询
     * @return
     */
    public  List<SysOperationLog> queryMap(Map<String,Object> queryMap, Integer start, Integer end){
        List<SysOperationLog> result =  sysOperationLogDao.queryMap(queryMap, start, end);
        return result;
    }
    
    /**
     * 根据id查询
     * @return
     */
    public SysOperationLog getById(Integer id){
        return sysOperationLogDao.getById(id);
    }

	public Integer add(SysOperationLog sysOperationLog){
		return sysOperationLogDao.add(sysOperationLog);
	}
	
	public Integer edit(SysOperationLog sysOperationLog){
		return sysOperationLogDao.edit(sysOperationLog);
	}
}