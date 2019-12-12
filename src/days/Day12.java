package days;

import common.Coordinate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 extends Day {

	@Override
	protected int getChallengeNumber() {
		return 12;
	}
	
	@Override
	protected Object part1(List<String> input) {
		Set<Moon> moons = getMoons(input);

		return energyAfter(moons, 1000);
	}

	private Set<Moon> getMoons(List<String> input) {
		Set<Moon> moons = new HashSet<>();
		input.forEach(in -> {
			in = in.substring(1, in.length() - 1);
			String[] coords = in.split("(, |=)");
			int x = Integer.parseInt(coords[1]);
			int y = Integer.parseInt(coords[3]);
			int z = Integer.parseInt(coords[5]);
			moons.add(new Moon(x, y, z));
		});
		return moons;
	}

	public Integer energyAfter(Collection<Moon> moons, int steps) {
		for(int i = 0; i < steps; i++) {
			moons.forEach(moon -> {
				moons.forEach(other -> {
					int xDiff = Integer.compare(other.position.getX(), moon.position.getX());
					int yDiff = Integer.compare(other.position.getY(), moon.position.getY());
					int zDiff = Integer.compare(other.position.getZ(), moon.position.getZ());
					moon.updateVelocity(xDiff, yDiff, zDiff);
				});
			});
			moons.forEach(Moon::move);
		}
		return moons.stream()
				.map(Moon::getEnergy)
				.reduce(0, (a,b) -> a + b);
	}

	@Override
	public Object part2(List<String> input) {
		Set<Moon> moons = getMoons(input);
		int xPeriod = calcPeriod(moons.stream().map(m -> m.getPosition().getX()).collect(Collectors.toList()).toArray(new Integer[0]));
		int yPeriod = calcPeriod(moons.stream().map(m -> m.getPosition().getY()).collect(Collectors.toList()).toArray(new Integer[0]));
		int zPeriod = calcPeriod(moons.stream().map(m -> m.getPosition().getZ()).collect(Collectors.toList()).toArray(new Integer[0]));
		return xPeriod + " " + yPeriod + " " + zPeriod;
		//4410095324602605 too high
	}

	private int calcPeriod(Integer[] initial) {
		Integer[] current = Arrays.copyOf(initial, initial.length);
		int steps = 0;
		int[] diff = new int[current.length];
		do {
			for(int i = 0; i < current.length; i++) {
				for(int j = i+1; j < current.length; j++) {
					int comparison = Integer.compare(current[j], current[i]);
					diff[i] += comparison;
					diff[j] -= comparison;
				}
			}
			for(int i = 0; i < current.length; i++) {
				current[i] += diff[i];
			}
			steps++;
		} while (!Objects.deepEquals(current, initial) && steps < 1_000_000);
		return steps;
	}

	public static class Moon {
		private Coord3d position;
		private Coord3d velocity = new Coord3d(0, 0, 0);

		public Moon(int x, int y, int z) {
			position = new Coord3d(x, y, z);
		}

		public Coord3d getPosition() {
			return position;
		}

		public void updateVelocity(int xDiff, int yDiff, int zDiff) {
			velocity.addX(xDiff);
			velocity.addY(yDiff);
			velocity.addZ(zDiff);
		}

		public void move() {
			position.addX(velocity.getX());
			position.addY(velocity.getY());
			position.addZ(velocity.getZ());
		}

		public int getEnergy() {
			return (Math.abs(position.getX()) +
					Math.abs(position.getY()) +
					Math.abs(position.getZ())) *
					(Math.abs(velocity.getX()) +
					Math.abs(velocity.getY()) +
					Math.abs(velocity.getZ()));
		}
	}

	private static class Coord3d extends Coordinate {
		private int z;
		public Coord3d(int x, int y, int z) {
			super(x, y);
			this.z = z;
		}

		public int getZ() {
			return z;
		}

		public void addX(int d) {
			x += d;
		}

		public void addY(int d) {
			y += d;
		}

		public void addZ(int d) {
			z += d;
		}
	}
}
