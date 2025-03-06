package test;

import java.io.File;

import javax.swing.ImageIcon;

public class ImageTest {
    public static void main(String[] args) {
    	String path = "Image/CocoaImages.png";
        File file = new File(path);

        if (file.exists()) {
            System.out.println("파일을 찾았습니다! 절대 경로: " + file.getAbsolutePath());
        } else {
            System.out.println("파일을 찾을 수 없습니다! 시도한 경로: " + file.getAbsolutePath());
        }
    }
}