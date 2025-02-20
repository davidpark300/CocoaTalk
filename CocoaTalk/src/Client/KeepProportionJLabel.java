package Client;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class KeepProportionJLabel extends JLabel {
	// 데이터 멤버
	private boolean keepImg = false;
	private Image img = null;
	private ProportionData imgDestProportionData = new ProportionData();
	private ProportionData imgSrcProportionData = new ProportionData();
	private Component imgObserver = null;
	
	// 생성자
	public KeepProportionJLabel() {
		super();
		initialize();
	}
	public KeepProportionJLabel(Icon image) {
		super(image);
		initialize();
	}
	public KeepProportionJLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		initialize();
	}
	public KeepProportionJLabel(String text) {
		super(text);
		initialize();
	}
	public KeepProportionJLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		initialize();
	}
	public KeepProportionJLabel(String text, Icon image, int horizontalAlignment) {
		super(text, image, horizontalAlignment);
		initialize();
	}
	
	public void initialize() {
		if (this.getIcon() == null) {
			this.img = null;
		}
		else img = ((ImageIcon)(super.getIcon())).getImage();
		
		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component target = (Component)e.getSource();
				target.revalidate();
				target.repaint();
			}
		});
	}
	
	public void paintComponent(Graphics g) {
		// 이미지 출력, 설정된 경로가 없다면 생략
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
		Icon oldIcon = super.getIcon();
		super.setIcon(null);
		super.paintComponent(g);
		super.setIcon(oldIcon);
	}
	
	// 이미지 아이콘 설정
	@Override
	public void setIcon(Icon icon) {
		super.setIcon(icon);
		if (icon == null) {
			this.img = null;
			return;
		}
		this.img = ((ImageIcon)(super.getIcon())).getImage();
	}
	
	// 이미지 비율 설정
	public void setImgProportion(ProportionData destProportionData) {
		this.keepImg = true;
		this.imgDestProportionData = destProportionData;
	}
	public void setImgProportion(ProportionData destProportionData, ProportionData srcProportionData, Component imgObserver) {
		this.keepImg = true;
		this.imgDestProportionData.assign(destProportionData);
		this.imgSrcProportionData.assign(srcProportionData);
		this.imgObserver = imgObserver;
	}
	// 이미지 비율 설정 해제
	public void releaseIconProportion() {
		this.keepImg = false;
	}
}