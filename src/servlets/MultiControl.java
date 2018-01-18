package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Controller;
//多个子设备控制  未完成1215

public class MultiControl extends HttpServlet {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		// JSON参数---

		String ID = req.getParameter("ID");
		String IP_ADDR = req.getParameter("IP");
		int PORT = Integer.parseInt(req.getParameter("PORT"));
		String operate = req.getParameter("operate");
		Map<String, Controller> conMap = Controller.getConMap();

		
		Controller controller = conMap.get(ID);
		if (controller == null)
			controller = new Controller(ID, IP_ADDR, PORT);
		for (Entry<String, Controller> entry : conMap.entrySet()) {

			System.out.println(entry.getKey() + "---"
					+ entry.getValue().getIP_ADDR() + "--"
					+ entry.getValue().getID() + "--"
					+ entry.getValue().getPORT());
		}
		//用多线程写
		// controller.conn();
		// switch(operate){
		// case "start":
		// controller.start();
		// break;
		// case "stop":
		// controller.stop();
		// break;
		// case "close":
		// controller.close();
		// break;
		// }
	}

}
