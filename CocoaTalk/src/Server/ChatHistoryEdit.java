package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class ChatHistoryEdit {
	private DatabaseConfig config = new DatabaseConfig();

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cocoatalkdb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String SQLID;
	private String SQLPW;

	private String roomName = null;
	private ChatHistory chatHistory = null;
	private Vector<ChatHistory> vChatHistory = new Vector<ChatHistory>();;

	public ChatHistoryEdit() {
		SQLID = config.getSQLID();
		SQLPW = config.getSQLPW();
	}

	public ChatHistoryEdit(String roomName) {
		SQLID = config.getSQLID();
		SQLPW = config.getSQLPW();
		this.roomName = new String(roomName);
		getSQLChatHistory(this.roomName);
	}

	public void getSQLChatHistory(String roomKey) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("JDBC 드라이버 불러오기 실패");
		}
		String sql = "select * from chatting_history_" + roomKey;
		// System.out.println(sql);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, SQLID, SQLPW);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int hIndex = rs.getInt("Chatting_History_Index");
				String hText = rs.getString("Chatting_History_Text");
				String hNick = rs.getString("Chatting_History_SenderNickName");
				String hType = rs.getString("Chatting_History_Type");
				String hID = rs.getString("Chatting_History_SenderID");
				chatHistory = new ChatHistory(hIndex, hText, hNick, hType, hID);
				//vChatHistory = new Vector<ChatHistory>();
				vChatHistory.add(chatHistory);
				//System.out.println("채팅방 내역 " + hIndex + "," + hText + "," + hNick + "," + hType + "," + hID);
				System.out.println(vChatHistory.getLast().getChatHistoryText());
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("채팅방 리스트 SQL 구문 에러");
		} finally {
			// 6. 자원 해제
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Vector<ChatHistory> getVectorChatHistory() {
		return vChatHistory;
	}
}
