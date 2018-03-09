package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;

public class Analysis {
	// private static Map<String, Socket> socketMap = new HashMap<String,
	// Socket>();
	private Socket socket = null;
	private ServerSocket server1 = null;//终端服务器
	private ServerSocket server2 = null;//干扰器服务器
	private AcceptThread1 acceptThread1;
	private AcceptThread2 acceptThread2;
	private static String str = null;
	private static String com = null;

	public static String getCom() {
		return com;
	}

	public static void setCom(String com) {
		Analysis.com = com;
	}

	public static ArrayList<String[]> infos = new ArrayList<String[]>();

	private static boolean flag = false;

	public void ServerGo() {
		try {

			server1 = new ServerSocket(5210);
			server2 = new ServerSocket(5209);
			System.out.println("socket开启");
			acceptThread1 = new AcceptThread1(server1);

			acceptThread2 = new AcceptThread2(server2);
			acceptThread1.start();
			acceptThread2.start();

		} catch (Exception e) {
			System.out.println("Error." + e);
		}
	}

	public void close() {
		try {

			server1.close();
			server2.close();
			System.out.println("server closed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setFlag(boolean f) {
		flag = f;
	}

	public static boolean getFlag() {
		return flag;
	}

	public static String getStr() {
		return str;
	}

	public static void setStr(String str) {
		Analysis.str = str;
		System.out.println(str + " 发送成功");
	}

}

class AcceptThread1 extends Thread {
	private ServerSocket server = null;
	private Socket socket = null;

	public AcceptThread1(ServerSocket server) {
		this.server = server;
	}

	public void run() {
		try {
			while (!Analysis.getFlag()) {
				socket = server.accept();

				System.out.println("socket1:" + socket.getInetAddress());
				WriteThread1 writeThread1 = new WriteThread1(socket);
				ReadThread1 readThread1 = new ReadThread1(socket);
				readThread1.start();
				writeThread1.start();
				//Analysis.setStr("airodump-ng -c "+"6"+" --bssid "+"DC:FE:18:DD:E3:A3"+" wlan0 -w /root/test_analysis/information/user");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ReadThread1 extends Thread {
	private Socket socket = null;
	private static String function = null;
	private SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");

	public ReadThread1(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {

			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String str = null;
			JSONObject apInfo = new JSONObject();
			while ((str = in.readLine()) != null) {
				//System.out.println(str);
				synchronized (DealJSON.class) {
					if (str.contains("test:")) {
						System.out
								.println(DealJSON.getAPInfoArray().toString());
						DealJSON.getAPInfoArray().clear();
						
					}
					else if(str.contains("user:")){
						System.out
						.println(DealJSON.getAPUserArray().toString());
						DealJSON.getAPUserArray().clear();
					}
					/*
					 * else if(str.equals("users:")){
					 * DealJSON.getAPuserArray().clear(); }
					 */
					// ------------------------APInfo-----------------------------------------------------
					else if (str.contains("Address")) {
						int index = str.indexOf(':');
						String mac = str.substring(index + 1).trim();
						apInfo.put("MAC", mac);

					} else if (str.contains("Channel:")) {
						int index = str.indexOf(':');
						String channel = str.substring(index + 1).trim();
						apInfo.put("Channel", channel);

					} else if (str.contains("Frequency")) {
						int index = str.indexOf(':');
						String frequency = str.substring(index + 1, index + 6)
								.trim();
						apInfo.put("Frequency", frequency);

					} else if (str.contains("Signal")) {
						int index = str.indexOf("=-");
						String power = str.substring(index + 1).trim();
						apInfo.put("Power", power);

					} else if (str.contains("ESSID")) {
						str = str.trim();
						int index = str.indexOf(':');
						String ssid = str.substring(index + 2,
								str.length() - 1);
						apInfo.put("SSID", ssid);

						DealJSON.addAPInfo(apInfo);

						apInfo = new JSONObject();

						// ------------------------userInfo-----------------------------------------------------
					} else if (str.length() > 5 && str.startsWith("--2:")) {
						str = str.substring(4);
						String[] APuser = str.split(",");
						System.out.println("user--" + str);

						if (APuser[5].trim().length() == 17) {

							DealJSON.addAPuser(APuser);
							// System.out.println(str);
						}
					}
					// ------------------------anaResult-----------------------------------------------------
					else if (str.length() > 5 && str.startsWith("--3:")) {
						str = str.substring(4);
						System.out.println(str);
						/*
						 * String[] temp = str.split(","); String time =
						 * df.format(System.currentTimeMillis()); String srcMAC
						 * = temp[1].trim().substring(temp[1].length() - 19,
						 * temp[1].length() - 2); String dstMAC =
						 * temp[2].trim().substring(temp[2].length() - 19,
						 * temp[2].length() - 2); String[] ana = new String[] {
						 * srcMAC, dstMAC, time }; DealJSON.addAnaResult(ana);
						 * System.out.println(ana[0] + "  " + ana[1]);
						 */

					}

					// in.close();
				}
			}

		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			try {
				socket.close();
				System.out.println("socket:" + socket.toString() + "  close");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

class WriteThread1 extends Thread {
	private Socket socket = null;
	PrintWriter writer;

	public WriteThread1(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
					true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {

		while (!Analysis.getFlag()) {
			try {
				if (socket.isClosed()) {
					writer.close();

					break;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// System.out.println(Analysis.getStr());
			if (Analysis.getStr() != null) {

				writer.write(Analysis.getStr());
				writer.flush();
				Analysis.setStr(null);
			}
		}

	}
}

class AcceptThread2 extends Thread {
	private ServerSocket server = null;
	private Socket socket = null;

	public AcceptThread2(ServerSocket server) {
		this.server = server;
	}

	public void run() {
		try {
			while (!Analysis.getFlag()) {
				socket = server.accept();

				System.out.println("socket2:" + socket.getInetAddress());
				WriteThread2 writeThread2 = new WriteThread2(socket);
				ReadThread2 readThread2 = new ReadThread2(socket);
				readThread2.start();
				writeThread2.start();
				//Analysis.setStr("airodump-ng -c "+"6"+" --bssid "+"DC:FE:18:DD:E3:A3"+" wlan0 -w /root/test_analysis/information/user");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class ReadThread2 extends Thread {
	private Socket socket = null;

	public ReadThread2(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {

			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			String str = null;
			while ((str = in.readLine()) != null && !socket.isClosed()) {
				System.out.println(str);
			}

		} catch (IOException | ArrayIndexOutOfBoundsException e) {
			try {
				socket.close();
				System.out.println("socket2:" + socket.toString() + "  close");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

class WriteThread2 extends Thread {
	private Socket socket = null;
	PrintWriter writer;

	public WriteThread2(Socket socket) {
		this.socket = socket;
		try {
			writer = new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(), "UTF-8"),
					true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {

		while (!Analysis.getFlag()) {
			try {
				if (socket.isClosed()) {
					writer.close();

					break;
				}
				Thread.sleep(1000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			if (Analysis.getCom() != null) {

				writer.write(Analysis.getCom() + "\n");
				System.out.println("ok");
				writer.flush();
				Analysis.setCom(null);
			}
		}

	}
}
