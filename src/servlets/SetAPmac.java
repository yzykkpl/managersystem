package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Provider.Service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.Analysis;

@WebServlet("/setAPmac")
public class SetAPmac extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/x-www-form-urlencoded");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String APMAC=req.getParameter("APMAC");
		Analysis.setAPMAC(APMAC);
	}

	
}
