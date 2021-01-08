package unittest;

import io.github.leejoker.utils.MathTools;
import org.junit.Test;

/**
 * 敏感词实体
 *
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
}
