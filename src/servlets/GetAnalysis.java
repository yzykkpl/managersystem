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
			//String test="[{"srcIP":"(125.221.46.252)","dstMAC":"(7c:dd:90:a6:b0:f4)","srcMAC":"(58:66:ba:0d:81:00)","time":"2018-4-26 14:27:27","dstIP":"(192.168.140.155)","keyWord":"河北"}]"
			String anaResult = DealJSON.getAnaResultArray().toJSONString();
			System.out.println("--------------");
			System.out.println(anaResult);
			System.out.println("--------------");
			out.write(anaResult);
		}
	}
}
