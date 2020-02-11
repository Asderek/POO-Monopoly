package monopoly.desenho;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Draw extends JPanel {

	public URL getURL () {
		return getClass().getClassLoader().getResource(s);
	};
	
	private Image img;
	private String s;
	
	public Draw() {
	}
	
	public Draw(String s) {
		this.s = s;
		init();
	}
	
	public void init() {
		loadImage();
		Dimension dm = new Dimension(img.getWidth(null),img.getHeight(null));
		setPreferredSize(dm); 
	}
	
	private void loadImage(){
		img = new ImageIcon(getURL()).getImage();  

	}

	private void doDrawing(Graphics g) {
		Graphics2D g3d = (Graphics2D) g;
		g3d.drawImage(img,0,0,null);

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public Image getImage () {
		return img;
	}
	
	public String getName () {
		return s;
	}
	

}
