package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import freemarker.template.Template;
import jdbc.ColumnClass;
import jdbc.JdbcUtil;

public class CodeGenerateUtils {
    private String changeTableName;
    private String diskPath;

    private final String AUTHOR = "hui";
    private final String tableName = "repairs";
    private final String packageName = "com";
    private final String packageChildren = "";
    private final String tableAnnotation = "数据补录";
    private final TypeMap typeMap = new TypeMap();
    
    public static String dbName; //数据库名称
    
    public CodeGenerateUtils() throws IOException {
        super();
        this.changeTableName = replaceUnderLineAndUpperCase(tableName);
        File file = new File("");
        this.diskPath = file.getCanonicalPath()+"/src/code/";
    }

    public String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        String[] arr = str.split("_");
        for(String s:arr) {
            sb.append(StringUtil.firstUpCase(s.toLowerCase()));
        }
        return sb.toString();
    }
    
    
    public void generate() throws Exception{
        try {
            Connection connection = JdbcUtil.getConnection();
            Statement stmt = connection.createStatement();
            String sql = "SELECT" + 
            		"	COLUMN_NAME property," + 
            		"	DATA_TYPE type, " + 
            		"	COLUMN_COMMENT comment " + 
            		"FROM" + 
            		"	information_schema. COLUMNS " + 
            		"WHERE" + 
            		"	table_name = '" + tableName + "' " + 
            		"and table_schema = '" + dbName + "'";
            ResultSet resultSet = stmt.executeQuery(sql);
//            DatabaseMetaData databaseMetaData = connection.getMetaData();
//            ResultSet resultSet = databaseMetaData.getColumns(null,"%", tableName,"%");
            
            //生成实体类文件
            generateEntityFile(resultSet);
            //生成Model文件
//            generateModelFile();
            //生成Mapper文件
            resultSet = stmt.executeQuery(sql);
            generateMapperFile(resultSet);
            //生成Dao文件
            generateDaoFile();
            
            
            //生成服务层接口文件
            generateServiceInterfaceFile();
            //生成服务实现层文件
            generateServiceImplFile();
            //生成Controller层文件
            generateControllerFile();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally{

        }
    }

    private void generateControllerFile() throws Exception{
        final String suffix = "Controller.java";
        final String path = diskPath + changeTableName + suffix;
        System.out.println(path);
        final String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceImplFile() throws Exception{
        final String suffix = "ServiceImpl.java";
        final String path = diskPath + changeTableName + suffix;
        System.out.println(path);
        final String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateServiceInterfaceFile() throws Exception{
        final String prefix = "I";
        final String suffix = "Service.java";
        final String path = diskPath + prefix + changeTableName + suffix;
        System.out.println(path);
        final String templateName = "ServiceInterface.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateModelFile() throws Exception{

        final String suffix = "Model.java";
        final String path = diskPath + changeTableName + suffix;
        System.out.println(path);
        final String templateName = "Model.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }
    
    private void generateDaoFile() throws Exception{
        final String suffix = "Dao.java";
        final String path = diskPath + "I" + changeTableName + suffix;
        System.out.println(path);
        final String templateName = "Dao.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        generateFileByTemplate(templateName,mapperFile,dataMap);

    }

    private void generateMapperFile(ResultSet resultSet) throws Exception{
        final String suffix = "Mapper.xml";
        
        final String path = diskPath + changeTableName + suffix;
        System.out.println(path);
        
        final String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        
        List<ColumnClass> columnClassList = new ArrayList<>();
        ColumnClass columnClass = null;
        while(resultSet.next()){
            //id字段略过
            //if(resultSet.getString("name").equals("ID")) continue;
            columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("property"));
            //获取字段类型
            columnClass.setColumnType(resultSet.getString("type"));
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("property")));
            columnClassList.add(columnClass);
        }
        dataMap.put("model_column",columnClassList);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }
    
    private void generateEntityFile(ResultSet resultSet) throws Exception {
        final String suffix = ".java";
        
        final String path = diskPath + changeTableName + suffix;
        System.out.println(path);
        
        final String templateName = "Entity.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<>();
        
        List<ColumnClass> columnClassList = new ArrayList<>();
        ColumnClass columnClass = null;
        while(resultSet.next()){
            //id字段略过
            //if(resultSet.getString("name").equals("ID")) continue;
            columnClass = new ColumnClass();
            //获取字段名称
            columnClass.setColumnName(resultSet.getString("property"));
            //获取字段类型
            String columnType = resultSet.getString("type");
            columnClass.setColumnType(columnType);
            //转换字段名称，如 sys_name 变成 SysName
            columnClass.setChangeColumnName(replaceUnderLineAndUpperCase(resultSet.getString("property")));
            //设置实体类属性类型
            columnClass.setChangeColumnType(typeMap.get(columnType));
            //获取字段注释 
            columnClass.setColumnComment(resultSet.getString("comment"));         
            columnClassList.add(columnClass);
        }
        dataMap.put("model_column",columnClassList);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private void generateFileByTemplate(final String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("table_name_small",tableName);
        dataMap.put("table_name",changeTableName);
        dataMap.put("author",AUTHOR);
        dataMap.put("date",DateUtils.getTime());
        dataMap.put("package_name",packageName);
        dataMap.put("table_annotation",tableAnnotation);
        dataMap.put("package_children", packageChildren);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }
    
    public static void main(String[] args) throws Exception {
        new CodeGenerateUtils().generate();
    }
}
