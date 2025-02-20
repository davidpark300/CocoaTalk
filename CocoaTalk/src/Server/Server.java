package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
	private static Set<PrintWriter> clientWriters = new HashSet<>();	//연결된 클라이언트 저장(Set은 중복 불가, 순서가 없음)

	public static void main(String[] args) {
		int port = 2000;
		System.out.println("채팅 서버 시작...");
		
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			while (true) {		//연결이 될 때까지 무한 루프
				Socket clientSocket = serverSocket.accept();	//클라이언트가 연결 요청하면 소켓 생성
				System.out.println("클라이언트 연결됨: " + clientSocket.getInetAddress());
				ClientHandler clientThread = new ClientHandler(clientSocket);	//새로운 클라이언트 스레드 생성
				clientThread.start();	//새로운 클라이언트 스레드 시작
			}
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static class ClientHandler extends Thread {		//클라이얹트별 스레드
		private Socket socket;
		private PrintWriter out;
		private BufferedReader in;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {		//클라이언트 메시지 처리 메소드
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream(), true);
				
				synchronized (clientWriters) {	//동기화
					clientWriters.add(out);		//클라이언트 리스트에 추가
				}
				
				String message;
				while ((message = in.readLine()) != null) {		//메시지가 있다면
					System.out.println("클라이언트 : " + message);
					broadcast(message);		//모든 클라이언트에게 메시지 전달
				}
				
			} catch(IOException e) {
				e.printStackTrace();
				
			} finally {		//클라이언트 종료 시
				try {
					socket.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
				
				synchronized (clientWriters) {
					clientWriters.remove(out);
				}
			}
		}
		
		
		private void broadcast(String message) {
			synchronized(clientWriters) {		//여러 스레드가 동시에 접근하지 못하도록 함
				for(PrintWriter writer : clientWriters) {
					writer.println(message);
				}
			}
		}
	}
}
