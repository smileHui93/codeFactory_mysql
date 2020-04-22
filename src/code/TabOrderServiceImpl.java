package com.serviceImpl.;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
* 描述：系统用户 服务实现层
* @author linhuang
* @date 2019/09/20
*/
@Service("tabOrderService")
public class TabOrderServiceImpl implements ITabOrderService {
	private Logger log = Logger.getLogger(TabOrderServiceImpl.class);
	
    @Resource(name = "tabOrderModel")
    private TabOrderModel tabOrderModel;
    
    @Override
	public TabOrder getById(Integer id) {
		TabOrder result = null;
		try {
            result = tabOrderModel.getById(id);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
	}

    @Override
	public List<TabOrder> queryMap(Map<String, Object> queryMap, Integer start, Integer end) {
		List<TabOrder> result = null;
		try {
            result = tabOrderModel.queryMap(queryMap, start, end);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
	}

	@Override
	public Integer count(Map<String, Object> queryMap) {
		Integer count = 0;
        try {
            count = tabOrderModel.count(queryMap);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}

	@Override
	public Integer add(TabOrder tabOrder) {
		Integer count = 0;
        try {
            count = tabOrderModel.add(tabOrder);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}

	@Override
	public Integer edit(TabOrder tabOrder) {
		Integer count = 0;
        try {
            count = tabOrderModel.edit(tabOrder);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}
}