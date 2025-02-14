package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainUIManager {
	// 응용프로그램에서 사용할 컨테이너 팩토리
	public static KeepProportionUIContainerFactory containerUIFactory = new KeepProportionUIContainerFactory();
	
	// 상수
	final int INITIAL_WIDTH = 800;
	final int INITIAL_HEIGHT = 600;
	
	// mainUI 객체
	public JFrame mainUI = containerUIFactory.createJFrame("코코아톡");
	public JFrame GetUI() { return mainUI; }
	
	// 카드 패널
	private JPanel cardPanel = new JPanel(new CardLayout());
	
	// 자식 컴포넌트
	private LoginUIManager loginUIManager = new LoginUIManager(this);
	private ChatUIManager chatUIManager = new ChatUIManager(this);
	
	// 생성자
	MainUIManager() {
		mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainUI.setSize(INITIAL_WIDTH, INITIAL_HEIGHT);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dim2 = mainUI.getSize();
		int x = (dim.width - dim2.width) / 2;
		int y = (dim.height - dim2.height) / 2;
		mainUI.setLocation(x, y);
		mainUI.setLayout(new CardLayout());
		
		SetCards();
		
		setExtra();
		
		mainUI.setVisible(true);
	}
	
	// 메서드
	private void SetCards() {
		cardPanel.add(loginUIManager.GetUI(), "loginUI");
		cardPanel.add(chatUIManager.GetUI(), "chatUI");
		mainUI.add(cardPanel);
	}
	public void SwitchUI(String name) {
		CardLayout cl = (CardLayout) cardPanel.getLayout();
		cl.show(cardPanel, name);
	}
	
	// 추가 기능 설정
	private void setExtra() {
		
	}
}