package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class LoginUI extends JPanel {
	public LoginUI(MainUI mainUi) {
		this.setBackground(Color.YELLOW);
		JButton jb = new JButton("하하");
		add(jb);
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainUi.switchUI("chatUI");
			}
		});
	}
}
