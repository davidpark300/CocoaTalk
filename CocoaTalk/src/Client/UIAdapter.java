package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.User;
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
	
	public void register(String ID, String PW, String Nick) {
		userEdit.registerUser(new User(ID, PW, Nick));
	}
}

class ChatAdapter {
	static public enum MessageType {
		text
	}
	
	private ChatUIManager target = null;

	private String userNickName = new String();
	
	private Vector<String> chatRoomList = new Vector<String>();
	
	private ArrayList<String> senderList = new ArrayList<String>();
	private ArrayList<MessageType> typeList = new ArrayList<MessageType>();
	private ArrayList<String> contentList = new ArrayList<String>();
	
	public ChatAdapter(ChatUIManager target) {
		this.target = target;
		this.target = target;
		userNickName = "test name";
		for (int i = 0; i < 30; i++) {
			chatRoomList.add("room number " + i);
		}
	}
	
	public String getUserName() {
		return this.userNickName;
	}
	public int size() {
		return chatRoomList.size();
	}
	public String getRoomName(int index) {
		return chatRoomList.get(index);
	}
	
	public void setMessageLists() {
		for (int i = 0; i < 5; i++) {
			senderList.add("test name");
			typeList.add(MessageType.text);
			contentList.add("hi");
			senderList.add("Jack");
			typeList.add(MessageType.text);
			contentList.add("hello");
		}
	}
	
	public int messageSize() {
		return senderList.size();
	}
	public String getSender(int index) {
		return senderList.get(index);
	}
	public MessageType getType(int index) {
		return typeList.get(index);
	}
	public String getContent(int index) {
		return contentList.get(index);
	}
}

class ChatListAdapter {
}

class ViewerUIAdapter {
	private ViewerUIManager target;
	public ViewerUIAdapter(ViewerUIManager target) {
		this.target = target;
	}
}