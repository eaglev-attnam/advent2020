package visuals;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

public abstract class Visual {
	
	public void show() {
		JFrame frame = new JFrame();
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Canvas canvas = new ImageCanvas(getImage());
		frame.add(canvas);
		frame.setVisible(true);
		frame.pack();
	}
	
	protected abstract Image getImage();
	
	private class ImageCanvas extends Canvas {
		private static final long serialVersionUID = 1L;
		
		private Image image;
		
		private ImageCanvas(Image image) {
			super();
			this.image = image;
			this.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(image, 0, 0, null);
		}
	}
}
