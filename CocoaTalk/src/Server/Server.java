package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private static final int PORT = 12345;
    private static Vector<ClientHandler> clients = new Vector<>(); // 동기화된 리스트

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 실행 중입니다...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("새로운 클라이언트 접속: " + socket);

                ClientHandler clientHandler = new ClientHandler(socket, clients);
                clients.add(clientHandler);
                clientHandler.start(); // 새로운 클라이언트 스레드 실행
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
