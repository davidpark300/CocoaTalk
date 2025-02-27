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
	private LoginAdapter loginAdapter = null;
	
	// registerUI 객체
	private JPanel registerUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return registerUI; }

	
	// 자식 컴포넌트
	private JPanel registerPanel = MainUIManager.containerUIFactory.createJPanel();
	private ImageIcon cocoaImages = new ImageIcon("Image/CocoaImages.png");
	private JLabel logoLabel = MainUIManager.componentUIFactory.createJLabel(cocoaImages);
	private JLabel idLabel = MainUIManager.componentUIFactory.createJLabel("id");
	private JTextField idTextField = MainUIManager.componentUIFactory.createJTextField();
	private JLabel pwLabel = MainUIManager.componentUIFactory.createJLabel("pw");
	private JTextField pwTextField = MainUIManager.componentUIFactory.createJTextField();
	private JLabel nnLabel = MainUIManager.componentUIFactory.createJLabel("nick-name");
	private JTextField nnTextField = MainUIManager.componentUIFactory.createJTextField();
	private JButton registerButton = MainUIManager.componentUIFactory.createJButton(cocoaImages);
	private JButton loginButton = MainUIManager.componentUIFactory.createJButton(cocoaImages);
	
	// 데이터 멤버
	
	
	// 생성자
	public RegisterUIManager(MainUIManager owner) {
		this.owner = owner;
		loginAdapter = new LoginAdapter(owner.loginUIManager);
		
		registerUI.setBackground(new Color(0x00E3F6CE));	// 화면 배경 설정
		
		// 컴포넌트 추가
		registerUI.add(registerPanel);					// 화면에 회원가입 패널 추가
		registerPanel.setBackground(null);				// 회원가입 패널 배경 제거
		registerPanel.add(logoLabel);					// 회원가입 패널에 로고 라벨 추가
		registerPanel.add(idLabel);						// 회원가입 패널에 아이디 라벨 추가
		registerPanel.add(idTextField);					// 회원가입 패널에 아이디 텍스트 필드 추가
		registerPanel.add(pwLabel);						// 회원가입 패널에 비밀번호 라벨 추가
		registerPanel.add(pwTextField);					// 회원가입 패널에 비밀번호 텍스트 필드 추가
		registerPanel.add(nnLabel);						// 회원가입 패널에 닉네임 라벨 추가
		registerPanel.add(nnTextField);					// 회원가입 패널에 닉네임 텍스트 필드 추가
		
		registerPanel.add(registerButton);				// 회원가입 패널에 회원가입 버튼 추가
		registerButton.setRolloverIcon(cocoaImages);	// 회원가입 버튼에 마우스를 올렸을 때 이미지 추가
		registerButton.setPressedIcon(cocoaImages);		// 회원가입 버튼에 버튼을 눌렀을 때 이미지 추가
		registerButton.setBorderPainted(false);			// 회원가입 버튼의 테두리 제거
		registerButton.setContentAreaFilled(false);		// 회원가입 버튼의 배경 색상 제거
		
		registerPanel.add(loginButton);					// 회원가입 패널에 로그인 버튼 추가
		loginButton.setRolloverIcon(cocoaImages);		// 로그인 버튼에 마우스를 올렸을 때 이미지 추가
		loginButton.setPressedIcon(cocoaImages);		// 로그인 버튼에 버튼을 눌렀을 때 이미지 추가
		loginButton.setBorderPainted(false);			// 로그인 버튼의 테두리 제거
		loginButton.setContentAreaFilled(false);		// 로그인 버튼의 배경 색상 제거
		
		// 회원가입 버튼 리스너 - 회원가입
		registerButton.addActionListener(e -> {
			loginAdapter.register(idTextField.getText(), pwTextField.getText(), nnTextField.getText());
        });
		// 로그인 버튼 리스너 - 로그인 화면으로 돌아가기
		loginButton.addActionListener(e -> {
			owner.switchUI("loginUI");
        });
		
		setExtra();
		
		
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
		// registerUI - registerPanel - logoLabel
		((KeepProportionJPanel)registerPanel).setChildProportion(logoLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 300),
				((x, y, w, h) -> 151)
			));
		// registerUI - registerPanel - logoLabel - img
		((KeepProportionJLabel)logoLabel).setImgProportion(
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 300),
					((x, y, w, h) -> 151)
					),
				null);
		((KeepProportionJPanel)registerPanel).setRepaint(false);
		// registerUI - registerPanel - idLabel
		((KeepProportionJPanel)registerPanel).setChildProportion(idLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - idTextField
		((KeepProportionJPanel)registerPanel).setChildProportion(idTextField, new ProportionData(
				((x, y, w, h) -> 20),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - pwLabel
		((KeepProportionJPanel)registerPanel).setChildProportion(pwLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 200),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - pwTextField
		((KeepProportionJPanel)registerPanel).setChildProportion(pwTextField, new ProportionData(
				((x, y, w, h) -> 20),
				((x, y, w, h) -> 200),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - nnLabel
		((KeepProportionJPanel)registerPanel).setChildProportion(nnLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 230),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// registerUI - registerPanel - nnTextField
		((KeepProportionJPanel)registerPanel).setChildProportion(nnTextField, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 250),
				((x, y, w, h) -> 200),
				((x, y, w, h) -> 25)
			));
		
		// registerUI - registerPanel - registerButton
		((KeepProportionJPanel)registerPanel).setChildProportion(registerButton, new ProportionData(
				((x, y, w, h) -> 210),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 70),
				((x, y, w, h) -> 50)
			));
		// registerUI - registerPanel - registerButton - img
		((KeepProportionJButton)registerButton).setImgProportion(
				KeepProportionJButton.State.DEFAULT,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 510),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
		// registerUI - registerPanel - registerButton - rolloverImg
		((KeepProportionJButton)registerButton).setImgProportion(
				KeepProportionJButton.State.ROLLOVER,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 580),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
		// registerUI - registerPanel - registerButton - pressedImg
		((KeepProportionJButton)registerButton).setImgProportion(
				KeepProportionJButton.State.PRESSED,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 650),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
		
		// registerUI - registerPanel - loginButton
		((KeepProportionJPanel)registerPanel).setChildProportion(loginButton, new ProportionData(
				((x, y, w, h) -> 210),
				((x, y, w, h) -> 225),
				((x, y, w, h) -> 70),
				((x, y, w, h) -> 50)
			));
		// registerUI - registerPanel - loginButton - img
		((KeepProportionJButton)loginButton).setImgProportion(
				KeepProportionJButton.State.DEFAULT,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 300),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
		// registerUI - registerPanel - loginButton - rolloverImg
		((KeepProportionJButton)loginButton).setImgProportion(
				KeepProportionJButton.State.ROLLOVER,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 370),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
		// registerUI - registerPanel - loginButton - pressedImg
		((KeepProportionJButton)loginButton).setImgProportion(
				KeepProportionJButton.State.PRESSED,
				new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> w),
					((x, y, w, h) -> h)
				),
				new ProportionData(
					((x, y, w, h) -> 440),
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 70),
					((x, y, w, h) -> 60)
					),
				null);
	}
}
