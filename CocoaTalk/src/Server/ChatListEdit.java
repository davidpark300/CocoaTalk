package Server;

public class ChatListEdit {
	private DatabaseConfig config = new DatabaseConfig();
	private SQLChatList chatList = null;
	private User user = null;
	private UserEdit userEdit = null;

	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/cocoatalkdb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String SQLID;
	private String SQLPW;

	public ChatListEdit() {
		SQLID = config.getSQLID();
		SQLPW = config.getSQLPW();
	}

}
