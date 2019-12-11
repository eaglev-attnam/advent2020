package visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Day8Visual2 extends Visual {

	private static final int ZOOM = 20;
	
	@Override
	protected Image getImage(Object input) {
		if(!(input instanceof Character[][])) {
			throw new IllegalArgumentException();
		}
		Character[][] chars = (Character[][]) input;
		BufferedImage image = new BufferedImage(ZOOM*chars.length, ZOOM*chars[0].length, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		for(int x = 0; x < chars.length; x++) {
			for(int y = 0; y < chars[0].length; y++) {
				if(chars[x][y] == '0') {
					g.setColor(Color.BLACK);
				} else {
					g.setColor(Color.RED);
				}
				g.fillRect(ZOOM*x, ZOOM*y, ZOOM, ZOOM);
			}
		}
		g.finalize();
		return image;
	}

}
