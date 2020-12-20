package visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Visual20Part2 extends Visual {

	private char[][] sea;
	private static final int PIXEL_SIZE = 10;
	
	public Visual20Part2(char[][] sea) {
		this.sea = sea;
	}
	
	@Override
	protected Image getImage() {
		BufferedImage image = new BufferedImage(PIXEL_SIZE * sea.length, PIXEL_SIZE * sea.length, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		for(int x = 0; x < sea.length; x++) {
			for(int y = 0; y < sea.length; y++) {
				if(sea[sea.length - y - 1][sea.length - x - 1] == '#') {
					g.setColor(Color.BLUE);
					g.fillRect(PIXEL_SIZE*x, PIXEL_SIZE*y, PIXEL_SIZE, PIXEL_SIZE);
				} else if(sea[sea.length - y - 1][sea.length - x - 1] == 'O') {
					g.setColor(Color.RED);
					g.fillRect(PIXEL_SIZE*x, PIXEL_SIZE*y, PIXEL_SIZE, PIXEL_SIZE);
				}
			}
		}
		return image;
	}
}
