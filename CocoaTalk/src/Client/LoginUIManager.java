package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginUIManager {
	// 부모 프레임 매니저 객체
	MainUIManager owner;
	
	// 어댑터 객체
	public LoginAdapter loginAdapter = new LoginAdapter(this);
	
	// loginUI 객체
	public JPanel loginUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return loginUI; }
	
	// 자식 컴포넌트
	private JPanel loginPanel = MainUIManager.containerUIFactory.createJPanel();
	private ImageIcon cocoaImages = new ImageIcon("Image/CocoaImages.png");
	private JLabel logoLabel = MainUIManager.componentUIFactory.createJLabel(cocoaImages);
	private JLabel idLabel = MainUIManager.componentUIFactory.createJLabel("id");
	private JTextField idTextField = MainUIManager.componentUIFactory.createJTextField();
	private JLabel pwLabel = MainUIManager.componentUIFactory.createJLabel("pw");
	private JTextField pwTextField = MainUIManager.componentUIFactory.createJTextField();
	private JButton loginButton = MainUIManager.componentUIFactory.createJButton(cocoaImages);
	private JButton registerLabel = MainUIManager.componentUIFactory.createJButton("회원가입");
	private JButton findIDPWLabel = MainUIManager.componentUIFactory.createJButton("ID/PW 찾기");
	
	// 비밀번호 텍스트 필드 문서 리스너
	private DocumentListener passwordTextFieldDocumentListener = new DocumentListener() {
		@Override
        public void insertUpdate(DocumentEvent e) {
            int offset = e.getOffset();  // 변경이 발생한 위치
            int length = e.getLength();  // 변경된 문자 길이
            
            String textField = pwTextField.getText();
            password.insert(offset, textField.substring(offset, offset + length));
            char[] chars = new char[password.length()];
            Arrays.fill(chars, '*');
            SwingUtilities.invokeLater(() -> {
            	e.getDocument().removeDocumentListener(this);
        		pwTextField.setText(new String(chars));
            	e.getDocument().addDocumentListener(this);
        	});
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            int offset = e.getOffset();  // 변경이 발생한 위치
            int length = e.getLength();  // 변경된 문자 길이

            String textField = pwTextField.getText();
            password.delete(offset, offset + length);
            char[] chars = new char[password.length()];
            Arrays.fill(chars, '*');
            SwingUtilities.invokeLater(() -> {
            	e.getDocument().removeDocumentListener(this);
        		pwTextField.setText(new String(chars));
            	e.getDocument().addDocumentListener(this);
            });
        }

        @Override
        public void changedUpdate(DocumentEvent e) {}
	};
	
	// 데이터 멤버
	private StringBuffer password = new StringBuffer();
	
	// 생성자
	public LoginUIManager(MainUIManager owner) {
		this.owner = owner;
		
		loginUI.setLayout(null);
		loginUI.setBackground(new Color(0x00F5ECCE));

		// 컴포넌트 추가
		loginUI.add(loginPanel);					// 화면에 로그인 패널 추가
		
		loginPanel.setBackground(null);				// 로그인 패턴 배경 제거
		loginPanel.add(logoLabel);					// 로그인 패널에 로고 라벨 추가
		loginPanel.add(idLabel);					// 로그인 패널에 아이디 라벨 추가
		idLabel.setFont(new Font("Arial", 0, 15));	// 아이디 라벨 추가 폰트 설정
		loginPanel.add(idTextField);				// 로그인 패널에 아이디 텍스트 필드 추가
		loginPanel.add(pwLabel);					// 로그인 패널에 비밀번호 라벨 추가
		pwLabel.setFont(new Font("Arial", 0, 15));	// 비밀번호 라벨 추가 폰트 설정
		loginPanel.add(pwTextField);				// 로그인 패널에 비밀번호 텍스트 필드 추가
		
		loginPanel.add(loginButton);				// 로그인 패널에 로그인 버튼 추가
		loginButton.setRolloverIcon(cocoaImages);	// 로그인 버튼에 마우스를 올렸을 때 이미지 추가
		loginButton.setPressedIcon(cocoaImages);	// 로그인 버튼에 버튼을 눌렀을 때 이미지 추가
		loginButton.setBorderPainted(false);		// 로그인 버튼의 테두리 제거
		loginButton.setContentAreaFilled(false);	// 로그인 버튼의 배경 색상 제거
		
		loginPanel.add(registerLabel);				// 로그인 패널에 회원가입 버튼 추가
		registerLabel.setBorderPainted(false);		// 회원가입 버튼의 테두리 제거
		registerLabel.setBackground(new Color(0x00F3E2A9));	// 회원가입 버튼의 배경 색상 설정
		
		loginPanel.add(findIDPWLabel);				// 로그인 패널에 아이디/비밀번호 찾기 버튼 추가
		findIDPWLabel.setBorderPainted(false);		// 아이디/비밀번호 찾기 버튼의 테두리 제거
		findIDPWLabel.setBackground(new Color(0x00F3E2A9));	// 아이디/비밀번호 찾기 버튼의 배경 색상 설정
		
		// 로그인 버튼 리스너 - IDPW가 유효한지 확인 후 chatUI으로 넘어가기
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (loginAdapter.verifyIDPW(idTextField.getText(), password.toString()) == 1) {
					owner.switchUI("chatUI");
					owner.chatUIManager.chatAdapter.renew();
				}
				else JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
			}
		});
		// 아이디 텍스트 필드 액션 리스너 - IDPW가 유효한지 확인 후 chatUI으로 넘어가기
		idTextField.addActionListener(e -> {
			if (loginAdapter.verifyIDPW(idTextField.getText(), password.toString()) == 1) {
				owner.switchUI("chatUI");
				owner.chatUIManager.chatAdapter.renew();
			}
			else JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        });
		// 비밀번호 텍스트 필드 문서 리스너 - 문자 검열
		pwTextField.getDocument().addDocumentListener(passwordTextFieldDocumentListener);
		// 비밀번호 텍스트 필드 액션 리스너 - IDPW가 유효한지 확인 후 chatUI으로 넘어가기
		pwTextField.addActionListener(e -> {
			if (loginAdapter.verifyIDPW(idTextField.getText(), password.toString()) == 1) {
				owner.switchUI("chatUI");
				owner.chatUIManager.chatAdapter.renew();
			}
			else JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 틀렸습니다.", "Message", JOptionPane.ERROR_MESSAGE);
        });
		// 회원가입 버튼 리스너 - 회원가입 화면 출력
		registerLabel.addActionListener(e -> {
			owner.switchUI("registerUI");
		});
		
		setExtra();
		
		loginUI.setVisible(true);
	}
	
	// 추가 기능 설정
	private void setExtra() {
		// loginUI - loginPanel
		((KeepProportionJPanel)loginUI).setChildProportion(loginPanel, new ProportionData(
				((x, y, w, h) -> w / 2 - 150),
				((x, y, w, h) -> h / 2 - 150),
				((x, y, w, h) -> 300),
				((x, y, w, h) -> 300)
			));
		((KeepProportionJPanel)loginPanel).setRepaint(false);
		// loginUI - loginPanel - logoLabel
		((KeepProportionJPanel)loginPanel).setChildProportion(logoLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 300),
				((x, y, w, h) -> 151)
			));
		// loginUI - loginPanel - logoLabel - img
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
		// loginUI - loginPanel - idLabel
		((KeepProportionJPanel)loginPanel).setChildProportion(idLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// loginUI - loginPanel - idTextField
		((KeepProportionJPanel)loginPanel).setChildProportion(idTextField, new ProportionData(
				((x, y, w, h) -> 20),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// loginUI - loginPanel - pwLabel
		((KeepProportionJPanel)loginPanel).setChildProportion(pwLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 210),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// loginUI - loginPanel - pwTextField
		((KeepProportionJPanel)loginPanel).setChildProportion(pwTextField, new ProportionData(
				((x, y, w, h) -> 20),
				((x, y, w, h) -> 210),
				((x, y, w, h) -> 180),
				((x, y, w, h) -> 25)
			));
		// loginUI - loginPanel - loginButton
		((KeepProportionJPanel)loginPanel).setChildProportion(loginButton, new ProportionData(
				((x, y, w, h) -> 210),
				((x, y, w, h) -> 170),
				((x, y, w, h) -> 70),
				((x, y, w, h) -> 60)
			));
		// loginUI - loginPanel - loginButton - img
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
		// loginUI - loginPanel - loginButton - rolloverImg
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
		// loginUI - loginPanel - loginButton - pressedImg
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
		// loginUI - loginPanel - registerLabel
		((KeepProportionJPanel)loginPanel).setChildProportion(registerLabel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 250),
				((x, y, w, h) -> h / 2),
				((x, y, w, h) -> 15)
			));
		// loginUI - loginPanel - findIDPWLabel
		((KeepProportionJPanel)loginPanel).setChildProportion(findIDPWLabel, new ProportionData(
				((x, y, w, h) -> h / 2),
				((x, y, w, h) -> 250),
				((x, y, w, h) -> h / 2),
				((x, y, w, h) -> 15)
			));
	}
}
