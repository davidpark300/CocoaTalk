package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.User;
import Server.UserEdit;

public class LoginUIManager {
	// loginUI 객체
	public JPanel loginUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel GetUI() { return loginUI; }
	
	// 부모 프레임 매니저 객체
	MainUIManager owner;
	
	// 자식 컴포넌트
	private JButton jb = new JButton("하하");
	
	// 생성자
	public LoginUIManager(MainUIManager owner) {
		this.owner = owner;
		
		JPanel a = MainUIManager.containerUIFactory.createJPanel();
		loginUI.setBackground(Color.YELLOW);
		
		jb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sampleLogin();
				owner.SwitchUI("chatUI");
			}
		});
		
		setExtra();
	}
	
	// 추가 기능 설정
	private void setExtra() {
		((KeepProportionJPanel)loginUI).addKeepProportionUIComponent(jb, new ProportionData(
			true, ((x, y, w, h) ->  w / 5), 0,
			true, ((x, y, w, h) ->  h / 5), 0,
			true, ((x, y, w, h) ->  w / 5), 0,
			true, ((x, y, w, h) ->  h / 5), 0
		));
	}

	private UserEdit userEdit = new UserEdit();
	private User user = null;
	private String id;
	private String pw;
	public void sampleLogin() {
		id = "id는" + (Math.random() * 10000);
		pw = "pw는" + (Math.random() * 10000);
		user = new User(id, pw, "킥킥킥");
		userEdit.addUser(user);
		ArrayList<User> list = userEdit.selectList();
		userEdit.printUserList(list);
	}
}
