package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Analysis;


/**
 * @author kkpl 参数 aim:analysis| detection action:start|stop
 */
@WebServlet("/Control")
public class Control extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		String aim = req.getParameter("aim");
		String action = req.getParameter("action");
		System.out.println(aim + action);

		if (aim.equals("analysis")) {
			String[] words = req.getParameter("words").split("+");
			StringBuilder str = new StringBuilder("ana:");
			for (String string : words) {
				str.append(string).append("||");
			}
			System.out.println(str.toString());
			Analysis.setStr(str.substring(0, str.length() - 2));

			/*if (action.equals("start"))
				Analysis.setStr("anaStart");
			else if (action.equals("stop"))
				Analysis.setStr("anaStop");*/

		} else if (aim.equals("detection")) {
			if (action.equals("start")) {
				Analysis.setStr("test");

			} else if (action.equals("stop"))
				Analysis.setStr("detStop");

			//检测某AP下用户
		} else if (aim.equals("userBlock")) {
			//Analysis.setStr("users");
			String uMAC = req.getParameter("uMAC");
			String APMAC = req.getParameter("APMAC");

			String command = "aireplay-ng -0 100 -a " + APMAC + " -c " + uMAC
					+ " wlan0";
			//String command  = "aireplay-ng -0 0 -a "+APMAC+" -c "+uMAC+" wlan1";
			Analysis.setStr(command);

		} else if (aim.equals("block")) {
			Analysis.setStr("block");
			String mac = req.getParameter("MACd");
			System.out.println(mac);
			Analysis.setStr("aireplay-ng -0 0 -a " + mac + " wlan0");
		} else if (aim.equals("userInfo")) {
			String channel = req.getParameter("Channel");
			String APMAC = req.getParameter("APMAC");
			req.getSession().setAttribute("MAC", APMAC);
			String command = "airodump-ng -c " + channel + " --bssid " + APMAC
					+ " wlan0 -w /root/test_analysis/information/user/user";
			Analysis.setStr(command);
		} else if (aim.equals("switch")) {
			String num = req.getParameter("num");
			String sta = req.getParameter("sta");
			String com = "AT+STACH" + num + "=" + sta;
			Analysis.setCom(com);
		}
		else
			out.write("Worry Command");
	}

}
