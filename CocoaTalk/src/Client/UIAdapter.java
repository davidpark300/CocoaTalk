package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.ChatHistory;
import Server.ChatHistoryEdit;
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
	private Vector<ChatHistoryEdit> vChatHistory = new Vector<ChatHistoryEdit>();

	static public enum MessageType {
		text
	}

	private ChatUIManager target = null;

	private String userNickName = new String();
	private String userID = new String();

	private int roomIndex = -1;
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
		if (user == null)
			return 0;
		return user.getUserRoomCount();
	}

	public String getRoomName(int index) {
		sqlChatList = userEdit.getUserChatList(user.getRoomName(index));
		getChatHistory(user.getRoomName(index));
		return sqlChatList.getRoomName();
	}

	public void getChatHistory(String roomKey) {
		ChatHistoryEdit chatHistoryEdit = new ChatHistoryEdit(roomKey);
		vChatHistory.add(chatHistoryEdit);
		// vChatHistory = chatHistoryEdit.getVectorChatHistory();
	}

	// room list
	// -----------
	// viewer

	public void setMessageLists() {
		resetArrayList();
		if (vChatHistory != null && vChatHistory.size() != 0 && roomIndex != -1) {
			for (ChatHistory chatHistory : vChatHistory.get(roomIndex).getVectorChatHistory()) {
				senderIDList.add(chatHistory.getChatHistorySenderID());
				senderNickNameList.add(chatHistory.getChatHistorySenderNickName());
				typeList.add(MessageType.text);
				contentList.add(chatHistory.getChatHistoryText());
			}
		}
	}

	public void resetArrayList() {
		senderIDList.clear();
		senderNickNameList.clear();
		typeList.clear();
		contentList.clear();
	}
	
	public void setRoomIndex(int index) {
		this.roomIndex = index;
	}

	public int getRoomIndex() {
		return roomIndex;
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