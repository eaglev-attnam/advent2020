package visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Visual20Part1 extends Visual {

	private char[][] sea;
	private static final int PIXEL_SIZE = 8;
	
	public Visual20Part1(char[][] sea) {
		this.sea = sea;
	}
	
	@Override
	protected Image getImage() {
		BufferedImage image = new BufferedImage(PIXEL_SIZE * sea.length, PIXEL_SIZE * sea.length, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		for(int x = 0; x < sea.length; x++) {
			for(int y = 0; y < sea.length; y++) {
				if(sea[x][y] == '#') {
					if(x % 10 == 0 || x % 10 == 9 || y % 10 == 0 || y % 10 == 9) {
						g.setColor(Color.GREEN);
						if(x % 10 == 0 && x != 0 && sea[x-1][y] != '#') {
							g.setColor(Color.RED);
						} else if(x % 10 == 9 && x != sea.length - 1 && sea[x+1][y] != '#') {
							g.setColor(Color.RED);
						} else if(y % 10 == 0 && y != 0 && sea[x][y-1] != '#') {
							g.setColor(Color.RED);
						} else if(y % 10 == 9 && y != sea.length - 1 && sea[x][y+1] != '#') {
							g.setColor(Color.RED);
						}
					} else {
						g.setColor(Color.BLUE);
					}
					g.fillRect(PIXEL_SIZE*x, PIXEL_SIZE*y, PIXEL_SIZE, PIXEL_SIZE);
				}
			}
		}
		return image;
	}
}
