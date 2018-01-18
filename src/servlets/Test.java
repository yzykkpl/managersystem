package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Analysis;
@WebServlet("/test")
public class Test extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String str = "aireplay-ng -0 10 -a "+"DC:FE:18:DD:E3:A3"+" -c "+"1C:15:1F:1F:34:C2 wlan0";
		String a="0";
		String b="1";
		String ana="ana:bytes";
		String com = "AT+STACH" + a + "=" + b;
		String command1 = "airodump-ng -c 6 --bssid DC:FE:18:DD:E3:A3 wlan0 -w /root/test_analysis/information/user/user";//-w /root/test_analysis/information/test/test";
		String command2="airodump-ng -c 11 --bssid C8:3A:35:18:87:88 wlan0 -w /root/test_analysis/information/user/user";
		Analysis.setStr(ana);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Analysis.setStr(str);
		//System.out.println(str);
	}
	
}
