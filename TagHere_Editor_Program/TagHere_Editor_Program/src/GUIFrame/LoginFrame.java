package GUIFrame;

import java.awt.Color;

import javax.swing.JFrame;

import GUIComponent.SimpleJFrame;

/**
 * JFrame for Login
 * Have two textView
 * @author DongKyu
 */

public class LoginFrame extends SimpleJFrame{
	

	public LoginFrame(String frameName, int width, int height) {
		super(frameName, width, height);
		getContentPane().setBackground(new Color(230, 230, 230));
	}
	
	public static void main (String args[]) {
		LoginFrame frame = new LoginFrame("�α���", 300, 200);
	}

}