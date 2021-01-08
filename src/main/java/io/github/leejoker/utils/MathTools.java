package io.github.leejoker.utils;

import java.math.BigDecimal;

/**
 * math tools
 * <p>
 * the time unit is hour
 * the param of sin or cos is radians
 *
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2020/12/28 13:29
 */
public class MathTools {
    private static final int DIVIDE_MODE = BigDecimal.ROUND_HALF_UP;

    /**
     * @param lat latitude
     * @param ha  hour angle
     * @return
     * @see <a href="https://blog.csdn.net/my_michealyang/article/details/51290728">太阳高度角/方位角计算公式</a>
     */
    public static Double sunAltitude(Double lat, Double ha, Double sunDeclination) {
        assert lat != null;
        assert ha != null;
        assert sunDeclination != null;
        lat = Math.toRadians(lat);
        ha = Math.toRadians(ha);
        sunDeclination = Math.toRadians(sunDeclination);
        double sinHs = Math.sin(lat) * Math.sin(sunDeclination)
                + Math.cos(lat) * Math.cos(sunDeclination) * Math.cos(ha);
        return Math.asin(sinHs);
    }

    /**
     * @param lat latitude
     * @return
     */
    public static Double sunAzimuth(Double lat, Double altitude, Double sunDeclination) {
        assert lat != null;
        assert altitude != null;
        assert sunDeclination != null;
        altitude = Math.toRadians(altitude);
        lat = Math.toRadians(lat);
        sunDeclination = Math.toRadians(sunDeclination);
        double param1 = Math.sin(altitude) * Math.sin(lat) - Math.sin(sunDeclination);
        double param2 = Math.cos(altitude) * Math.cos(lat);
        return Math.acos(decimalDivide(param1, param2));
    }

    /**
     * unit is degree
     *
     * @param dayNumber day number in current year
     * @return
     */
    public static Double declination(Integer dayNumber) {
        assert dayNumber != null && dayNumber != 0;
        double b = decimalDivide(2 * Math.PI * (dayNumber - 1), 365D);
        return 0.006918 - 0.399912 * Math.cos(b) + 0.070257 * Math.sin(b)
                - 0.006758 * Math.cos(2 * b) + 0.000907 * Math.sin(2 * b)
                - 0.002697 * Math.cos(3 * b) + 0.00148 * Math.sin(3 * b);
    }

    /**
     * unit is degree
     *
     * @param trueSunHour
     * @return
     */
    public static Double hourAngle(Double trueSunHour) {
        assert trueSunHour != null;
        return (trueSunHour - 12) * 15;
    }

    /**
     * @param hour
     * @param longitude
     * @param timeDifference
     * @return
     */
    public static Double trueSunHour(Double hour, Double longitude, Double timeDifference) {
        assert longitude != null;
        return hour - (120D - longitude) * decimalDivide(4D, 60D) + timeDifference;
    }


    /**
     * use mode BigDecimal.ROUND_HALF_UP
     *
     * @param a
     * @param b
     * @return a / b
     */
    public static Double decimalDivide(Double a, Double b) {
        assert a != null && b != null && b.intValue() != 0;
        BigDecimal bd1 = BigDecimal.valueOf(a);
        BigDecimal bd2 = BigDecimal.valueOf(b);
        return bd1.divide(bd2, DIVIDE_MODE).doubleValue();
    }

    /**
     * 均时差,近似值
     *
     * @param dayNumber
     * @return
     * @see <a href="https://www.knowpia.cn/pages/%E5%9D%87%E6%97%B6%E5%B7%AE">均时差</a>
     */
    public static Double et(Integer dayNumber) {
        assert dayNumber != null && dayNumber != 0;

        Double b = decimalDivide(2 * Math.PI * (dayNumber - 81), 364D);
        return 9.87 * Math.sin(2 * b) - 7.53 * Math.cos(b) - 1.5 * Math.sin(b);
    }

    /**
     * @param hour
     * @param minutes
     * @param seconds
     * @return
     */
    public static Double transferToHour(int hour, int minutes, int seconds) {
        return hour + decimalDivide((double) minutes, 60.0)
                + decimalDivide(decimalDivide((double) seconds, 60.0), 60.0);
    }
}
