package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

@FunctionalInterface
interface QuadIntegerFunction {
	Integer apply(Integer t1, Integer t2, Integer t3, Integer t4);
}

// 사각형 데이터를 저장하는 객체
class Rect {
	public int x;
	public int y;
	public int w;
	public int h;
	
	public Rect() {
		this.x = 0;
		this.y = 0;
		this.w = 0;
		this.h = 0;
	}
	public Rect(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public void assign(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
}

// 비율 데이터를 저장하는 객체
class ProportionData {
	public QuadIntegerFunction xProportion;
	public QuadIntegerFunction yProportion;
	public QuadIntegerFunction wProportion;
	public QuadIntegerFunction hProportion;
	
	public ProportionData() {}
	public ProportionData(
			QuadIntegerFunction xProportion,
			QuadIntegerFunction yProportion,
			QuadIntegerFunction wProportion,
			QuadIntegerFunction hProportion
	) {
		this.xProportion = xProportion;
		this.yProportion = yProportion;
		this.wProportion = wProportion;
		this.hProportion = hProportion;
	}
	
	public void assign(ProportionData other) {
		this.xProportion = other.xProportion;
		this.yProportion = other.yProportion;
		this.wProportion = other.wProportion;
		this.hProportion = other.hProportion;
	}
	public void setXProportion(QuadIntegerFunction xProportion) {
		this.xProportion = xProportion;
	}
	public void setYProportion(QuadIntegerFunction yProportion) {
		this.yProportion = yProportion;
	}
	public void setWProportion(QuadIntegerFunction wProportion) {
		this.wProportion = wProportion;
	}
	public void setHProportion(QuadIntegerFunction hProportion) {
		this.hProportion = hProportion;
	}
}

//KeepProportionJTextField
class KeepProportionJTextField extends JTextField {
	public KeepProportionJTextField() {
		super();
		this.initialize();
	}
	public KeepProportionJTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		this.initialize();
	}
	public KeepProportionJTextField(int columns) {
		super(columns);
		this.initialize();
	}
	public KeepProportionJTextField(String text) {
		super(text);
		this.initialize();
	}
	public KeepProportionJTextField(String text, int columns) {
		super(text, columns);
		this.initialize();
	}
	
	public void initialize() {}
}

public class KeepProportionUIComponentFactory {
	public KeepProportionUIContainerFactory createContainerFactory() { return new KeepProportionUIContainerFactory(); }
	
	public KeepProportionJLabel createJLabel() { return new KeepProportionJLabel(); }
	public KeepProportionJLabel createJLabel(Icon image) { return new KeepProportionJLabel(image); }
	public KeepProportionJLabel createJLabel(Icon image, int horizontalAlignment) { return new KeepProportionJLabel(image, horizontalAlignment); }
	public KeepProportionJLabel createJLabel(String text) { return new KeepProportionJLabel(text); }
	public KeepProportionJLabel createJLabel(String text, int horizontalAlignment) { return new KeepProportionJLabel(text, horizontalAlignment); }
	public KeepProportionJLabel createJLabel(String text, Icon image, int horizontalAlignment) { return new KeepProportionJLabel(text, image, horizontalAlignment); }
	
	public KeepProportionJTextField createJTextField() { return new KeepProportionJTextField(); }
	public KeepProportionJTextField createJTextField(Document doc, String text, int columns) { return new KeepProportionJTextField(doc, text, columns); }
	public KeepProportionJTextField createJTextField(int columns) { return new KeepProportionJTextField(columns); }
	public KeepProportionJTextField createJTextField(String text) { return new KeepProportionJTextField(text); }
	public KeepProportionJTextField createJTextField(String text, int columns) { return new KeepProportionJTextField(text, columns); }
	
	public KeepProportionJButton createJButton() { return new KeepProportionJButton(); }
	public KeepProportionJButton createJButton(Action a) { return new KeepProportionJButton(a); }
	public KeepProportionJButton createJButton(Icon icon) { return new KeepProportionJButton(icon); }
	public KeepProportionJButton createJButton(String text) { return new KeepProportionJButton(text); }
	public KeepProportionJButton createJButton(String text, Icon icon) { return new KeepProportionJButton(text, icon); }
}
