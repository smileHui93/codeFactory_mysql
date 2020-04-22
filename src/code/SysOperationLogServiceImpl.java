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
@Service("sysOperationLogService")
public class SysOperationLogServiceImpl implements ISysOperationLogService {
	private Logger log = Logger.getLogger(SysOperationLogServiceImpl.class);
	
    @Resource(name = "sysOperationLogModel")
    private SysOperationLogModel sysOperationLogModel;
    
    @Override
	public SysOperationLog getById(Integer id) {
		SysOperationLog result = null;
		try {
            result = sysOperationLogModel.getById(id);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return result;
	}

    @Override
	public List<SysOperationLog> queryMap(Map<String, Object> queryMap, Integer start, Integer end) {
		List<SysOperationLog> result = null;
		try {
            result = sysOperationLogModel.queryMap(queryMap, start, end);
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
            count = sysOperationLogModel.count(queryMap);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}

	@Override
	public Integer add(SysOperationLog sysOperationLog) {
		Integer count = 0;
        try {
            count = sysOperationLogModel.add(sysOperationLog);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}

	@Override
	public Integer edit(SysOperationLog sysOperationLog) {
		Integer count = 0;
        try {
            count = sysOperationLogModel.edit(sysOperationLog);
        } catch(BusinessException be) {
            log.error(be.getMessage(),be);
	    } catch(Exception e) {
            log.error(e.getMessage(),e);
        }
        return count;
	}
}