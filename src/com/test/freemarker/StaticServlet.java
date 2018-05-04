package com.test.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author shibin E-mail : shibin@uama.com.cn
 * @Date creation time ：2018-05-04 10:38
 * @Description
 * 	使用List，Map，普通变量取值
 */
public class StaticServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 创建一个合适的Configration对象
			Configuration configuration = new Configuration();
			configuration.setDirectoryForTemplateLoading(new File("E:\\tomcat\\apache-tomcat-8.0(2)\\webapps\\freemarker-test\\templates"));
			configuration.setObjectWrapper(new DefaultObjectWrapper());
			configuration.setDefaultEncoding("UTF-8"); // 这个一定要设置，不然在生成的页面中 会乱码
			// 获取或创建一个模版。
			Template template = configuration.getTemplate("static.html");
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("description", "我正在学习使用Freemarker生成静态文件！");

			List<String> nameList = new ArrayList<String>();
			nameList.add("aa");
			nameList.add("bb");
			nameList.add("cc");
			paramMap.put("nameList", nameList);

			Map<String, Object> weaponMap = new HashMap<String, Object>();
			weaponMap.put("first", "1");
			weaponMap.put("second", "2");
			weaponMap.put("third", "3");
			weaponMap.put("fourth", "4");
			weaponMap.put("fifth", "5");
			weaponMap.put("sixth", "6");
			weaponMap.put("seventh", null);
			paramMap.put("weaponMap", weaponMap);
			paramMap.put("content", new ContentDirective());//key为自定义标签名，输出值输入值在ContentDirective定义
			response.setContentType("text/html; charset=" + template.getEncoding());
			PrintWriter out = response.getWriter();
			template.process(paramMap, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
}
