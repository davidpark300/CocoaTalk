package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UIAdapter {

}

class LoginAdapter {
	private LoginUIManager target = null; 
	private HashMap<String, String> tmpIDPWDB = new HashMap<String, String>();
	
	public LoginAdapter(LoginUIManager target) {
		this.target = target;
		tmpIDPWDB.put("Amy", "a1234");
		tmpIDPWDB.put("Betty", "b1234");
		tmpIDPWDB.put("Cynthia", "c1234");
		tmpIDPWDB.put(" ", " ");
	}
	
	public int verifyIDPW(String id, String pw) {
		if (tmpIDPWDB.get(id) == null) return -1;
		if (tmpIDPWDB.get(id).equals(pw) == false) return 0;
		return 1;
	}
}

class ChatAdapter {
	private ChatUIManager target = null;
	
	public ChatAdapter(ChatUIManager target) {
		this.target = target;
	}
}

class ChatListAdapter {
	private ChatListManager target = null;
	private String userName = new String();
	
	public LinkedList<String> chatRoomList = new LinkedList<String>();
	
	public ChatListAdapter(ChatListManager target) {
		this.target = target;
		userName = "test name";
		for (int i = 0; i < 30; i++) {
			chatRoomList.add("room number " + i);
		}
	}

	public String getUserName() {
		return this.userName;
	}
	public int size() {
		return chatRoomList.size();
	}
	public String getRoomName(int index) {
		return chatRoomList.get(index);
	}
}