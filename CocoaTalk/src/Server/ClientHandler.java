package Server;

import java.io.*;
import java.net.Socket;
import java.util.Vector;

public class ClientHandler extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private Vector<ClientHandler> clients;

    public ClientHandler(Socket socket, Vector<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("받은 메시지: " + message);
                broadcast(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    // 동기화된 메시지 브로드캐스트
    private synchronized void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.out.println(message);
        }
    }

    // 클라이언트 종료 시 정리
    private void closeConnection() {
        try {
            socket.close();
            synchronized (clients) {
                clients.remove(this);
            }
            System.out.println("클라이언트 연결 종료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
