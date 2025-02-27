package Server;

public class SQLChatList {
	private String roomKey = null;
	private String roomName = null;
	private int roomMember;
	private String roomSummary = null;
	
	public SQLChatList() {
		
	}
	
	public SQLChatList(String roomKey, String roomName, int roomMember, String roomSummary) {
		this.roomKey = roomKey;
		this.roomName = roomName;
		this.roomMember = roomMember;
		this.roomSummary = roomSummary;
	}

	public void setRoomKey(String newRoomKey) {
		this.roomKey = newRoomKey;
	}

	public String getRoomKey() {
		return this.roomKey;
	}

	public void setRoomName(String newRoomName) {
		this.roomName = newRoomName;
	}

	public String getRoomName() {
		return this.roomName;
	}

	public void setRoomMember(int newRoomMember) {
		this.roomMember = newRoomMember;
	}

	public int getRoomMember() {
		return this.roomMember;
	}

	public void setRoomSummary(String newRoomSummary) {
		this.roomSummary = newRoomSummary;
	}

	public String getRoomSummary() {
		return this.roomSummary;
	}
}
