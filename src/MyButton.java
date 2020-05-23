import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class MyButton extends JButton {
	private Color txtColor = Color.white;
	private Color bgColor = Color.black;
	public MyButton(String text) {
		super(text);
		setBorderPainted(false);
		setOpaque(false);
	}
	public void setTxtColor(Color c) {
		txtColor = c;
	}
	public void setBgColor(Color c) {
		bgColor = c;
	}
	@Override
	public void paint(Graphics g) {
		int w = getWidth();
		int h = getHeight();
		
		g.setColor(bgColor);
		g.fillRoundRect(0, 0, w, h, 100, 100);
		
		g.setColor(txtColor);
		g.drawString(getText(), getWidth()/2-5, getHeight()/2+3);
	}
}
