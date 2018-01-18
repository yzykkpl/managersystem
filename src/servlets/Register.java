package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import mongo.ConnectDemo;

@WebServlet("/register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email =req.getParameter("email");
		String auth = req.getParameter("auth");
		PrintWriter out = resp.getWriter();
		req.setCharacterEncoding("UTF-8");
		ConnectDemo connect=ConnectDemo.getConnect();
	
		System.out.println(username);
		System.out.println(password);
		
		int result = connect.register(username, password, email, auth);
		switch (result) {
		case 0:
			out.write('0');
			System.out.println("注册成功");
			//成功--TODO
			break;
		case 1:
			//用户已存在--TODO
			out.write('1');
			System.out.println("已存在!!!!!");
			break;
		case 2:
			out.write('2');
			System.out.println("错误!!!");
			//error
			break;

		}
	}
	

}
