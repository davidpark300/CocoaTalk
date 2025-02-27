package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class UserEdit {
	private DatabaseConfig config = new DatabaseConfig();

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cocoatalkdb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String SQLID;
	private String SQLPW;
	private User user = null;
	private SQLChatList sqlChatList = null;

	public UserEdit() {
		SQLID = config.getSQLID();
		SQLPW = config.getSQLPW();
	}

	public User loginUser(String userID, String userPW) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("JDBC 드라이버 불러오기 실패");
		}
		String sql = "select * from logintb where id ='" + userID + "' and pw ='" + userPW + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, SQLID, SQLPW);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ID = rs.getString("ID");
				String PW = rs.getString("PW");
				String nickName = rs.getString("NickName");
				System.out.println(ID + "," + PW + "," + nickName);
				user = new User(ID, PW, nickName);
				// JSON 데이터타입
				String jsonKey = rs.getString("RoomKey");
				JSONParser parser = new JSONParser();
				JSONObject jsonObject = (JSONObject) parser.parse(jsonKey);
				JSONArray keyArray = (JSONArray) jsonObject.get("key");
				for (Object key : keyArray) {
					user.setUserRoomKey(key.toString());
					// System.out.println((String) key);
				}
				return user;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("로그인 SQL 구문 에러");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return null;
	}

	public void registerUser(User dto) {
		// 1. JDBC Driver 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DriverManager.getConnection(url, SQLID, SQLPW);
			// SQL 구문
			StringBuilder sql = new StringBuilder();
			sql.append("insert into logintb values(?,?,?)");
			pstmt = conn.prepareStatement(sql.toString()); // 3. SQL 실행 통로 형성
			pstmt.setString(1, dto.getUserID()); // setString, setInt 등 데이터 타입에 맞춰서 작성
			pstmt.setString(2, dto.getUserPW());
			pstmt.setString(3, dto.getUserNickName());

			int result = pstmt.executeUpdate();

			// 5. SQL 결과 처리
			if (result == 1) {
				System.out.println("회원가입 완료");
			} else {
				System.out.println("ID 중복으로 인한 회원가입 실패");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO: handle exception
					System.out.println("회원가입 SQL 구문 에러");
				}
			}
		}
	}

	public SQLChatList getUserChatList(String key) {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.out.println("JDBC 드라이버 불러오기 실패");
		}
		String sql = "select * from chatting_room_list where roomkey ='" + key + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, SQLID, SQLPW);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String roomKey = rs.getString("RoomKey");
				String roomName = rs.getString("RoomName");
				int roomMember = rs.getInt("RoomMember");
				String roomSummary = rs.getString("RoomSummary");
				System.out.println(roomKey + "," + roomName + "," + roomMember);
				sqlChatList = new SQLChatList(roomKey, roomName, roomMember, roomSummary);
				return sqlChatList;
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
		return null;
	}
}
