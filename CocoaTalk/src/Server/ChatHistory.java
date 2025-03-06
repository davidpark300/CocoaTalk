package Server;

public class ChatHistory {
	private int chattingHistoryIndex;
	private String chattingHistoryText;
	private String chattingHistorySenderNickName;
	private String chattingHistoryType;
	private String chattingHistorySenderID;

	public ChatHistory() {

	}

	public ChatHistory(int index, String text, String nick, String type, String id) {
		this.chattingHistoryIndex = index;
		this.chattingHistoryText = text;
		this.chattingHistorySenderNickName = nick;
		this.chattingHistoryType = type;
		this.chattingHistorySenderID = id;
	}

	public int getChatHistoryIndex() {
		return chattingHistoryIndex;
	}

	public String getChatHistoryText() {
		return chattingHistoryText;
	}

	public String getChatHistorySenderNickName() {
		return chattingHistorySenderNickName;
	}

	public String getChatHistoryType() {
		return chattingHistoryType;
	}

	public String getChatHistorySenderID() {
		return chattingHistorySenderID;
	}
}
