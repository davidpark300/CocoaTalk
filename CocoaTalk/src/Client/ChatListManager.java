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
	
	// 부모 채팅 매니저 객체
	ChatUIManager owner;
	private User user = null;
	private UserEdit userEdit = new UserEdit();
	private SQLChatList sqlChatList = null;
	// 어댑터 객체
	private ChatListAdapter chatListAdapter = new ChatListAdapter(this);
	
	// chatListUI 객체
	private JPanel chatListUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return chatListUI; }
	
	// 자식 컴포넌트
	private JPanel userPanel = MainUIManager.containerUIFactory.createJPanel();
	private JLabel userName = MainUIManager.componentUIFactory.createJLabel(chatListAdapter.getUserName());
	
	private JPanel chatListPanel = new JPanel();
	private JScrollPane chatListScrollPane = null;
	
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
		// 채팅 리스트 스크롤팬 추가
		chatListScrollPane = new JScrollPane(chatListPanel);
		
		// 로그인 이후 실행시키기
		//setChatList();
		
		//chatListAdapter.
		
		chatListUI.add(chatListScrollPane);			// 화면에 채팅 리스트 스크롤팬 추가
		chatListScrollPane.setLayout(new ScrollPaneLayout()); 	// 채팅 리스트 스크롤팬 배치관리자 설정
		chatListScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 항상 세로 스크롤바 표시
		chatListScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤바는 숨김
		
		setExtra();
		
		chatListUI.setVisible(true);
	}
	
	public void setChatList(User user) {
		this.user = user;
		this.userName.setText(user.getUserNickName());
		chatListPanel.setLayout(new BoxLayout(chatListPanel, BoxLayout.Y_AXIS));
		chatListPanel.setBackground(new Color(0x003A2F0B));
		//chatListAdapter.setChatList();
		// 채팅 리스트 패널에 채팅 패널들 추가
		for (int index = 0; index < user.getUserRoomCount()/*chatListAdapter.size()*/; index++) {
			this.sqlChatList = userEdit.getUserChatList(user.getRoomName(index));
			JPanel chatPanel = new JPanel();
			chatPanel.setLayout(null);
			chatPanel.setPreferredSize(new Dimension(ChatUIManager.CHATLIST_WIDTH, CHAT_HEIGHT)); // 각 패널의 크기 지정
			chatPanel.setBackground(new Color(0x00FBF2EF));
			
			JLabel chatRoomName = new JLabel(sqlChatList.getRoomName());
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
			chatListPanel.add(Box.createRigidArea(new Dimension(0, 3 * CHAT_HEIGHT / 50))); // 간격 추가
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
