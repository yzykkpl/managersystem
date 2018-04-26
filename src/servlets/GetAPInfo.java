package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.json.JsonMode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tools.Analysis;
import tools.DealJSON;

@WebServlet("/getAPInfo")
public class GetAPInfo extends HttpServlet {
	JSONArray AllUsers = new JSONArray();
	JSONArray users = new JSONArray();
	String Info = null;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.write(Info);
	}

	@Override
	protected void doPost(HttpServletRequest req, javax.servlet.http.HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String demand = req.getParameter("demand");
		String M = req.getParameter("MAC");
		//System.out.println(demand+"  "+M);
		Class<?> dealJSON = DealJSON.class;
		synchronized (dealJSON) {
			if (demand.equals("wifi")){
				Info = DealJSON.getAPInfoArray().toJSONString();
				System.out.println("当前ap信息："+Info);
			}
			else if (demand.equals("users")) {
			
				String MAC = (String) req.getSession().getAttribute("MAC");
				AllUsers = (JSONArray) DealJSON.getAPUserArray().clone();
				users.clear();
				for (int i = 0; i < AllUsers.size(); i++) {
					JSONObject temp = AllUsers.getJSONObject(i);
					if (temp.containsValue(MAC)) {
						users.add(temp);
					}
				}
				Info = users.toString();
				System.out.println("-!-!----"+Info);

			} else
				Info = "Worry demand";
			out.write(Info);
		}
	}
}
