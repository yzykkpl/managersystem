package tools;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Controller {
	private String ID;
	private Socket socket = null;
	private String IP_ADDR;
	private int PORT;
	private BufferedReader in;
	private PrintWriter out;
	private static Map<String, Controller> conMap = new HashMap<String, Controller>();

	public static Map<String, Controller> getConMap() {
		return conMap;
	}

	public Controller(String ID, String IP_ADDR, int PORT) {
		this.ID = ID;
		this.IP_ADDR = IP_ADDR;
		this.PORT = PORT;
		conMap.put(ID, this);
	}

	public Controller() {
	}

	public void conn() {
		try {
			// 创建一个流套接字并将其连接到指定主机上的指定端口号
			if (!socket.isConnected()) {
				this.socket = new Socket(IP_ADDR, PORT);
				System.out.println("已连接" + ID);
			}

		} catch (Exception e) {
			System.out.println("连接失败:" + e.getMessage());
		}

	}

	public void start() {
		try {
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "UTF-8"));
			out = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
					true);
			out.write("start");
			out.close();
			if (in.readLine().equals("started")) {
				System.out.println("成功开启 " + this.ID);
			} else {
				System.out.println("开启失败 " + this.ID);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String str = new BufferedReader(new
		// InputStreamReader(System.in)).readLine();

	}

	public void stop() {
		try {
			in = new BufferedReader(
					new InputStreamReader(socket.getInputStream(), "UTF-8"));
			out = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
					true);
			out.write("stop");
			out.close();
			if (in.readLine().equals("stoped")) {
				System.out.println("成功关闭 " + this.ID);
			} else {
				System.out.println("关闭失败 " + this.ID);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getID() {
		return ID;
	}

	public Socket getSocket() {
		return socket;
	}

	public String getIP_ADDR() {
		return IP_ADDR;
	}

	public int getPORT() {
		return PORT;
	}
}
