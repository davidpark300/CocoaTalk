package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatListManager {
	// 어댑터 객체
	private ChatListAdapter chatListAdapter = new ChatListAdapter(this);
	
	// chatListUI 객체
	private JPanel chatListUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return chatListUI; }
	
	// 부모 채팅 매니저 객체
	ChatUIManager owner;
	
	// 자식 컴포넌트
	private JPanel userPanel = MainUIManager.containerUIFactory.createJPanel();
	private JLabel userName = MainUIManager.componentUIFactory.createJLabel(chatListAdapter.getUserName());
	private JScrollPane chatListScrollPane = MainUIManager.containerUIFactory.createJScrollPane();
	private LinkedList<JPanel> chatPanels = new LinkedList<JPanel>();
	
	// 데이터 멤버
	private int selectedIndex = 0;
	
	// 생성자
	public ChatListManager(ChatUIManager owner) {
		this.owner = owner;
		chatListUI.setLayout(null);
		chatListUI.setBackground(Color.YELLOW);
		
		// 컴포넌트 추가
		chatListUI.add(userPanel);					// 화면에 유저 패널 추가
		userPanel.add(userName);					// 유저 패널에 유저 이름 라벨 추가
		
		chatListUI.add(chatListScrollPane);			// 화면에 채팅 리스트 스크롤팬 추가
		updateChatPanels();							// 채팅 패널 연결 리스트 업데이트
		for (JPanel chatPanel : chatPanels) {		// 채팅 리스트 스크롤 패널에 채팅 패널들 추가
			//chatListScrollPane.add(chatPanel);
		}
		
		setExtra();
		
		chatListUI.setVisible(true);
	}
	
	private void updateChatPanels() {
		chatPanels = new LinkedList<JPanel>();
		for (int index = 0; index < chatListAdapter.size(); index++) {
			JPanel chatPanel = MainUIManager.containerUIFactory.createJPanel();
			JLabel chatRoomName = MainUIManager.componentUIFactory.createJLabel(chatListAdapter.getRoomName(index));
			chatPanel.add(chatRoomName);
			JButton charRoomStore = MainUIManager.componentUIFactory.createJButton("저장된 정보 보기");
			chatPanel.add(charRoomStore);
			chatPanels.add(chatPanel);
			
			updateChatPanelsExtra(chatPanel, chatRoomName, charRoomStore);
		}
	}
	
	// 추가 기능 설정
	private void setExtra() {
		// chatListUI - userPanel
		((KeepProportionJPanel)chatListUI).setChildProportion(userPanel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w),
				((x, y, w, h) -> 50)
			));
		// chatListUI - userPanel - userName
		((KeepProportionJPanel)userPanel).setChildProportion(userName, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w),
				((x, y, w, h) -> h)
			));
		// chatListUI - chatListScrollPane
		((KeepProportionJPanel)chatListUI).setChildProportion(chatListScrollPane, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 50),
				((x, y, w, h) -> w),
				((x, y, w, h) -> h - 50)
			));
		int index = 0;
		for (JPanel chatPanel : chatPanels) {
			// chatListUI - chatListPanel - chatPanel
			final int INDEX = index;
			((KeepProportionJScrollPane)chatListScrollPane).setChildProportion(chatPanel, new ProportionData(
					((x, y, w, h) -> 0),
					((x, y, w, h) -> 50 * INDEX),
					((x, y, w, h) -> w),
					((x, y, w, h) -> 50)
				));
			index++;
		}
		
	}
	
	private void updateChatPanelsExtra(JPanel chatPanel, JLabel chatRoomName, JButton chatRoomStore) {
		((KeepProportionJPanel)chatPanel).setRepaint(false);
		// chatListUI - chatListPanel - chatPanels - chatPanel
		((KeepProportionJPanel)chatPanel).setChildProportion(chatRoomName, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w / 2),
				((x, y, w, h) -> h)
			));
		// chatListUI - chatListPanel - chatPanels - chatRoomStore
		((KeepProportionJPanel)chatPanel).setChildProportion(chatRoomStore, new ProportionData(
				((x, y, w, h) -> w / 2),
				((x, y, w, h) -> h / 100),
				((x, y, w, h) -> w / 2),
				((x, y, w, h) -> h - h / 100)
			));
	}
}
