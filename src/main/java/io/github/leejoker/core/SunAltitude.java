package io.github.leejoker.core;

import io.github.leejoker.utils.MathTools;

import java.util.Calendar;
import java.util.Date;

/**
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2021/1/8 16:19
 */
public class SunAltitude {
    public static Double calCurSunAltitude(Double lat, Double lon) {
        Date d = new Date();
        Double trueSunHour = getTrueSunHour(d, lon);
        Double ha = MathTools.hourAngle(trueSunHour);
        Double sunDeclination = MathTools.sunDeclination(getDayNumber(d));
        return Math.toDegrees(MathTools.sunAltitude(lat, ha, sunDeclination));
    }

    public static Integer getDayNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static Double getTrueSunHour(Date date, Double lon) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int sec = calendar.get(Calendar.SECOND);
        Double curTime = MathTools.transferToHour(hour, min, sec);
        Double difference = MathTools.et(calendar.get(Calendar.DAY_OF_YEAR));
        return MathTools.trueSunHour(curTime, lon, difference);
    }
}
