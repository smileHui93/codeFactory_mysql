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
@Component("tabOrderModel")
public class TabOrderModel{

	@Resource(name = "tabOrderDao")
	private ITabOrderDao tabOrderDao;

	/**
     * 总数
     * @param queryMap
     * @return
     */
	public Integer count(Map<String,Object> queryMap){
		return tabOrderDao.count(queryMap);
	}

    /**
     * 分页查询
     * @return
     */
    public  List<TabOrder> queryMap(Map<String,Object> queryMap, Integer start, Integer end){
        List<TabOrder> result =  tabOrderDao.queryMap(queryMap, start, end);
        return result;
    }
    
    /**
     * 根据id查询
     * @return
     */
    public TabOrder getById(Integer id){
        return tabOrderDao.getById(id);
    }

	public Integer add(TabOrder tabOrder){
		return tabOrderDao.add(tabOrder);
	}
	
	public Integer edit(TabOrder tabOrder){
		return tabOrderDao.edit(tabOrder);
	}
}