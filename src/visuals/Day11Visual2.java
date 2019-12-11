package visuals;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Objects;

import common.Coordinate;

public class Day11Visual2 extends Visual {

	private static final int ZOOM = 20;
	
	@SuppressWarnings("unchecked")
	@Override
	protected Image getImage(Object input) {
		if(!(input instanceof Map)) {
			throw new IllegalArgumentException();
		}
		Map<Coordinate, Integer> map = (Map<Coordinate, Integer>) input;
		int minX = 0;
		int maxX = 0;
		int minY = 0;
		int maxY = 0;
		for(Coordinate coord : map.keySet()) {
			if(coord.getX() > maxX) {
				maxX = coord.getX();
			}
			if(coord.getX() < minX) {
				minX = coord.getX();
			}
			if(coord.getY() > maxY) {
				maxY = coord.getY();
			}
			if(coord.getY() < minY) {
				minY = coord.getY();
			}
		}
		BufferedImage image = new BufferedImage(ZOOM * (maxX - minX + 1), ZOOM * (maxY - minY + 1), BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		for(int x = minX; x <= maxX; x++) {
			for(int y = minY; y <= maxY; y++) {
				if(Objects.equals(map.get(new Coordinate(x, y)), 1)) {
					g.setColor(Color.WHITE);
				} else {
					g.setColor(Color.BLACK);
				}
				g.fillRect(ZOOM * (x - minX), ZOOM * (y - minY), ZOOM, ZOOM);
			}
		}
		g.finalize();
		return image;
	}

}
