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
	private ChatUIManager target = null;

	public ChatAdapter(ChatUIManager target) {
		this.target = target;
	}
}

class ChatListAdapter {
	private ChatListManager target = null;
	private String userName = new String();

	private LoginAdapter loginAdapter = null;

	private User user = null;
	private int roomCount;
	private String roomText = null;

	public Vector<String> chatRoomList = new Vector<String>();

	public ChatListAdapter(ChatListManager target) {
		this.target = target;
		userName = "test name";
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
		return this.userName;
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
}