package days;

import java.util.List;
import java.util.Optional;

import visuals.Day8Visual2;
import visuals.Visual;

public class Day8 extends Day {

	int WIDTH  = 25;
	int HEIGHT =  6;
	
	@Override
	protected int getChallengeNumber() {
		return 8;
	}
	
	@Override
	protected Object part1(List<String> input) {
		String code = input.get(0);
		int min0s = Integer.MAX_VALUE;
		int product = 0;
		for(int i = 0; i < code.length(); i += WIDTH * HEIGHT) {
			String layer = code.substring(i, i + WIDTH * HEIGHT);
			int zeroes = layer.replaceAll("[^0]", "").length();
			if(zeroes < min0s) {
				min0s = zeroes;
				product = layer.replaceAll("[^1]", "").length() * layer.replaceAll("[^2]", "").length();
			}
		}
		return product;
		
		// 1785 too high
	}

	@Override
	protected Object part2(List<String> input) {
		String code = input.get(0);
		Character[][] image = new Character[WIDTH][HEIGHT];
		for(int x = 0; x < WIDTH; x++) {
			for(int y = 0; y < HEIGHT; y++) {
				int offset = x + WIDTH * y;
				int i = 0;
				while(code.charAt(i + offset) == '2') {
					i += WIDTH * HEIGHT;
				}
				image[x][y] = code.charAt(i + offset);
			}
		}
		return image;
	}
	
	@Override
	public Optional<Visual> get2Visual() {
		return Optional.of(new Day8Visual2());
	}
}
