package tools;

import java.util.ArrayList;

public class UserState {
	private static ArrayList<String> loggedUser=new ArrayList<String>();
	public static String getLoggedUser() {
		return loggedUser.toString();
	}
	public static void updateLog(String operate,String username){
		if(operate.equals("login")){
			if(!loggedUser.contains(username))
				loggedUser.add(username);
			
		}else if(operate.equals("logout")){
			if(loggedUser.contains(username))
				loggedUser.remove(username);
			
		}
		
		
	}
	
}
