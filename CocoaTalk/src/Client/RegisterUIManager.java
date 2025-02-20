package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterUIManager {
	// 부모 로그인 매니저 객체
	LoginUIManager owner;
	
	// 어댑터 객체
	private LoginAdapter loginListAdapter = new LoginAdapter(owner);
	
	// registerUI 객체
	private JDialog registerUI = MainUIManager.containerUIFactory.createJDialog(null, "회원가입", true);
	public JDialog getUI() { return registerUI; }
	
	// 자식 컴포넌트
	
	
	// 데이터 멤버
	
	// 생성자
	public RegisterUIManager(LoginUIManager owner) {
		this.owner = owner;
		registerUI.setSize(400, 300);
		
		// 컴포넌트 추가
		
		
		setExtra();
		
		registerUI.setVisible(false);
	}
	
	private void setExtra() {
		
	}
}
