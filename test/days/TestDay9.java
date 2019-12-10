package days;

import common.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class TestDay9 {
    private Day9 day9 = new Day9();

    @Test
    public void testPart1() {
        assert day9.gcd(12, 9) == 3;
        assert day9.gcd(9, 12) == 3;
        assert day9.gcd(1071, 462) == 21;
        assert day9.gcd(462, 1071) == 21;

        List<Coordinate> coords = Arrays.asList(new Coordinate(0,0), new Coordinate(1,1), new Coordinate(2, 2));
        assert day9.isVisibleFrom(coords.get(0), coords.get(1), coords);
        assert !day9.isVisibleFrom(coords.get(0), coords.get(2), coords);
        assert day9.isVisibleFrom(coords.get(1), coords.get(0), coords);
        assert day9.isVisibleFrom(coords.get(1), coords.get(2), coords);
        assert !day9.isVisibleFrom(coords.get(2), coords.get(0), coords);
        assert day9.isVisibleFrom(coords.get(2), coords.get(1), coords);

        Object result = day9.part1(
                Arrays.asList(  ".#..#",
                                ".....",
                                "#####",
                                "....#",
                                "...##"));
        assert Objects.equals(result, "3 4 8");
        result = day9.part1(
                Arrays.asList(
                        "......#.#.",
                        "#..#.#....",
                        "..#######.",
                        ".#.#.###..",
                        ".#..#.....",
                        "..#....#.#",
                        "#..#....#.",
                        ".##.#..###",
                        "##...#..#.",
                        ".#....####"));
        assert Objects.equals(result, "5 8 33");
        result = day9.part1(
                Arrays.asList(
                        "#.#...#.#.",
                        ".###....#.",
                        ".#....#...",
                        "##.#.#.#.#",
                        "....#.#.#.",
                        ".##..###.#",
                        "..#...##..",
                        "..##....##",
                        "......#...",
                        ".####.###."));
        assert Objects.equals(result, "1 2 35");
        result = day9.part1(
                Arrays.asList(".#..#..###",
                        "####.###.#",
                        "....###.#.",
                        "..###.##.#",
                        "##.##.#.#.",
                        "....###..#",
                        "..#.#..#.#",
                        "#..#.#.###",
                        ".##...##.#",
                        ".....#.#.."));
        assert Objects.equals(result, "6 3 41");
        result = day9.part1(
                Arrays.asList(".#..##.###...#######",
                        "##.############..##.",
                        ".#.######.########.#",
                        ".###.#######.####.#.",
                        "#####.##.#.##.###.##",
                        "..#####..#.#########",
                        "####################",
                        "#.####....###.#.#.##",
                        "##.#################",
                        "#####.##.###..####..",
                        "..######..##.#######",
                        "####.##.####...##..#",
                        ".#####..#.######.###",
                        "##...#.##########...",
                        "#.##########.#######",
                        ".####.#.###.###.#.##",
                        "....##.##.###..#####",
                        ".#.#.###########.###",
                        "#.#.#.#####.####.###",
                        "###.##.####.##.#..##"));
        assert Objects.equals(result, "11 13 210");
    }

    @Test
    public void testPart2() {
        Object result = day9.part2(
                Arrays.asList(".#..##.###...#######",
                        "##.############..##.",
                        ".#.######.########.#",
                        ".###.#######.####.#.",
                        "#####.##.#.##.###.##",
                        "..#####..#.#########",
                        "####################",
                        "#.####....###.#.#.##",
                        "##.#################",
                        "#####.##.###..####..",
                        "..######..##.#######",
                        "####.##.####...##..#",
                        ".#####..#.######.###",
                        "##...#.##########...",
                        "#.##########.#######",
                        ".####.#.###.###.#.##",
                        "....##.##.###..#####",
                        ".#.#.###########.###",
                        "#.#.#.#####.####.###",
                        "###.##.####.##.#..##"));
        assert Objects.equals(result, 802);
    }
}
