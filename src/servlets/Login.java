package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mongo.ConnectDemo;
import tools.Analysis;
import tools.UserState;

@WebServlet(urlPatterns="/login",loadOnStartup=1)
public class Login extends HttpServlet{
	Analysis ana;
	@Override
	public void init(ServletConfig config) throws ServletException {
		ana =new Analysis();
		ana.ServerGo();
		ServletContext serc=config.getServletContext();
		serc.setAttribute("Analysis", ana);
	}


	private static final long serialVersionUID = 1L;
	

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("login");
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username);
		ConnectDemo connect=ConnectDemo.getConnect();
		int result = 0;
		//int result = connect.login(username, password);
		switch(result){
			case 0:
				//成功--TODO
				req.getSession().setAttribute("USER_IN_SESSION", username);
				out.write("0");
				UserState.updateLog("login", username);
				System.out.println("登陆成功");
				break;
			case 1:
				out.write("1");
				//密码错误--TODO
				break;
			case 2:
				out.write("2");
				//用户不存在--TODO
				break;
			case 3:
				out.write("3");
				//error--TODO
				break;	
				
		}
	}
	@Override
	public void destroy() {
		ConnectDemo connect=ConnectDemo.getConnect();
		connect.close();
		ana.close();
	}
	
	

}
