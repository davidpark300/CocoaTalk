package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// KeepProportionUIContainer의 인터페이스
interface KeepProportionUIContainerInterface {
	void setChildBounds(Component comp);
	void setChildProportion(Component comp, ProportionData proportionData);
	ProportionData getChildProportion(Component comp);
	void removeChildProportion(Component comp);
	void setRepaint(boolean on);
}

// KeepProportionUIContainer의 인터페이스를 상세 구현하고 KeepProportionUIContainer 객체의 공통 기능을 정의하는 클래스
class KeepProportionUIContainerBase implements KeepProportionUIContainerInterface {
	public HashMap<Component, ProportionData> proportionDatas = new HashMap<Component, ProportionData>();
	private Container container;
	private boolean doRepaint = false;
	
	public KeepProportionUIContainerBase(Container container)
	{
		this.container = container;
	}
	
	public ComponentAdapter KeepProportionUIContainerComponentAdapter = new ComponentAdapter() {
		@Override
		public void componentResized(ComponentEvent e) {
			Container target = (Container)e.getSource();

			for (Component comp : target.getComponents()) {
				((KeepProportionUIContainerInterface)target).setChildBounds(comp);
			}
			if (doRepaint == true) {
				target.revalidate();
				target.repaint();
			}
		}
	};
 	@Override
	public void setChildBounds(Component comp) {
 		// comp에 대한 proportionDatas 정보가 없다면 함수 종료
		if (proportionDatas.get(comp) == null) return;
		// proportionDatas.get(componentIndex)의 정보에 따라 Bounds 설정
		comp.setBounds(
			proportionDatas.get(comp).xProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()),
			proportionDatas.get(comp).yProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()),
			proportionDatas.get(comp).wProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()),
			proportionDatas.get(comp).hProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight())
		);
	}
 	@Override
	public void setChildProportion(Component comp, ProportionData proportionData) {
 		// 있는 객체만 변경
		proportionDatas.put(comp, proportionData);
	}
 	@Override
 	public ProportionData getChildProportion(Component comp) {
 		// 없는 객체라면 null 반환
 		return proportionDatas.get(comp);
 	}
 	@Override
	public void removeChildProportion(Component comp) {
 		// 비율 데이터만 제거
		proportionDatas.remove(comp);
		container.revalidate();
		container.repaint();
	}
 	@Override
	public void setRepaint(boolean on) {
 		doRepaint = on;
 	}
}

// KeepProportionJDialog
class KeepProportionJDialog extends JDialog implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJDialog(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildBounds(Component comp) {
		twin.setChildBounds(comp);
	}
 	@Override
	public void setChildProportion(Component comp, ProportionData proportionData) {
		twin.setChildProportion(comp, proportionData);
	}
 	@Override
 	public ProportionData getChildProportion(Component comp) {
 		return twin.getChildProportion(comp);
 	}
 	@Override
	public void removeChildProportion(Component comp) {
		twin.removeChildProportion(comp);
	}
 	@Override
	public void setRepaint(boolean on) {
 		twin.setRepaint(on);
 	}
}

// KeepProportionJFrame
class KeepProportionJFrame extends JFrame implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJFrame(String text) {
		super(text);
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildBounds(Component comp) {
		twin.setChildBounds(comp);
	}
 	@Override
	public void setChildProportion(Component comp, ProportionData proportionData) {
		twin.setChildProportion(comp, proportionData);
	}
 	@Override
 	public ProportionData getChildProportion(Component comp) {
 		return twin.getChildProportion(comp);
 	}
 	@Override
	public void removeChildProportion(Component comp) {
		twin.removeChildProportion(comp);
	}
 	@Override
	public void setRepaint(boolean on) {
 		twin.setRepaint(on);
 	}
}

// KeepProportionJPanel
class KeepProportionJPanel extends JPanel implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJPanel() {
		super();
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildBounds(Component comp) {
		twin.setChildBounds(comp);
	}
 	@Override
	public void setChildProportion(Component comp, ProportionData proportionData) {
		twin.setChildProportion(comp, proportionData);
	}
 	@Override
 	public ProportionData getChildProportion(Component comp) {
 		return twin.getChildProportion(comp);
 	}
 	@Override
	public void removeChildProportion(Component comp) {
		twin.removeChildProportion(comp);
	}
 	@Override
	public void setRepaint(boolean on) {
 		twin.setRepaint(on);
 	}
}

// KeepProportionUIContainerFactory
public class KeepProportionUIContainerFactory {
	public KeepProportionUIContainerFactory() {}
	public KeepProportionJDialog createJDialog(JFrame owner, String title, boolean modal) { return new KeepProportionJDialog(owner, title, modal); }
	public KeepProportionJFrame createJFrame(String text) { return new KeepProportionJFrame(text); }
	public KeepProportionJPanel createJPanel() { return new KeepProportionJPanel(); }
}