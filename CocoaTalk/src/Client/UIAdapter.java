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
	private User user = null;

	public LoginAdapter(LoginUIManager target) { // sql 로그인 tb랑 비교해서 확인하기
		this.target = target;
		tmpIDPWDB.put("Amy", "a1234");
		tmpIDPWDB.put("Betty", "b1234");
		tmpIDPWDB.put("Cynthia", "c1234");
		tmpIDPWDB.put(" ", " ");
	}

	public User getUserInfo() {
		return user;
	}

	public int verifyIDPW(String id, String pw) { // 여기서 확인
		if (userEdit.loginUser(id, pw) == null)
			return 0;
		user = userEdit.loginUser(id, pw);
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
	
	private ChatListManager target = null;
	private String userName = new String();

	private LoginAdapter loginAdapter = null;

	private User user = null;
	private int roomCount;
	private String roomText = null;

	public Vector<String> chatRoomList = new Vector<String>();
	
	public ChatAdapter(ChatUIManager target) {
		this.target = target;
		userNickName = "test name";
		for (int i = 0; i < 30; i++) {
			chatRoomList.add("room number " + i);
		}
	}
	
	public void setChatList() {
		user = loginAdapter.getUserInfo();
		chatRoomList.clear();
		for (String keys : user.getUserRoomKeys())
			chatRoomList.add(keys);
	}

	public String getUserName() {
		return this.userNickName;
	}

	public int size() {
		return chatRoomList.size();
	}

	public void setRoomCount() {
		roomCount = user.getUserRoomCount();

		System.out.println(roomCount);
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