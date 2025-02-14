package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ChatUIManager {
	// chatUI 객체
	public JPanel chatUI = MainUIManager.containerUIFactory.createJPanel();
	public JPanel GetUI() { return chatUI; }
	
	// 부모 프레임 매니저 객체
	MainUIManager owner;
	
	// 자식 컴포넌트
	private EnterUI eui = new EnterUI();
	private ChatList cl = null;// = new ChatList();
	
	// 생성자
	public ChatUIManager(MainUIManager owner) {
		this.owner = owner;
		chatUI.setLayout(null);
		chatUI.setSize(800, 600);
		chatUI.setVisible(true);
		setExtra();
		cl = new ChatList(owner.GetUI().getHeight());
		chatUI.add(cl);
	}
	
	// 추가 기능 설정
	private void setExtra() {
		((KeepProportionJPanel)chatUI).addKeepProportionUIComponent(eui, new ProportionData(
			true, ((x, y, w, h) ->  w / 4), 0,
			true, ((x, y, w, h) ->  h / 4), 0,
			true, ((x, y, w, h) ->  w / 4 * 2), 0,
			true, ((x, y, w, h) ->  h / 4), 0
		));
	}
}

class EnterUI extends JPanel {
	JButton selectFileButton = new JButton("file");
	JButton selectEmojiButton = new JButton("emoji");
	JButton drawPaintButton = new JButton("draw");
	JTextField messageTextField = new JTextField();
	JButton sendMessageButuon = new JButton("send");
	
	public EnterUI() {
		this.setLayout(null);
		
		this.add(selectFileButton);
		this.add(selectEmojiButton);
		this.add(drawPaintButton);
		this.add(messageTextField);
		this.add(sendMessageButuon);
		
		this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            	EnterUI sc = (EnterUI)e.getSource();
            	sc.placeChildren();
            	sc.repaint();
            }
        });
		
		this.setVisible(true);
	}
	
	public void placeChildren() {
		int x = this.getX();
		int y = this.getY();
		int w = this.getWidth();
		int h = this.getHeight();

		selectFileButton.setBounds(0, 0, w / 5, h / 3);
		selectEmojiButton.setBounds(0, h / 3, w / 5, h / 3);
		drawPaintButton.setBounds(0, h * 2 / 3, w / 5, h / 3);
		messageTextField.setBounds(w / 5, 0, w * 3 / 5, h);
		sendMessageButuon.setBounds(w * 4 / 5, 0, w / 5, h);
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