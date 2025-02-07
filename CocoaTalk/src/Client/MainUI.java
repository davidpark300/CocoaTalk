package Client;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame {

	private JPanel cardPanel = new JPanel(new CardLayout());
	private LoginUI loginUI = new LoginUI(this);
	private ChatUI chatUI = null;// = new ChatUI();

	public MainUI() {
		this.setTitle("코코아톡");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim2 = getSize();
		int x = (dim.width - dim2.width) / 2;
		int y = (dim.height - dim2.height) / 2;
		this.setLocation(x, y);
		chatUI = new ChatUI(this.getHeight() - 40);
		this.setLayout(new CardLayout());

		setCardPanel();

		this.setVisible(true);
	}

	private void setCardPanel() {
		cardPanel.add(loginUI, "loginUI");
		cardPanel.add(chatUI, "chatUI");
		MainUI.this.add(cardPanel);
	}

	public void switchUI(String str) {
		CardLayout cl = (CardLayout) cardPanel.getLayout();
		cl.show(cardPanel, str);
	}

}
