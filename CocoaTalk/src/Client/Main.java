package Client;

import java.net.Socket;

public class Main {
	public static void main(String[] args) {
		new MainUIManager();
		try {
			Socket socket = new Socket("192.168.45.22", 3000);
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();	//예외가 발생했을 때, 오류 내용과 위치를 출력
		}
		
	}
}
