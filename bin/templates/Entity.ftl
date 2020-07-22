package ${package_name}.entity.${package_children}.${table_name}Entity;

/**
* @desc: ${table_annotation}实体类
* @author: ${author}
* @date: ${date}
*/

@Data
@TableName("${table_name_small}")
public class ${table_name}Entity implements Serializable{
	private static final long serialVersionUID = 1L;

	<#if model_column??>
       <#list model_column as model>
    /** ${model.columnComment} */
	private ${model.changeColumnType} ${model.changeColumnName?uncap_first};
	
        </#list>
    </#if>
	
	<#if model_column??>
		<#list model_column as model>
	public ${model.changeColumnType} get${model.changeColumnName}() 
	{
	    return this.${model.changeColumnName?uncap_first};
	}
	
	public void set${model.changeColumnName}(${model.changeColumnType} ${model.changeColumnName?uncap_first}) 
	{
	    this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
	}
		</#list>
	</#if>
}