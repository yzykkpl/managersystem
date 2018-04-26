package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import tools.Analysis;
import tools.DealJSON;

/**
 * @author kkpl 没用
 */
@WebServlet("/getAnalysis")
public class GetAnalysis extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/x-www-form-urlencoded");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		
		Class<?> dealJSON = DealJSON.class;
		synchronized (dealJSON) {
			JSONObject APMAC=new JSONObject();
			APMAC.put("APMAC", Analysis.getAPMAC());
			JSONArray anaResult = DealJSON.getAnaResultArray();
			if (anaResult.size()>0) {
				anaResult.add(APMAC);
				System.out.println("--------------");
				System.out.println(anaResult.toJSONString());
				System.out.println("--------------");
				out.write(anaResult.toJSONString());
			}
		}
	}
}
