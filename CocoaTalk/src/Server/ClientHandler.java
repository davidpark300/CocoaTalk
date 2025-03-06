package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler implements Runnable {
	private Socket socket;
	private ChatDatabase chatdatabase;
	private String roomID;
	private Vector<ClientHandler> clients;
	
	
	public ClientHandler(Socket socket, Vector<ClientHandler> clients) {
		this.socket = socket;
		chatdatabase = new ChatDatabase();
	}
	
	public void receiveMessage() {
		//데이터베이스에 저장되어있는 메시지를 불러옴
		chatdatabase.getChatHistory(roomID);
	}
	
	public void sendMessage(String message) {
		//클라이언트에서 보내기가 되면 DB에 저장이 되고 채팅방에 기록
	}

	@Override
	public void run() {   //클라이언트에서 실행되는 모든 작업이 여기에서 수행
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
			
			while(true) {
				String clientRequest = input.readLine();  // 클라이언트의 입력을 받음
	            if (clientRequest == null) {
	                break;  // 클라이언트가 연결을 종료하면 루프 탈출
	            }
	            
	            if(clientRequest.startsWith("SELECT_ROOM")) {	//클라이언트가 채팅방을 선택하면
					roomID = clientRequest.split(" ")[1];
					receiveMessage();
					output.println("채팅방 " + roomID + "에 입장했습니다.");
				}
				
	            else if(clientRequest.startsWith("SEND_MESSAGE")) {		//클라이언트가 메시지를 보내면
	            	String message = clientRequest.substring(13);
	                sendMessage(message);
	            }
				
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}
}