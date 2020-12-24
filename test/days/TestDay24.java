package days;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay24 {
	private Day24 day24 = new Day24();
	
	@Test
	public void testPart1() {
		List<String> input = Arrays.asList(
				"sesenwnenenewseeswwswswwnenewsewsw",
				"neeenesenwnwwswnenewnwwsewnenwseswesw",
				"seswneswswsenwwnwse",
				"nwnwneseeswswnenewneswwnewseswneseene",
				"swweswneswnenwsewnwneneseenw",
				"eesenwseswswnenwswnwnwsewwnwsene",
				"sewnenenenesenwsewnenwwwse",
				"wenwwweseeeweswwwnwwe",
				"wsweesenenewnwwnwsenewsenwwsesesenwne",
				"neeswseenwwswnwswswnw",
				"nenwswwsewswnenenewsenwsenwnesesenew",
				"enewnwewneswsewnwswenweswnenwsenwsw",
				"sweneswneswneneenwnewenewwneswswnese",
				"swwesenesewenwneswnwwneseswwne",
				"enesenwswwswneneswsenwnewswseenwsese",
				"wnwnesenesenenwwnenwsewesewsesesew",
				"nenewswnwewswnenesenwnesewesw",
				"eneswnwswnwsenenwnwnwwseeswneewsenese",
				"neswnwewnwnwseenwseesewsenwsweewe",
				"wseweeenwnesenwwwswnew");
		assert Objects.equals(day24.part1(input), 10);
	}
	
	@Test
	public void testPart2() {
		List<String> input = Arrays.asList(
				"sesenwnenenewseeswwswswwnenewsewsw",
				"neeenesenwnwwswnenewnwwsewnenwseswesw",
				"seswneswswsenwwnwse",
				"nwnwneseeswswnenewneswwnewseswneseene",
				"swweswneswnenwsewnwneneseenw",
				"eesenwseswswnenwswnwnwsewwnwsene",
				"sewnenenenesenwsewnenwwwse",
				"wenwwweseeeweswwwnwwe",
				"wsweesenenewnwwnwsenewsenwwsesesenwne",
				"neeswseenwwswnwswswnw",
				"nenwswwsewswnenenewsenwsenwnesesenew",
				"enewnwewneswsewnwswenweswnenwsenwsw",
				"sweneswneswneneenwnewenewwneswswnese",
				"swwesenesewenwneswnwwneseswwne",
				"enesenwswwswneneswsenwnewswseenwsese",
				"wnwnesenesenenwwnenwsewesewsesesew",
				"nenewswnwewswnenesenwnesewesw",
				"eneswnwswnwsenenwnwnwwseeswneewsenese",
				"neswnwewnwnwseenwseesewsenwsweewe",
				"wseweeenwnesenwwwswnew");
		assert Objects.equals(day24.part2(input), 2208);
	}
}
