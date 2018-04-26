package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		String aim = req.getParameter("aim");
		String action = req.getParameter("action");
		// System.out.println(aim + action);
		String word = req.getParameter("words");

		if (aim.equals("analysis")) {
			if (action!=null&&action.equals("stop"))
				Analysis.setStr("stop_analysis");
			else {
				System.out.println(word);
				StringBuilder str = new StringBuilder();
				String[] words = word.split("\\+");

				String strUTF = URLEncoder.encode(words[2].trim(), "UTF-8");

				if (strUTF.contains("%")) {
					String[] r = strUTF.substring(1).split("%");
					StringBuilder keyWord = new StringBuilder();
					for (String string : r) {
						keyWord = keyWord.append(Integer.toOctalString(Integer.parseInt(string, 16)));
					}
					words[2] = keyWord.toString();
				}
				for (String string : words) {
					string = string.trim();
					if (!string.isEmpty())
						str.append(string).append("|");
				}
				System.out.println("result:--" + str.toString() + "--");
				Analysis.setStr("ana:" + str.substring(0, str.length() - 1));
				out.write("0");
			}

		} else if (aim.equals("detection")) {
			if (action.equals("start")) {
				Analysis.setStr("test");

			} else if (action.equals("stop"))
				Analysis.setStr("stop_test");

			// 检测某AP下用户
		} else if (aim.equals("userBlock")) {
			if (action!=null&&action.equals("stop")) {
				Analysis.setStr("stop_block");
			} else {
				String uMAC = req.getParameter("uMAC");
				String APMAC = req.getParameter("APMAC");
				String command = "aireplay-ng -0 100 -a " + APMAC + " -c " + uMAC + " wlan0";
				Analysis.setStr(command);
			}

		} else if (aim.equals("block")) {
			if (action.equals("stop"))
				Analysis.setStr("stop_block");
			else {
				String mac = req.getParameter("MACd");
				System.out.println(mac);
				Analysis.setStr("aireplay-ng -0 0 -a " + mac + " wlan0");
			}
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
		} else
			out.write("Worry Command");
	}

}
