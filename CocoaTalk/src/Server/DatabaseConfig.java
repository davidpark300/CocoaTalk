package Server;

import java.io.FileInputStream;
import java.util.Properties;

public class DatabaseConfig {
	private String SQLID;
	private String SQLPW;

	public DatabaseConfig() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
			SQLID = prop.getProperty("db.user");
			SQLPW = prop.getProperty("db.password");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("MySql 로그인 실패, Config 확인 필요");
		}
	}

	public String getSQLID() {
		return SQLID;
	}

	public String getSQLPW() {
		return SQLPW;
	}
}
