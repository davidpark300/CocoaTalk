package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import Server.User;
import Server.UserEdit;

public class LoginUI extends JPanel {
	private UserEdit userEdit = new UserEdit();
	private User user = null;
	private String id;
	private String pw;

	public LoginUI(MainUI mainUi) {
		this.setBackground(Color.YELLOW);
		JButton jb = new JButton("하하");
		add(jb);
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				sampleLogin();
				mainUi.switchUI("chatUI");
			}
		});
	}

	
	
	public void sampleLogin() {
		id = "id는" + (Math.random() * 10000);
		pw = "pw는" + (Math.random() * 10000);
		user = new User(id, pw, "킥킥킥");
		userEdit.addUser(user);
		ArrayList<User> list = userEdit.selectList();
		userEdit.printUserList(list);
	}
}
