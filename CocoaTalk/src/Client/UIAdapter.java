package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.SQLChatList;
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
	private User user = null;
	private UserEdit userEdit = new UserEdit();
	private SQLChatList sqlChatList = null;
	
	static public enum MessageType {
		text
	}
	
	private ChatUIManager target = null;

	private String userNickName = new String();
	private String userID = new String();
	
	private ArrayList<String> senderIDList = new ArrayList<String>();
	private ArrayList<String> senderNickNameList = new ArrayList<String>();
	private ArrayList<MessageType> typeList = new ArrayList<MessageType>();
	private ArrayList<String> contentList = new ArrayList<String>();
	
	public ChatAdapter(ChatUIManager target) {
		this.target = target;
	}
	
	public void renew() {
		this.user = target.owner.loginUIManager.loginAdapter.getUserInfo();
		if (user != null) {
			userNickName = this.user.getUserNickName();
			userID = this.user.getUserID();
		}
	}

	public String getUserID() {
		return this.userID;
	}
	public String getUserNickName() {
		return this.userNickName;
	}

	public int getRoomSize() {
		if (user == null) return 0;
		return user.getUserRoomCount();
	}

	public String getRoomName(int index) {
		sqlChatList = userEdit.getUserChatList(user.getRoomName(index));
		return sqlChatList.getRoomName();
	}
	
	// room list
	// -----------
	// viewer
	
	public void setMessageLists() {
		for (int i = 0; i < 5; i++) {
			senderIDList.add("Amy");
			senderNickNameList.add("히히");
			typeList.add(MessageType.text);
			contentList.add("hi");
			
			senderIDList.add("Jack");
			senderNickNameList.add("hi");
			typeList.add(MessageType.text);
			contentList.add("hello");
		}
	}
	
	public int messageSize() {
		return senderIDList.size();
	}
	public String getSenderID(int index) {
		return senderIDList.get(index);
	}
	public String getSenderNickName(int index) {
		return senderNickNameList.get(index);
	}
	public MessageType getType(int index) {
		return typeList.get(index);
	}
	public String getContent(int index) {
		return contentList.get(index);
	}
}