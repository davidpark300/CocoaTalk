package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserEdit {
	private DatabaseConfig config = new DatabaseConfig();

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cocoatalkdb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String SQLID;
	private String SQLPW;

	public UserEdit() {
		SQLID = config.getSQLID();
		SQLPW = config.getSQLPW();
	}

	public ArrayList<User> selectList() {
		// 1. JDBC Driver 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<User> list = null; // 결과 데이터를 담을 배열
		try {
			// 2. DB 서버 연결
			conn = DriverManager.getConnection(url, SQLID, SQLPW);

			// 3. SQL 실행 통로 형성
			stmt = conn.createStatement();

			// 4. SQL 실행 요청 및 겨로가 받기
			// CUD : excuteUpdate(sql) : int
			// R : excuteQuery(sql) : ResultSet
			String sql = "select * from logintb";
			rs = stmt.executeQuery(sql);

			// 5. SQL 결과 처리
			list = new ArrayList<>();
			while (rs.next()) {
				String userID = rs.getString("id"); // select index 번호는 1번부터
				// String userPW = rs.getString("pw");
				String userPW = rs.getString(2);
				String nickname = rs.getString("nickname");
				User dto = new User(userID, userPW, nickname);

				list.add(dto);
			}
		} catch (SQLException e) {
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
		return list;
	}

	public int loginUser(String userID, String userPW) {
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
				String nickName = rs.getNString("NickName");
				// JSON 파싱하는 구분 필요
				return 1;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("SQL 구문 에러");
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
		return 0;
	}

	public User selectUser(String userID) {
		// 1. JDBC Driver 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "select * from logintb where id ='" + userID + "'";
		try {
			Connection conn = DriverManager.getConnection(url, SQLID, SQLPW);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String ID = rs.getString(1);
				String PW = rs.getString("pw");
				String nickName = rs.getString(3);
				return new User(ID, PW, nickName);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // 찾은 유저가 없는 경우 null 반환
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
				System.out.println("유저 등록 완료");
			} else {
				System.out.println("유저 등록 실패");
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
					e.printStackTrace();
				}
			}
		}
	}

	public void printUserList(ArrayList<User> list) {
		for (User dto : list) {
			System.out.println(dto);
		}
	}
}
