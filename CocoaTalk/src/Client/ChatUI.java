package Client;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.*;

public class ChatUI extends JPanel {
    private Chatview chatview;
    private JScrollPane scrollPane;

    public ChatUI() {
        this.setLayout(null);
        this.setBackground(Color.BLACK);

        chatview = new Chatview();
        scrollPane = new JScrollPane(chatview);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                int scrollWidth = (int) (panelWidth * 2.0 / 3);
                int scrollHeight = (int) (panelHeight * 2.0 / 3);
                int xPosition = panelWidth / 3;
                int yPosition = 0;

                scrollPane.setBounds(xPosition, yPosition, scrollWidth, scrollHeight);
            }
        });
    }

}


class Chatview extends JPanel {
    private JPanel chatviewPanel;
    private JLabel roomName;

    public Chatview() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.YELLOW);

        // 채팅방 제목
        roomName = new JLabel("채팅방", SwingConstants.CENTER);
        roomName.setOpaque(true);
        roomName.setBackground(Color.GRAY);
        this.add(roomName, BorderLayout.NORTH);

        // 채팅 메시지 패널 (스크롤 패널 제거)
        chatviewPanel = new JPanel();
        chatviewPanel.setLayout(new BoxLayout(chatviewPanel, BoxLayout.Y_AXIS)); // 메시지 세로 정렬
        this.add(chatviewPanel, BorderLayout.CENTER); // 직접 추가 (JScrollPane X)
    }

    public void getMessage(String id, String msg) {
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        messagePanel.setBackground(Color.LIGHT_GRAY);
        messagePanel.setMaximumSize(new Dimension(580, 30));

        JLabel chatterID = new JLabel(id + ": ");
        JLabel chatText = new JLabel(msg);

        messagePanel.add(chatterID);
        messagePanel.add(chatText);

        chatviewPanel.add(messagePanel);
        chatviewPanel.revalidate();
        chatviewPanel.repaint();
    }
}

