package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.Document;

import mongo.ConnectDemo;

@WebServlet("/usermanage")
public class UserManage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConnectDemo connect=ConnectDemo.getConnect();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		
		String username = req.getParameter("username");//操作用户名
		String operate = req.getParameter("operate");//操作
		String auth = "null";//操作用户权限
		String aimUsername = req.getParameter("aimUsername");//操作对象用户名
		String key = req.getParameter("key");//操作对象用户的字段
		String value = req.getParameter("value");//操作对象用户的字段值
		Document user = connect.findByUsername(username);//操作用户
		Document aimUser = connect.findByUsername(aimUsername);//操作对象用户
		if (user != null && aimUser != null) {
			auth = user.getString("auth");
			if (auth!=null && auth.equals("Administrator")) {
				//删除用户
				if (operate.equals("delete")) {
					int result = connect.delete(aimUser);
					if(result==0)
						out.write("1");
					else{
						out.write("0");
					}

				}
				//更新用户
				if (operate.equals("update")) {
					String oldValue = connect.update(aimUser, key, value);
					if(!oldValue.isEmpty())
						out.print("1");
					else out.print("0");

				}
			}
			else{
				out.write("用户 "+username+" 没有权限执行该操作！");
			}
		} else {
			out.write("目标用户不存在");//目前问题：前端JS要识别这三种返回结果。
		}

	}
	

}
