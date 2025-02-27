package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatUIManager {
	final public static int CHATLIST_WIDTH = 200;
	
	// 부모 프레임 매니저 객체
	MainUIManager owner;
	
	// 어댑터 객체
	public ChatAdapter chatAdapter = new ChatAdapter(this);
	
	// chatUI 객체
	public JPanel chatUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return chatUI; }
	
	// 자식 컴포넌트
	private ChatListManager chatListManger = new ChatListManager(this);
	private JPanel enterUI = MainUIManager.containerUIFactory.createJPanel();
	private JButton addFileButton = MainUIManager.componentUIFactory.createJButton("파일 추가");
	private JButton addEmojiButton = MainUIManager.componentUIFactory.createJButton("이모지 추가");
	private JButton addPaintButton = MainUIManager.componentUIFactory.createJButton("그림 추가");
	private JTextField enterTextField = MainUIManager.componentUIFactory.createJTextField();
	private JButton addSendButton = MainUIManager.componentUIFactory.createJButton("보내기");
	
	private ViewerUIManager viewerUI = new ViewerUIManager(this);
	
	// 생성자
	public ChatUIManager(MainUIManager owner) {
		this.owner = owner;
		chatUI.setLayout(null);
		chatUI.setBackground(new Color(0x00E0E0E0));
		
		// 컴포넌트 추가
		chatUI.add(chatListManger.getUI());		// 화면에 채팅 리스트 추가
		
		chatUI.add(enterUI);					// 화면에 입력 화면 추가
		enterUI.setBackground(new Color(0x0061380B));	// 입력 화면 배경 설정
		
		enterUI.add(addFileButton);				// 입력 화면에 파일 추가 버튼 추가
		addFileButton.setBorderPainted(false);	// 파일 추가 버튼의 테두리 제거
		addFileButton.setBackground(new Color(0x00FBF8EF));	// 파일 추가 버튼의 배경 색상 설정
		
		enterUI.add(addEmojiButton);			// 입력 화면에 이모지 추가 버튼 추가
		addEmojiButton.setBorderPainted(false);	// 이모지 추가 버튼의 테두리 제거
		addEmojiButton.setBackground(new Color(0x00FBF8EF));	// 이모지 추가 버튼의 배경 색상 설정
		
		enterUI.add(addPaintButton);			// 입력 화면에 그림 추가 버튼 추가
		addPaintButton.setBorderPainted(false);	// 그림 추가 버튼의 테두리 제거
		addPaintButton.setBackground(new Color(0x00FBF8EF));	// 그림 추가 버튼의 배경 색상 설정
		
		enterUI.add(enterTextField);			// 입력 화면에 입력 텍스트 필드 추가
		
		enterUI.add(addSendButton);				// 입력 화면에 보내기 버튼
		addSendButton.setBorderPainted(false);	// 보내기 버튼의 테두리 제거
		addSendButton.setBackground(new Color(0x00FBF8EF));	// 보내기 버튼의 배경 색상 설정

		chatUI.add(viewerUI.getUI());			// 화면에 뷰어 화면 추가
		
		setExtra();
		
		chatUI.setVisible(true);
	}
	
	// 추가 기능 설정
	private void setExtra() {
		// chatUI - chatListUI
		((KeepProportionJPanel)chatUI).setChildProportion(chatListManger.getUI(), new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> CHATLIST_WIDTH),
				((x, y, w, h) -> h)
			));
		
		// chatUI - enterUI
		((KeepProportionJPanel)chatUI).setChildProportion(enterUI, new ProportionData(
				((x, y, w, h) -> CHATLIST_WIDTH),
				((x, y, w, h) -> 4 * h / 5),
				((x, y, w, h) -> w - CHATLIST_WIDTH),
				((x, y, w, h) -> h / 5)
			));
		((KeepProportionJPanel)enterUI).setRepaint(false);
		
		// chatUI - enterUI - addFileButton
		((KeepProportionJPanel)enterUI).setChildProportion(addFileButton, new ProportionData(
				((x, y, w, h) -> 1 * w / 20),
				((x, y, w, h) -> 1 * h / 10),
				((x, y, w, h) -> 4 * w / 20),
				((x, y, w, h) -> 2 * h / 10)
			));

		// chatUI - enterUI - addEmojiButton
		((KeepProportionJPanel)enterUI).setChildProportion(addEmojiButton, new ProportionData(
				((x, y, w, h) -> 1 * w / 20),
				((x, y, w, h) -> 4 * h / 10),
				((x, y, w, h) -> 4 * w / 20),
				((x, y, w, h) -> 2 * h / 10)
			));

		// chatUI - enterUI - addPaintButton
		((KeepProportionJPanel)enterUI).setChildProportion(addPaintButton, new ProportionData(
				((x, y, w, h) -> 1 * w / 20),
				((x, y, w, h) -> 7 * h / 10),
				((x, y, w, h) -> 4 * w / 20),
				((x, y, w, h) -> 2 * h / 10)
			));

		// chatUI - enterUI - enterTextField
		((KeepProportionJPanel)enterUI).setChildProportion(enterTextField, new ProportionData(
				((x, y, w, h) -> 6 * w / 20),
				((x, y, w, h) -> 1 * h / 10),
				((x, y, w, h) -> 10 * w / 20),
				((x, y, w, h) -> 8 * h / 10)
			));

		// chatUI - enterUI - addSendButton
		((KeepProportionJPanel)enterUI).setChildProportion(addSendButton, new ProportionData(
				((x, y, w, h) -> 17 * w / 20),
				((x, y, w, h) -> 3 * h / 10),
				((x, y, w, h) -> 2 * w / 20),
				((x, y, w, h) -> 4 * h / 10)
			));

		// chatUI - viewerUI
		((KeepProportionJPanel)chatUI).setChildProportion(viewerUI.getUI(), new ProportionData(
				((x, y, w, h) -> 200),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w - CHATLIST_WIDTH),
				((x, y, w, h) -> 4 * h / 5)
			));
		((KeepProportionJPanel)viewerUI.getUI()).setRepaint(false);
	}
}