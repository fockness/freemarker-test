package com.test.freemarker;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author shibin E-mail : shibin@uama.com.cn
 * @Date creation time ：2018-05-04 10:38
 * @Description
 * 
 */
public class HelloServlet extends HttpServlet {

	private Configuration cfg;

	@Override
	public void init() throws ServletException {
		super.init();
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		cfg = new Configuration();
		// 设置FreeMarker的模版文件位置
		cfg.setServletContextForTemplateLoading(getServletContext(), "templates");
		System.out.println(getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 建立数据模型
		Map<String, String> map = new HashMap<String, String>();
		// 放入对应数据key value
		map.put("user", "Zheng");
		// 取得模版文件
		Template t = cfg.getTemplate("hello.ftl");
		// 开始准备生成输出
		// 使用模版文件的charset作为本页面的charset
		// 使用text/html MIME-type
		response.setContentType("text/html; charset=" + t.getEncoding());
		PrintWriter out = response.getWriter();
		// 合并数据模型和模版，并将结果输出到out中
		try {
			t.process(map, out);// 用模板来开发servlet可以只在代码里面加入动态的数据
		} catch (TemplateException e) {
			throw new ServletException("处理Template模版中出现错误", e);
		}
	}
}
