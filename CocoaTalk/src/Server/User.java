package Server;

import java.util.Vector;

public class User {
	private String userID; // ID
	private String userPW; // PW
	private String userNickName; // NickName
	private Vector<String> userRoomKeys = null;// = new Vector<String>();
	private SQLChatList sqlChatList = null;
	public User() {

	}

	public User(String userID, String userPW, String userNickName) {
		this.userID = userID;
		this.userPW = userPW;
		this.userNickName = userNickName;
		userRoomKeys = new Vector<String>();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPW() {
		return userPW;
	}

	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}

	public String getUserNickName() {
		return userNickName;
	}

	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}

	public Vector<String> getUserRoomKeys() {
		return userRoomKeys;
	}

	public void setUserRoomKey(String newRoomKey) {
		userRoomKeys.add(newRoomKey);
		System.out.println(userRoomKeys.lastElement());
	}

	public int getUserRoomCount() {
		return userRoomKeys.size();
	}
	
	public String getRoomName(int index) {
		return userRoomKeys.get(index);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userID).append(" \t | ").append(userPW).append(" \t | ").append(userNickName);
		return builder.toString();
	}
}
