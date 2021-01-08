package unittest;

import io.github.leejoker.core.SunAltitude;
import io.github.leejoker.utils.MathTools;
import org.junit.Test;

/**
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2020/12/28 13:29
 */
public class MathToolTest {
    @Test
    public void etTest() {
        Double result = MathTools.et(8);
        System.out.println(result);
    }

    @Test
    public void calCurSunAltitudeTest() {
        double degree = SunAltitude.calCurSunAltitude(39.916527, 116.397128);
        System.out.println(degree);
    }
}
