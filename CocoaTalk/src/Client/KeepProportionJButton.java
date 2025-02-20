package Client;

import java.util.*;
import java.util.EnumMap;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeepProportionJButton extends JButton {
	// 데이터 멤버
	private boolean keepImg = false;
	
	static public enum State {
		DEFAULT,	// 기본
		ROLLOVER,	// 마우스를 올렸을 때
		PRESSED,	// 버튼을 눌렀을 때
		DISABLED,	// 버튼이 비활성화되었을 때
		SELECTED	// 버튼이 선택되었을 때 (토글 버튼용)
	};
	private Map<State, Image> imgs = new EnumMap<>(State.class);
	private Map<State, ProportionData> imgDestProportionDatas = new EnumMap<>(State.class);
	private Map<State, ProportionData> imgSrcProportionDatas = new EnumMap<>(State.class);
	
	private Component imgObserver = null;
	
	// 생성자
	public KeepProportionJButton() {
		super();
		initialize();
	}
	public KeepProportionJButton(Action a) {
		super(a);
		initialize();
	}
	public KeepProportionJButton(Icon icon) {
		super(icon);
		initialize();
	}
	public KeepProportionJButton(String text) {
		super(text);
		initialize();
	}
	public KeepProportionJButton(String text, Icon icon) {
		super(text, icon);
		initialize();
	}
	
	public void initialize() {
		// 기본 이미지 초기화
		if (this.getIcon() == null) {
			this.imgs.put(State.DEFAULT, null);
		}
		else this.imgs.put(State.DEFAULT, ((ImageIcon)(super.getIcon())).getImage());
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
		});
		
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
		});
	}
	
	public State getState() {
		if (this.getModel().isEnabled() == false) return State.DISABLED;
		if (this.getModel().isPressed() == true) return State.PRESSED;
		if (this.getModel().isRollover() == true) return State.ROLLOVER;
		if (this.getModel().isSelected() == true) return State.SELECTED;
		return State.DEFAULT;
	}
	
	public void paintComponent(Graphics g) {
		State state = getState();
		Image img = imgs.get(state);
		ProportionData imgDestProportionData = imgDestProportionDatas.get(state);
		ProportionData imgSrcProportionData = imgSrcProportionDatas.get(state);
		// 이미지가 없다면 기본 이미지로 대체
		if (img == null) {
			img = imgs.get(State.DEFAULT);
		}
		// 이미지 출력, (기본) 이미지가 없다면 생략
		if (img != null) {
			// destProportionData와 srcProportionData에 저장된 함수를 이용해 실제로 이미지를 출력할 범위 생성
			Rect destRect = new Rect();
			Rect srcRect = new Rect();
			// 이미지 비율이 설정되었는지 확인
			if (this.keepImg == true) {
				destRect.assign(
						imgDestProportionData.xProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgDestProportionData.yProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgDestProportionData.wProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgDestProportionData.hProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight())
					);
				srcRect.assign(
						imgSrcProportionData.xProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgSrcProportionData.yProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgSrcProportionData.wProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight()),
						imgSrcProportionData.hProportion.apply(this.getX(), this.getY(), this.getWidth(), this.getHeight())
					);
			}
			// 설정되어 있지 않다면 디폴트(원본 전체 복사 전체 출력)로 설정
			else {
				destRect.assign(0, 0, this.getWidth(), this.getHeight());
				srcRect.assign(0, 0, img.getWidth(imgObserver), img.getHeight(imgObserver));
			}
			
			g.drawImage(img,
					destRect.x, destRect.y,
					destRect.x + destRect.w, destRect.y + destRect.h,
					srcRect.x, srcRect.y,
					srcRect.x + srcRect.w, srcRect.y + srcRect.h,
					imgObserver);
		}
		
		// 텍스트 출력
		Icon oldIcon = null;
		if (state.equals(State.DEFAULT) == true) {
			oldIcon = this.getIcon();
			super.setIcon(null);
			super.paintComponent(g);
			super.setIcon(oldIcon);
		}
		if (state.equals(State.ROLLOVER) == true) {
			oldIcon = this.getRolloverIcon();
			super.setRolloverIcon(null);
			super.paintComponent(g);
			super.setRolloverIcon(oldIcon);
		}
		if (state.equals(State.PRESSED) == true) {
			oldIcon = this.getPressedIcon();
			super.setPressedIcon(null);
			super.paintComponent(g);
			super.setPressedIcon(oldIcon);
		}
		if (state.equals(State.DISABLED) == true) {
			oldIcon = this.getDisabledIcon();
			super.setDisabledIcon(null);
			super.paintComponent(g);
			super.setDisabledIcon(oldIcon);
		}
		if (state.equals(State.SELECTED) == true) {
			oldIcon = this.getSelectedIcon();
			super.setSelectedIcon(null);
			super.paintComponent(g);
			super.setSelectedIcon(oldIcon);
		}
	}
	
	// 기본 이미지 아이콘 설정
	@Override
	public void setIcon(Icon icon) {
		super.setIcon(icon);
		if (this.imgs != null) { // 생성 전에 호출 방지
			if (this.getIcon() == null) {
				this.imgs.put(State.DEFAULT, null);
			}
			else this.imgs.put(State.DEFAULT, ((ImageIcon)(super.getIcon())).getImage());
		}
	}

	// 마우스를 올렸을 때 이미지 아이콘 설정
	@Override
	public void setRolloverIcon(Icon icon) {
		super.setRolloverIcon(icon);
		if (this.imgs != null) { // 생성 전에 호출 방지
			if (this.getRolloverIcon() == null) {
				this.imgs.put(State.ROLLOVER, null);
			}
			else this.imgs.put(State.ROLLOVER, ((ImageIcon)(super.getRolloverIcon())).getImage());
		}
	}

	// 버튼을 눌렀을 때 이미지 아이콘 설정
	@Override
	public void setPressedIcon(Icon icon) {
		super.setPressedIcon(icon);
		if (this.imgs != null) { // 생성 전에 호출 방지
			if (this.getPressedIcon() == null) {
				this.imgs.put(State.PRESSED, null);
			}
			else this.imgs.put(State.PRESSED, ((ImageIcon)(super.getPressedIcon())).getImage());
		}
	}

	// 버튼이 비활성화되었을 때 이미지 아이콘 설정
	@Override
	public void setDisabledIcon(Icon icon) {
		super.setDisabledIcon(icon);
		if (this.imgs != null) { // 생성 전에 호출 방지
			if (this.getDisabledIcon() == null) {
				this.imgs.put(State.DISABLED, null);
			}
			else this.imgs.put(State.DISABLED, ((ImageIcon)(super.getDisabledIcon())).getImage());
		}
	}

	// 버튼이 선택되었을 때 이미지 아이콘 설정
	@Override
	public void setSelectedIcon(Icon icon) {
		super.setSelectedIcon(icon);
		if (this.imgs != null) { // 생성 전에 호출 방지
			if (this.getSelectedIcon() == null) {
				this.imgs.put(State.SELECTED, null);
			}
			else this.imgs.put(State.SELECTED, ((ImageIcon)(super.getSelectedIcon())).getImage());
		}
	}
	
	// 이미지 비율 설정
	public void setImgProportion(State state, ProportionData destProportionData) {
		this.keepImg = true;
		this.imgDestProportionDatas.put(state, destProportionData);
	}
	public void setImgProportion(State state, ProportionData destProportionData, ProportionData srcProportionData, Component imgObserver) {
		this.keepImg = true;
		this.imgDestProportionDatas.put(state, destProportionData);
		this.imgSrcProportionDatas.put(state, srcProportionData);
		this.imgObserver = imgObserver;
	}
	// 이미지 비율 설정 해제
	public void releaseIconProportion() {
		this.keepImg = false;
	}
}