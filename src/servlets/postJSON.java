package servlets;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import tools.DealJSON;

@WebServlet("/json")
public class postJSON extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("1");
		readJSON(req);
		PrintWriter out = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		JSONArray jArray = DealJSON.getAPInfoArray();
		System.out.println(jArray.toJSONString());
		//out.println(jArray.getJSONObject(0).getString("name"));

	}

	//读JSON数据并保存到JSONArray
	private void readJSON(HttpServletRequest req) {
		Class<?> dealJSON = DealJSON.class;
		synchronized (dealJSON) {

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(ServletInputStream) req.getInputStream(), "utf-8"));
				StringBuffer sb = new StringBuffer("");
				int c;
				while ((c = br.read()) != -1) {
					if ((char) c == '/') {
						//DealJSON.addAPInfo(sb.toString());
						if ((c = br.read()) == -1)
							break;
					}
					sb.append((char) c);

				}
				//DealJSON.addAPInfo(sb.toString());
				System.out.println(sb.toString());
				br.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}
}
