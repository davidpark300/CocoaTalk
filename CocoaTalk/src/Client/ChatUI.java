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
        scrollPane.setBounds(250, 0, 520, 100);
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
    private String chatName = "채팅방";
    

    public Chatview() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.YELLOW);

        roomName = new JLabel(chatName);
        roomName.setOpaque(true);
        roomName.setBackground(Color.GRAY);
        this.add(roomName, BorderLayout.NORTH);

        chatviewPanel = new JPanel();
        chatviewPanel.setLayout(new BoxLayout(chatviewPanel, BoxLayout.Y_AXIS));
        this.add(chatviewPanel, BorderLayout.CENTER);
        
        addText("홍성환", "안녕하세요.");
    }
    
    public void addText(String id, String text) {
    	JLabel name = new JLabel(id);
    	JLabel msg = new JLabel(text);
    	
    	
    	chatviewPanel.add(name);
    	chatviewPanel.add(msg);
    }

}

