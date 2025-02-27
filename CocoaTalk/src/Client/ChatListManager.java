package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Server.User;
import Server.UserEdit;
import Server.SQLChatList;

public class ChatListManager {
	final public static int CHAT_HEIGHT = 50;
	final public static int CHAT_BLANK_HEIGHT = 2;
	
	// 부모 채팅 매니저 객체
	ChatUIManager owner;
	
	// chatListUI 객체
	private JPanel chatListUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return chatListUI; }
	
	// 자식 컴포넌트
	private JPanel userPanel = MainUIManager.containerUIFactory.createJPanel();
	private JLabel userName = null;
	
	private JPanel chatListPanel = MainUIManager.containerUIFactory.createJPanel();
	private JScrollPane chatListScrollPane = null;
	
	// 데이터 멤버
	private int selectedIndex = 0;
	
	// 생성자
	public ChatListManager(ChatUIManager owner) {
		this.owner = owner;
		chatListUI.setLayout(null);
		chatListUI.setBackground(Color.YELLOW);
		
		// 컴포넌트 초기화
		userName = MainUIManager.componentUIFactory.createJLabel(owner.chatAdapter.getUserNickName());
		
		// 컴포넌트 추가
		chatListUI.add(userPanel);					// 화면에 유저 패널 추가
		userPanel.add(userName);					// 유저 패널에 유저 이름 라벨 추가
		
		// 채팅 리스트 스크롤팬 추가
		chatListScrollPane = new JScrollPane(chatListPanel);
		
		chatListUI.add(chatListScrollPane);			// 화면에 채팅 리스트 스크롤팬 추가
		chatListScrollPane.setLayout(new ScrollPaneLayout()); 	// 채팅 리스트 스크롤팬 배치관리자 설정
		chatListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 항상 세로 스크롤바 표시
		chatListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤바는 숨김
		
		// 화면이 갱신 될 때 마다 renew 호출
		chatListUI.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				chatListPanel.removeAll();
				renew();
			}
		});
		
		setExtra();
		
		chatListUI.setVisible(true);
	}
	
	public void renew() {
		this.owner.chatAdapter.renew();
		this.userName.setText(owner.chatAdapter.getUserNickName());
		chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
		chatListPanel.setBackground(new Color(0x003A2F0B));
		// 채팅 리스트 패널에 채팅 패널들 추가
		for (int index = 0; index < owner.chatAdapter.getRoomSize(); index++) {
			JPanel chatPanel = new JPanel();
			chatPanel.setLayout(null);
			chatPanel.setPreferredSize(new Dimension(ChatUIManager.CHATLIST_WIDTH, CHAT_HEIGHT)); // 각 패널의 크기 지정
			chatPanel.setBackground(new Color(0x00FBF2EF));
			
			JLabel chatRoomName = new JLabel(owner.chatAdapter.getRoomName(index));
			chatRoomName.setBounds(0, 0, ChatUIManager.CHATLIST_WIDTH / 2, CHAT_HEIGHT);
			JButton charRoomStore = new JButton("기록");
			charRoomStore.setBounds(
					ChatUIManager.CHATLIST_WIDTH / 2,
					CHAT_HEIGHT / 10,
					ChatUIManager.CHATLIST_WIDTH / 2 - ChatUIManager.CHATLIST_WIDTH / 10,
					CHAT_HEIGHT - 8 * CHAT_HEIGHT / 50
				);
			
			chatPanel.add(chatRoomName);
			chatPanel.add(charRoomStore);
			
			chatListPanel.add(chatPanel);
			chatListPanel.add(Box.createRigidArea(new Dimension(0, CHAT_BLANK_HEIGHT * CHAT_HEIGHT / 50))); // 간격 추가
		}
		// 스크롤의 크기가 방 리스트보다 클 경우 채팅 리스트 패널에 빈 패널 추가
		if (chatListUI.getHeight() > (CHAT_HEIGHT + CHAT_BLANK_HEIGHT * CHAT_HEIGHT / 50) * (owner.chatAdapter.getRoomSize())) {
			JPanel blankPanel = new JPanel();
			blankPanel.setPreferredSize(new Dimension(ChatUIManager.CHATLIST_WIDTH, 
					chatListPanel.getHeight() - (CHAT_HEIGHT + CHAT_BLANK_HEIGHT * CHAT_HEIGHT / 50) * owner.chatAdapter.getRoomSize()));
			blankPanel.setBackground(new Color(0x00000000));
			chatListPanel.add(blankPanel);
		}
	}
	
	// 추가 기능 설정
	private void setExtra() {
		// chatListUI - userPanel
		((KeepProportionJPanel)chatListUI).setChildProportion(userPanel, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> ChatUIManager.CHATLIST_WIDTH),
				((x, y, w, h) -> CHAT_HEIGHT)
			));
		// chatListUI - userPanel - userName
		((KeepProportionJPanel)userPanel).setChildProportion(userName, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> ChatUIManager.CHATLIST_WIDTH),
				((x, y, w, h) -> CHAT_HEIGHT)
			));
		// chatListUI - chatListScrollPane
		((KeepProportionJPanel)chatListUI).setChildProportion(chatListScrollPane, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> CHAT_HEIGHT),
				((x, y, w, h) -> ChatUIManager.CHATLIST_WIDTH),
				((x, y, w, h) -> h - CHAT_HEIGHT)
			));
	}
}
