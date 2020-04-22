package ${package_name}.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import ${package_name}.entity.${package_children}.${table_name};

/**
* 描述：${table_annotation} Dao层
* @author ${author}
* @date ${date}
*/
@Repository("${table_name?uncap_first}Dao")
public interface I${table_name}Dao {

	${table_name} getById(Integer id);
	
	Integer count(@Param("queryMap") Map<String,Object> queryMap);
	
	List<${table_name}> queryMap(@Param("queryMap") Map<String,Object> queryMap,
				@Param("start") Integer start,@Param("size") Integer size);
	
	Integer add(${table_name} ${table_name?uncap_first});
	
	Integer edit(${table_name} ${table_name?uncap_first});
	
	Integer batchDelete(@Param("ids") String ids);
}