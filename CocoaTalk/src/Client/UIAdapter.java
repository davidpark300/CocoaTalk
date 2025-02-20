package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.UserEdit;

public class UIAdapter {

}

class LoginAdapter {
	private LoginUIManager target = null; 
	private HashMap<String, String> tmpIDPWDB = new HashMap<String, String>();
	private UserEdit userEdit = new UserEdit();
	
	public LoginAdapter(LoginUIManager target) { // sql 로그인 tb랑 비교해서 확인하기
		this.target = target;
		tmpIDPWDB.put("Amy", "a1234");
		tmpIDPWDB.put("Betty", "b1234");
		tmpIDPWDB.put("Cynthia", "c1234");
		tmpIDPWDB.put(" ", " ");
	}
	
	public int verifyIDPW(String id, String pw) { // 여기서 확인
		if(userEdit.loginUser(id,pw) != 1) return 0;
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