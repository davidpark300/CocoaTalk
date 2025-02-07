package Client;

import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChatUI extends JPanel {
	private EnterUI eui = new EnterUI();
	public ChatUI() {
		this.setLayout(null);
		this.setSize(800, 600);
		this.setVisible(true);
		
		eui.setBounds(250, 450, 520, 100);
		this.add(eui);
	}
}

class EnterUI extends JPanel {
	JButton selectFileButton = new JButton("file");
	JButton selectEmojiButton = new JButton("emoji");
	JButton drawPaintButton = new JButton("draw");
	JTextField messageTextField = new JTextField();
	JButton sendMessageButuon = new JButton("send");
	
	public EnterUI() {
		this.setLayout(null);
		
		this.add(selectFileButton);
		this.add(selectEmojiButton);
		this.add(drawPaintButton);
		this.add(messageTextField);
		this.add(sendMessageButuon);
		
		this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	EnterUI sc = (EnterUI)e.getSource();
            	sc.placeChildren();
            	sc.repaint();
            }
        });
		
		this.setVisible(true);
	}
	
	public void placeChildren() {
		int x = this.getX();
		int y = this.getY();
		int w = this.getWidth();
		int h = this.getHeight();

		selectFileButton.setBounds(0, 0, w / 5, h / 3);
		selectEmojiButton.setBounds(0, h / 3, w / 5, h / 3);
		drawPaintButton.setBounds(0, h * 2 / 3, w / 5, h / 3);
		messageTextField.setBounds(w / 5, 0, w * 3 / 5, h);
		sendMessageButuon.setBounds(w * 4 / 5, 0, w / 5, h);
	}
}