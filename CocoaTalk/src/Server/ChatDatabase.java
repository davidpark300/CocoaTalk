package Server;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ChatDatabase {
	private Map<String, Set<String>> chatHistory;
	private Set<String> chatRooms;
	
	public ChatDatabase() {
		chatHistory = new HashMap<>();
		chatRooms = new HashSet<>();
	}
	
	public Set<String> getChatHistory(String roomID) {
		return chatHistory.getOrDefault(roomID, new HashSet<>());
	}
	
	public Set<String> getAllChatRooms() {
		return chatRooms;
	}
	
	public void saveMessage(String roomID, String sender, String message) {
		String formattedMessage = sender + ": " + message;
	    Set<String> history = chatHistory.get(roomID);
	    if (history == null) {
	        history = new HashSet<>();
	        chatHistory.put(roomID, history);
	    }
	    history.add(formattedMessage);
	}
	
//	public void createChatRoom(String roomName) {
//		chatRooms.add(roomName);
//	    if (!chatHistory.containsKey(roomName)) {
//	        chatHistory.put(roomName, new HashSet<>());
//	    }
//	}
	
}
