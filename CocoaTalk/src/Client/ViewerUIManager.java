package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewerUIManager {
	final public static int MESSAGE_HEIGHT = 50;
	
	// 부모 채팅 매니저 객체
	ChatUIManager owner;
	
	// viewerUI 객체
	private JPanel viewerUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return viewerUI; }
	
	// 자식 컴포넌트
	private JPanel viewerPanel = MainUIManager.containerUIFactory.createJPanel();
	private JScrollPane viewerScrollPane = null;
	
	// 데이터 멤버
	private String userNickName = new String();
	private String userID = new String();
	
	// 생성자
	public ViewerUIManager(ChatUIManager owner) {
		this.owner = owner;
		viewerUI.setLayout(null);
		
		viewerScrollPane = new JScrollPane(viewerPanel);		// 뷰어 패널을 가진 스크롤팬 생성
		viewerUI.add(viewerScrollPane);							// 화면에 채팅 리스트 스크롤팬 추가
		viewerScrollPane.setLayout(new ScrollPaneLayout()); 	// 채팅 리스트 스크롤팬 배치관리자 설정
		viewerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); // 항상 세로 스크롤바 표시
		viewerScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // 가로 스크롤바는 숨김

		// 스크롤 크기 재설정 될 때 마다 재설정하는 리스너
		viewerScrollPane.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				viewerPanel.removeAll();
				renew();
			}
		});
		
		setExtra();
		viewerUI.setVisible(true);
	}
	
	// 뷰어 패널 재설정
	public void renew() {
		this.owner.chatAdapter.renew();
		userNickName = owner.chatAdapter.getUserNickName();
		userID = owner.chatAdapter.getUserID();
		
		viewerPanel.setLayout(new BoxLayout(viewerPanel, BoxLayout.Y_AXIS));
		
		// 뷰어 패널에 각 채팅들 추가
		owner.chatAdapter.setMessageLists();
		
		viewerPanel.setLayout(new BoxLayout(viewerPanel, BoxLayout.Y_AXIS));
		viewerPanel.setBackground(Color.GRAY);
		// 채팅 리스트 패널에 채팅 패널들 추가
		for (int index = 0; index < owner.chatAdapter.messageSize(); index++) {
			JPanel messagePanel = MainUIManager.containerUIFactory.createJPanel();
			messagePanel.setLayout(null);
			messagePanel.setPreferredSize(new Dimension(viewerScrollPane.getWidth(), MESSAGE_HEIGHT)); // 각 패널의 크기 지정
			messagePanel.setBackground(Color.WHITE);
			
			JLabel senderNickNameLabel = new JLabel(owner.chatAdapter.getSenderNickName(index));
			String senderID = owner.chatAdapter.getSenderID(index);
			JLabel messageLabel = null;
			if (owner.chatAdapter.getType(index).equals(ChatAdapter.MessageType.text)) {
				messageLabel = new JLabel(owner.chatAdapter.getContent(index));
			}
			messagePanel.add(senderNickNameLabel);
			messagePanel.add(messageLabel);
			// 사용자의 ID와 같은 메시지는 오른쪽에 출력
			if (userID.equals(senderID)) {
				senderNickNameLabel.setHorizontalAlignment(JLabel.RIGHT);
				messageLabel.setHorizontalAlignment(JLabel.RIGHT);
			}
			
			renewViewerExtra(senderID, messagePanel, senderNickNameLabel, messageLabel);
			
			viewerPanel.add(messagePanel);
			viewerPanel.add(Box.createRigidArea(new Dimension(0, 2))); // 간격 추가
		}
		
		// 스크롤을 가장 아래로 이동
		SwingUtilities.invokeLater(() -> {
            JScrollBar verticalBar = viewerScrollPane.getVerticalScrollBar();
            verticalBar.setValue(verticalBar.getMaximum());
        });
	}
	
	private void renewViewerExtra(String senderID, JPanel messagePanel, JLabel senderLabel, JLabel messageLabel) {
		// viewerUI - viewerScrollPane - messagePanel - senderLabel
		((KeepProportionJPanel)messagePanel).setChildProportion(senderLabel, new ProportionData(
				((x, y, w, h) -> (userID.equals(senderID) ? w / 5 * 4 : 0)),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w / 5 - 20),
				((x, y, w, h) -> h)
			));
		// viewerUI - viewerScrollPane - messagePanel - messageLabel
		((KeepProportionJPanel)messagePanel).setChildProportion(messageLabel, new ProportionData(
				((x, y, w, h) -> w / 5),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 3 * w / 5),
				((x, y, w, h) -> h)
			));
	}
	
	// 추가 기능 설정
	private void setExtra() {
		// viewerUI - viewerScrollPane
		((KeepProportionJPanel)viewerUI).setChildProportion(viewerScrollPane, new ProportionData(
				((x, y, w, h) -> 0),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w),
				((x, y, w, h) -> h)
			));
	}
}