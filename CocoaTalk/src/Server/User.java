package Server;

public class User {
	private String userID; // ID
	private String userPW; // PW
	private String userNickName; // NickName

	public User() {
		
	}

	public User(String userID, String userPW, String userNickName) {
		this.userID = userID;
		this.userPW = userPW;
		this.userNickName = userNickName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(userID).append(" \t | ").append(userPW).append(" \t | ").append(userNickName);
		return builder.toString();
	}
}
