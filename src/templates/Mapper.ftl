<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_name}.sqlserver.dao.${package_children}.I${table_name}Dao">
	<#assign x = "#"+"{">
	<#assign y = "}">
	<resultMap type="${package_name}.entity.${package_children}.${table_name}" id="${table_name}Result">
		<#if model_column?exists>
	        <#list model_column as model>
       	<result column="${model.columnName}" property="${model.changeColumnName?uncap_first}"/>
	        </#list>
	    </#if>
		
	</resultMap>
	
	<select id="getById" resultMap="${table_name}Result">
		select 
			*
		from
			${table_name_small}
		where
			ID = ${x}id${y}
	</select>
	
	<sql id="whereConditions">
		<#if model_column?exists>
	        <#list model_column as model>
	    <if test="queryMap.q_${model.changeColumnName?uncap_first} != null and queryMap.q_${model.changeColumnName?uncap_first} != ''">and ${model.columnName} = ${x}queryMap.q_${model.changeColumnName?uncap_first}${y}</if>
	        </#list>
	    </#if>
	</sql>
	
	<select id="queryMap" resultMap="${table_name}Result">
		SELECT
			*
		from
			${table_name_small}
		where
			1=1
		<include refid="whereConditions"/>
		order by ID desc
		<if test="size != null and size !=''">limit  ${x}start${y}, ${x}size${y}</if>
	</select>

	<select id="count" resultType="java.lang.Integer">
		select
			count(1)
		from
			${table_name_small}
		where
		1=1
		<include refid="whereConditions" />
	</select>
	
	<insert id="add" parameterType="${package_name}.entity.${package_children}.${table_name}" keyProperty="id" keyColumn="ID" useGeneratedKeys="true">
		insert into ${table_name_small}
		<trim prefix="(" suffixOverrides="," suffix = ")">
			<#if model_column?exists>
		        <#list model_column as model>
        	${model.columnName},
		        </#list>
		    </#if>
		</trim>
		values
		<trim prefix="(" suffixOverrides="," suffix = ")">
			<#if model_column?exists>
		        <#list model_column as model>
        	${x}${model.changeColumnName?uncap_first}${y},
		        </#list>
		    </#if>
		</trim>
	</insert> 
	
	<update id="edit" parameterType="${package_name}.entity.${package_children}.${table_name}">
		update ${table_name_small}
		<trim prefix="set" suffixOverrides=",">
			<#if model_column?exists>
		        <#list model_column as model>
        	<if test="${model.changeColumnName?uncap_first} !=null and ${model.changeColumnName?uncap_first} != ''">${model.columnName} = ${x}${model.changeColumnName?uncap_first}${y},</if>
		        </#list>
		    </#if>
		</trim>
		where
			ID = ${x}id${y}
	</update>
	
	<delete id="batchDelete">
		delete from ${table_name_small}
		where 
			ID in ${x}ids${y}
	</delete>
</mapper>