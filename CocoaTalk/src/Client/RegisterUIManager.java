package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.*;

public class RegisterUIManager {
	// 부모 로그인 매니저 객체
	MainUIManager owner = null;
	
	// 어댑터 객체
	private LoginAdapter loginListAdapter = null;
	
	// registerUI 객체
	private JPanel registerUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return registerUI; }

	
	// 자식 컴포넌트
	private JPanel registerPanel = MainUIManager.containerUIFactory.createJPanel();
	private JTextField idTextField = MainUIManager.componentUIFactory.createJTextField();
	private JTextField pwTextField = MainUIManager.componentUIFactory.createJTextField();
	private JButton registerButton = MainUIManager.componentUIFactory.createJButton("가입");
	
	// 데이터 멤버
	
	
	// 생성자
	public RegisterUIManager(MainUIManager owner) {
		this.owner = owner;
		loginListAdapter = new LoginAdapter(owner.loginUIManager);
		
		// 컴포넌트 추가
		registerUI.add(registerPanel);					// 컨텐트팬에 회원가입 패널 추가
		
		registerPanel.add(idTextField);					// 회원가입 패널에 아이디 텍스트 필드 추가
		registerPanel.add(pwTextField);					// 회원가입 패널에 비밀번호 텍스트 필드 추가
		registerPanel.add(registerButton);				// 회원가입 패널에 회원가입 버튼 추가
		
		setExtra();
		
		registerButton.addActionListener(e -> {
			loginListAdapter.register(idTextField.getText(), pwTextField.getText(), "hi");
        });
		
		registerUI.setVisible(true);
	}
	
	private void setExtra() {
		// registerUI - registerPanel
		((KeepProportionJPanel)registerUI).setChildProportion(registerPanel, new ProportionData(
				((x, y, w, h) -> w / 2 - 150),
				((x, y, w, h) -> h / 2 - 150),
				((x, y, w, h) -> 300),
				((x, y, w, h) -> 300)
			));
		((KeepProportionJPanel)registerPanel).setRepaint(false);
		// registerUI - registerPanel - idTextField
		((KeepProportionJPanel)registerPanel).setChildProportion(idTextField, new ProportionData(
				((x, y, w, h) -> w / 2 - 130),
				((x, y, w, h) -> h / 2),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - pwTextField
		((KeepProportionJPanel)registerPanel).setChildProportion(pwTextField, new ProportionData(
				((x, y, w, h) -> w / 2 - 130),
				((x, y, w, h) -> h / 2 + 30),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - registerButton
		((KeepProportionJPanel)registerPanel).setChildProportion(registerButton, new ProportionData(
				((x, y, w, h) -> w / 2 + 60),
				((x, y, w, h) -> h / 2),
				((x, y, w, h) -> 70),
				((x, y, w, h) -> 60)
			));
	}
}
