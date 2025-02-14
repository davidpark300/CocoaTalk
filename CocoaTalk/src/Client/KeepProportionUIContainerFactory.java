package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//concrete product - KeepProportionUIContainer
@FunctionalInterface
interface QuadIntegerFunction {
	Integer apply(Integer t1, Integer t2, Integer t3, Integer t4);
}

// 비율 데이터를 저장하는 객체
class ProportionData {
	
	public boolean keepProportionX;
	public QuadIntegerFunction xProportion;
	public int x;
	
	public boolean keepProportionY;
	public QuadIntegerFunction yProportion;
	public int y;
	
	public boolean keepProportionW;
	public QuadIntegerFunction wProportion;
	public int w;
	
	public boolean keepProportionH;
	public QuadIntegerFunction hProportion;
	public int h;
	
	public ProportionData(
		boolean keepProportionX, QuadIntegerFunction xProportion, int x,
		boolean keepProportionY, QuadIntegerFunction yProportion, int y,
		boolean keepProportionW, QuadIntegerFunction wProportion, int w,
		boolean keepProportionH, QuadIntegerFunction hProportion, int h
	) {
		this.keepProportionX = keepProportionX;
		this.xProportion = xProportion;
		this.x = x;
		this.keepProportionY = keepProportionY;
		this.yProportion = yProportion;
		this.y = y;
		this.keepProportionW = keepProportionW;
		this.wProportion = wProportion;
		this.w = w;
		this.keepProportionH = keepProportionH;
		this.hProportion = hProportion;
		this.h = h;
	}
}

// KeepProportionUIContainer의 인터페이스
interface KeepProportionUIContainerInterface {
	void setChildProportion(Component comp);
	void addKeepProportionUIComponent(Component comp, ProportionData propertionData);
	void removeKeepProportionUIComponent(Component comp);
}

// KeepProportionUIContainer의 인터페이스를 상세 구현하고 KeepProportionUIContainer 객체의 공통 기능을 정의하는 클래스
class KeepProportionUIContainerBase implements KeepProportionUIContainerInterface {
	private Vector<ProportionData> _proportionDatas = new Vector<ProportionData>();
	private Container container;
	
	public KeepProportionUIContainerBase(Container container)
	{
		this.container = container;
	}
	
	public ComponentAdapter KeepProportionUIContainerComponentAdapter = new ComponentAdapter() {
     @Override
     public void componentResized(ComponentEvent e) {
     	Container target = (Container)e.getSource();
     	
     	for (Component comp : target.getComponents()) {
     		((KeepProportionUIContainerInterface)target).setChildProportion(comp);
     	}
     	
     	target.revalidate();
     	target.repaint();
     }
 };
 	@Override
	public void setChildProportion(Component comp) {
		if (_proportionDatas.size() == 0) return;
		comp.setBounds(
			(_proportionDatas.lastElement().keepProportionX) ? 
			_proportionDatas.lastElement().xProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()) :
			_proportionDatas.lastElement().x,
			
			(_proportionDatas.lastElement().keepProportionY) ? 
			_proportionDatas.lastElement().yProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()) :
			_proportionDatas.lastElement().y,
			
			(_proportionDatas.lastElement().keepProportionW) ? 
			_proportionDatas.lastElement().wProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()) :
			_proportionDatas.lastElement().w,
			
			(_proportionDatas.lastElement().keepProportionH) ? 
			_proportionDatas.lastElement().hProportion.apply(container.getX(), container.getY(), container.getWidth(), container.getHeight()) :
			_proportionDatas.lastElement().h
		);
	}
 	@Override
	public void addKeepProportionUIComponent(Component comp, ProportionData propertionData) {
		container.add(comp);
		_proportionDatas.add(propertionData);
		setChildProportion(comp);
		container.revalidate();
		container.repaint();
	}
 	@Override
	public void removeKeepProportionUIComponent(Component comp) {
		boolean miss = false;
		int removeComponentIndex;
		for (removeComponentIndex = 0; removeComponentIndex < container.getComponentCount(); ++removeComponentIndex) {
			if (container.getComponents()[removeComponentIndex] == comp) {
				break;
			}
			if (removeComponentIndex == container.getComponentCount() - 1) miss = true;
		}
		if (miss == true) return;
		
		_proportionDatas.remove(removeComponentIndex);
		container.remove(comp);
		container.revalidate();
		container.repaint();
	}
}

//KeepProportionJDialog
class KeepProportionJDialog extends JDialog implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJDialog(JFrame owner, String title, boolean modal) {
		super(owner, title, modal);
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildProportion(Component comp) {
		twin.setChildProportion(comp);
	}
 	@Override
	public void addKeepProportionUIComponent(Component comp, ProportionData propertionData) {
		twin.addKeepProportionUIComponent(comp, propertionData);
	}
 	@Override
	public void removeKeepProportionUIComponent(Component comp) {
		twin.removeKeepProportionUIComponent(comp);
	}
}

//KeepProportionJFrame
class KeepProportionJFrame extends JFrame implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJFrame(String text) {
		super(text);
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildProportion(Component comp) {
		twin.setChildProportion(comp);
	}
 	@Override
	public void addKeepProportionUIComponent(Component comp, ProportionData propertionData) {
		twin.addKeepProportionUIComponent(comp, propertionData);
	}
 	@Override
	public void removeKeepProportionUIComponent(Component comp) {
		twin.removeKeepProportionUIComponent(comp);
	}
}

//KeepProportionJPanel
class KeepProportionJPanel extends JPanel implements KeepProportionUIContainerInterface {
	private KeepProportionUIContainerBase twin = new KeepProportionUIContainerBase(this);
	
	public KeepProportionJPanel() {
		super();
		this.setLayout(null);
		this.addComponentListener(twin.KeepProportionUIContainerComponentAdapter);
	}

 	@Override
	public void setChildProportion(Component comp) {
		twin.setChildProportion(comp);
	}
 	@Override
	public void addKeepProportionUIComponent(Component comp, ProportionData propertionData) {
		twin.addKeepProportionUIComponent(comp, propertionData);
	}
 	@Override
	public void removeKeepProportionUIComponent(Component comp) {
		twin.removeKeepProportionUIComponent(comp);
	}
}

// KeepProportionUIContainerFactory
public class KeepProportionUIContainerFactory {
	public KeepProportionUIContainerFactory() {}
	public KeepProportionJDialog KeepProportionJDialog(JFrame owner, String title, boolean modal) { return new KeepProportionJDialog(owner, title, modal); }
	public KeepProportionJFrame createJFrame(String text) { return new KeepProportionJFrame(text); }
	public KeepProportionJPanel createJPanel() { return new KeepProportionJPanel(); }
}