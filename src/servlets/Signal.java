package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

//import javafx.scene.chart.PieChart.Data;
import tools.DealJSON;
@WebServlet("/signal")
public class Signal extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		JSONArray signalInfo = DealJSON.getSignalArray();
		if(signalInfo.size()>0)
			out.write(signalInfo.toString());
		else out.write("0");
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		readToJSON(req);
		
	}

	private void readToJSON(HttpServletRequest req) {
		Class<?> dealJSON = DealJSON.class;
		synchronized (dealJSON) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						(ServletInputStream) req.getInputStream(), "utf-8"));
				String sb=br.readLine();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
				String freq=(sb.substring(sb.indexOf("=")+1, sb.length()));
				String time =df.format(System.currentTimeMillis());
				StringBuilder result =  new StringBuilder("{\"time\":\"");
				result.append(time).append("\"");
				result.append(",\"freq\":\"").append(freq).append("\"}");
				DealJSON.addSignal(result.toString(),freq,time);
				System.out.println(result);
				br.close();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		
	}

}
