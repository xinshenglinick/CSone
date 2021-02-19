package com.xsl.utils;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * 创建人：FH 创建时间：2015年2月8日
 * @version
 */
public class Freemarker {

	public static String packageName  = "com.xsl";
	public static String pojoproject="pyg_pojo";
	public static String serviceproject="pyg_sellergoods_interface";
	public static String serviceImplproject="pyg_sellergoods_service";
	public static String controllerproject="pyg_manager_web";
	public static String commonrproject="pyg_common";
	//ftl模板文件路径
	public static String ftlpath=System.getProperty("user.dir")+"\\"+commonrproject+"\\src\\main\\resources\\freemarkerftl";

	/**
	 * 单表代码生成器
	 * @param tablename
	 */
	public static void createfiles(String tablename){
		String entityNamelower=lineToHump(tablename);//首字母小写的驼峰
		String entityName=upperStr(entityNamelower);//首字母大写的驼峰

		//生成service接口
		createMian(tablename,packageName+".pojo",entityName, "pojo.ftl",
				System.getProperty("user.dir")+"//"+pojoproject+"\\src\\main\\java\\"
						+packageName.replace(".","\\")+"\\pojo\\"+entityName+".java");

		//生成service接口
		createMian(tablename,packageName+".service",entityName, "service.ftl",
				System.getProperty("user.dir")+"//"+serviceproject+"\\src\\main\\java\\"
						+packageName.replace(".","\\")+"\\service\\"+entityName+"Service.java");


		//生成serviceIMPL接口
		createMian(tablename,packageName+".sellergoods.service.impl",entityName, "serviceImpl.ftl",
				System.getProperty("user.dir")+"//"+
						serviceImplproject+"\\src\\main\\java\\"+packageName.replace(".","\\")+"\\sellergoods\\service\\impl\\"
						+entityName+"ServiceImpl.java");

		//生成contrller接口
		createMian(tablename,packageName+".shop.controller",entityName,
				"controller.ftl",
				System.getProperty("user.dir")+"//"+
						controllerproject+"\\src\\main\\java\\"+packageName.replace(".","\\")+ "\\shop\\controller\\"
						+entityName+"Controller.java");
		//生成contrllerjs
		createMian(tablename,"",entityName,
				"controllerjs.ftl",
				System.getProperty("user.dir")+"//"+
						controllerproject+"\\src\\main\\webapp\\js\\controller\\"+entityNamelower+"Controller.js");

		//生成servicejs
		createMian(tablename,"",entityName,
				"servicejs.ftl",
				System.getProperty("user.dir")+"//"+
						controllerproject+"\\src\\main\\webapp\\js\\service\\"+entityNamelower+"Service.js");
	}
	/**
	 * tableName表名
	 * packageName包名
	 * entityName类名
	 * ftlname模板名称
	 * savepath生成文件保存路径
	 * 2017年3月16日下午8:47:33
	 *  wangyue
	 */
	public static void createMian(String tableName,String packageName,String entityName,String ftlname,String savepath){
		Map<String,Object> data = new HashMap<String,Object>();		//创建数据模型
		List<String[]> fields = getFields(tableName);
		data.put("fieldList",fields );
		data.put("idfield", fields.get(0));
		data.put("tableName", tableName);						//表名
		data.put("packageName", packageName);						//包名
		data.put("entityName", entityName);							//类名
		data.put("entityNameLower", lowerStr(entityName));		//(类名，首字母转小写）
		data.put("nowDate", new Date());
		Freemarker.printFile(ftlname, data, savepath);
	}

	/**
	 * 打印到控制台(测试用)
	 */
	public static void print(String ftlName, Map<String,Object> root) throws Exception{
		try {
			Template temp = getTemplate(ftlName);		//通过Template可以将模板文件输出到相应的流
			temp.process(root, new PrintWriter(System.out));
		} catch (TemplateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 输出到输出到文件
	 * @param ftlName   ftl文件名
	 * @param root		传入的map
	 * @param outFile	输出后的文件全部路径
	 */
	private static void printFile(String ftlName, Map<String,Object> root, String outFile){
		try {
			File file = new File(outFile);
			if(!file.getParentFile().exists()){				//判断有没有父路径，就是判断文件整个路径是否存在
				file.getParentFile().mkdirs();				//不存在就全部创建
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			Template template = getTemplate(ftlName);
			template.process(root, out);					//模版输出
			out.flush();
			out.close();
			System.out.println("======创建成功======"+outFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通过文件名加载模版
	 */
	public static Template getTemplate(String ftlName) throws Exception{
		try {
			Configuration cfg = new Configuration();  	//通过Freemaker的Configuration读取相应的ftl
			cfg.setEncoding(Locale.CHINA, "utf-8");
			cfg.setDirectoryForTemplateLoading(new File(ftlpath));		//设定去哪里读取相应的ftl模板文件
			Template temp = cfg.getTemplate(ftlName);		//在模板文件目录中找到名称为name的文件
			return temp;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取发布根目录
	 * 2017年3月16日下午8:06:09
	 *  wangyue
	 */
	private static String getResouce() {
		String path =  (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
		if(path.indexOf(":") != 1){
			path = File.separator + path;
		}
		return path;
	}

	public static List<String> gettables() throws Exception {
		DatabaseMetaData dbmd = DbConUtil.getcon().getMetaData();
		ResultSet rstables = dbmd.getTables(null, null, "%", null);//获取所有表
		List<String> tables = new ArrayList<>();
		while (rstables.next()) {
			tables.add(rstables.getString("TABLE_NAME"));
		}
		return tables;
	}
	/**
	 * 获取字段信息
	 */
	public static List<String[]> getFields(String tableName){
		List<String[]> fieldList = new ArrayList<String[]>();
		try {
			DatabaseMetaData dbmd=DbConUtil.getcon().getMetaData();
			ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");//查询表的所有字段
			while(rs.next()) {
				String type = rs.getString("TYPE_NAME");//字段类型
				String name = rs.getString("COLUMN_NAME");//字段名称
				String remark = rs.getString("REMARKS");//字段注释
				String[] field= new String[5];
				String lname=lineToHump(name);//首字母小写的驼峰
				field[0]=lname;
				field[2]=remark;
				String hname=upperStr(lname);//首字母大写的驼峰
				field[4]=hname;//首字母大写

				if(type.equals("INT")){
					field[1]="Integer";
					field[3]="是";
				} else if(type.equals("BIGINT")){
					field[1]="Long";
					field[3]="是";
				} else{
					field[1]="String";
					field[3]="是";
				}
				fieldList.add(field);
			}
			return fieldList;
		} catch (Exception e) {
			e.printStackTrace();
			return fieldList;
		}
	}

	/**
	 * 把输入字符串的首字母改成大写
	 *
	 * @param str
	 * @return
	 */
	public static String upperStr(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 把输入字符串的首字母改成 小写
	 *
	 * @param str
	 * @return
	 */
	public static String lowerStr(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'A' && ch[0] <= 'Z') {
			ch[0] = (char) (ch[0] + 32);
		}
		return new String(ch);
	}

	private static Pattern linePattern = Pattern.compile("_(\\w)");
	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);

		return sb.toString();
	}

	public static void main (String[] args)  throws Exception{
		List<String> tables = gettables();//获取所有的表名；
		//循环生成所有表对应的代码
		for (String tablename:tables){
			createfiles(tablename);
		}

	}
}
