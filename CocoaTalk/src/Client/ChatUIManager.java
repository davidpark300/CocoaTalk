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
	private ChatAdapter chatAdapter = new ChatAdapter(this);
	
	// chatUI 객체
	public JPanel chatUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel getUI() { return chatUI; }
	
	// 자식 컴포넌트
	public ChatListManager chatListManger = new ChatListManager(this);
	private JPanel enterUI = MainUIManager.containerUIFactory.createJPanel();
	private JButton addFileButton = MainUIManager.componentUIFactory.createJButton("파일 추가");
	private JButton addEmojiButton = MainUIManager.componentUIFactory.createJButton("이모지 추가");
	private JButton addPaintButton = MainUIManager.componentUIFactory.createJButton("그림 추가");
	private JTextField enterTextField = MainUIManager.componentUIFactory.createJTextField();
	private JButton addSendButton = MainUIManager.componentUIFactory.createJButton("보내기");
	
	private JPanel viewerUI = MainUIManager.containerUIFactory.createJPanel();
	
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

		chatUI.add(viewerUI);					// 화면에 뷰어 화면 추가
		viewerUI.setBackground(new Color(0x00F5F6CE));	//  뷰어 화면 배경 설정
		
		setExtra();
		
		chatUI.setVisible(true);
	}
	
	public ChatListManager getChatListManager() {
		return chatListManger;
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
		((KeepProportionJPanel)chatUI).setChildProportion(viewerUI, new ProportionData(
				((x, y, w, h) -> 200),
				((x, y, w, h) -> 0),
				((x, y, w, h) -> w - CHATLIST_WIDTH),
				((x, y, w, h) -> h)
			));
		((KeepProportionJPanel)viewerUI).setRepaint(false);
	}
}

// Q. 로그인을 하고 채팅에 어떻게 참여할 것인가
// A. 1. 친구 초대 방식
//    2. 오픈채팅처럼 개설된 채팅방에 참여(카톡 오픈채팅, 디스코드 채널)
// Q. 
class ChatList extends JPanel {
	// private Vector<String> vList = new Vector<String>();
	private JScrollPane jsp = null; // = new JScrollPane();
	// private JList<String> list = new JList<String>();
	private Vector<JPanel> vPanel = new Vector<JPanel>();
	private JList<JPanel> panelList = new JList<JPanel>();
	private LineBorder br = new LineBorder(Color.RED, 3, true);
	private JButton preferenceBtn = null;
	private ImageIcon img = new ImageIcon("Image\\wheel.png");

	private JButton createChattingRoom = new JButton("채팅방 생성");

	public ChatList(int height) {
		this.setBackground(Color.BLUE);
		System.out.println(height);
		this.setSize(250, height);
		this.setLocation(0, 0);
		this.setLayout(null);
		// basicSetting();
		createPanels();
	}

	private void createPanels() {

		for (int i = 0; i < 20; i++) {
			addNewChattingRoom();
		}
		panelList.setVisibleRowCount(3);
		panelList.setListData(vPanel);
		panelList.setFixedCellHeight(50);
		panelList.setCellRenderer(new PanelListCellRenderer());
		panelList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				Object selectedValue = null;
				int selectedIndex;
				if(!e.getValueIsAdjusting()) {
					selectedValue = panelList.getSelectedValue();
					selectedIndex = panelList.getSelectedIndex();
					if(selectedValue !=null) {
						System.out.println(selectedValue);
						System.out.println(selectedIndex);
					}
				}
			}
		});
		panelList.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() ==2)
					System.out.println("하하하");
			}
		});
		panelList.addContainerListener(new ContainerListener() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				// TODO Auto-generated method stub
				System.out.println("채팅방 제거됨");
			}
			
			@Override
			public void componentAdded(ContainerEvent e) {
				// TODO Auto-generated method stub
				System.out.println("새로운 채팅방 추가");
			}
		});
		
		jsp = new JScrollPane(panelList);
		jsp.setSize(250, 460);
		jsp.setLocation(0, 100);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(jsp);

		preferenceBtn = new JButton(img);
		preferenceBtn.setSize(125, 100);
		preferenceBtn.setLocation(0, 0);
		this.add(preferenceBtn);
		
		createChattingRoom.setSize(125, 100);
		createChattingRoom.setLocation(125, 0);
		createChattingRoom.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				addNewChattingRoom();
			}
		});
		this.add(createChattingRoom);
	}

	private void addNewChattingRoom() {
		JPanel newChattingRoom = new JPanel();
		newChattingRoom.setBackground(randomColor());
		newChattingRoom.setSize(250, 50);
		newChattingRoom.setBorder(br);
		newChattingRoom.setLayout(new BorderLayout());
		newChattingRoom.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("눌린건가");
			}
			
		});
		JButton myBtn = new JButton("저장소");
		myBtn.setSize(150, 150);
		myBtn.setOpaque(true);
		newChattingRoom.add(myBtn, BorderLayout.EAST);
		vPanel.add(newChattingRoom);
		panelList.add(newChattingRoom);
	}

	private Color randomColor() {
		int r = (int) (Math.random() * 255);
		int g = (int) (Math.random() * 255);
		int b = (int) (Math.random() * 255);
		Color color = new Color(r, g, b);
		return color;
	}

	/*
	 * private void basicSetting() { for (int i = 0; i < 20; i++) { String str = new
	 * String("Test" + i); vList.add(str); } list.setVisibleRowCount(5);
	 * list.setListData(vList); list.setFixedCellHeight(50); list.setBorder(br);
	 * 
	 * jsp = new JScrollPane(list); jsp.setSize(250, 500); jsp.setLocation(0, 100);
	 * jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	 * this.add(jsp);
	 * 
	 * preferenceBtn = new JButton(img); preferenceBtn.setSize(125, 100);
	 * preferenceBtn.setLocation(0, 0); this.add(preferenceBtn);
	 * 
	 * createChattingRoom.setSize(125, 100); createChattingRoom.setLocation(125, 0);
	 * this.add(createChattingRoom); }
	 */
}

class PanelListCellRenderer implements ListCellRenderer<JPanel> {
	@Override
	public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel panel, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (isSelected) {
			panel.setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // 선택 시 테두리 변경
		} else {
			panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		}
		return panel;
	}
}