import common.AdventMath;
import days.Day12;
import days.Day16;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TestDay16 {
	Day16 day16 = new Day16();

    @Test
    public void testPart1() {
        System.out.println(Arrays.toString(day16.fft(new int[]{1,2,3,4,5,6,7,8})));
        assert Objects.deepEquals(day16.fft(new int[]{1,2,3,4,5,6,7,8}), new int[]{4,8,2,2,6,1,5,8});
    }

    @Test
    public void testPart2() {
    }
}