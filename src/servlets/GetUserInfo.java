package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mongo.ConnectDemo;
import tools.UserState;
@WebServlet("/getUserInfo")
public class GetUserInfo extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("login");
		PrintWriter out = resp.getWriter();
		ConnectDemo connect = ConnectDemo.getConnect();
		String result = connect.findAll();
		out.write(result);
		System.out.println(result);

	}


}
