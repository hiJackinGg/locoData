package com.parse;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Клас, що реалізує методи для розшифрування параметрів з масиву байтів.
 * Тахож конвертує дату OLETime у більш звичний Date.
 * Даний клас має лише статичні методи та являється утилятивним, тобто, запропоновує службовий функціонал.
 */
public final class BinaryUtils {

    private BinaryUtils(){}


    public static Double getDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getDouble();
    }

    public static Integer getInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static Short getShort(byte[] bytes) {
        return ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN).getShort();
    }

    /**
     *
     * @param b
     * @param position of bit [0;7]
     * @return bit value int the specified position of byte
     */
    public static int getBit(byte b, int position) {
        return (b >> position) & 1;
    }

    public static String getTimeFromOLETime(String oleTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String date = null;
        String time = null;

        if (oleTime ==  null || oleTime.length() == 0) {
            return null;
        }

        if (!oleTime.contains(".")) {
            oleTime += ".0";
        }

        int daysElapsed = Integer.parseInt(oleTime.substring(0, oleTime.indexOf(".")));

        double hoursElapsed = 24 * Double.parseDouble("0." + oleTime.substring(oleTime.indexOf(".") + 1, oleTime.length()));

        String minutesElapsedTemp = Double.toString(hoursElapsed);
        double minutesElapsed = 60 * Double.parseDouble("0." + minutesElapsedTemp.substring(minutesElapsedTemp.indexOf(".") + 1));

        String secondsElapsedTemp = Double.toString(minutesElapsed);
        double secondsElapsed = 60 * Double.parseDouble("0." + secondsElapsedTemp.substring(secondsElapsedTemp.indexOf(".") + 1));

        time = Double.toString(hoursElapsed).substring(0, Double.toString(hoursElapsed).indexOf(".")) + ":"
                + Double.toString(minutesElapsed).substring(0, Double.toString(minutesElapsed).indexOf(".")) + ":"
                + Double.toString(secondsElapsed).substring(0, Double.toString(secondsElapsed).indexOf("."));

        Calendar c1 = Calendar.getInstance();
        c1.set(1899, 11, 30);
        c1.add(Calendar.DAY_OF_MONTH, daysElapsed);

        date = sdf.format(c1.getTime());

        return date + " " + time;

    }

    /**
     *  Values for the timestamp type are encoded as 64-bit signed integers representing
     * a number of milliseconds since the standard base time known as the epoch:
     * January 1 1970 at 00:00:00 GMT.
     * @param bytes
     * @return
     */
    public static Date getOLETimeFromBytes(byte[] bytes) {
        Double doubleValue = getDouble(bytes);

        if (doubleValue == null || doubleValue == 0) {
            return null;
        }

        int daysElapsed = doubleValue.intValue();

        Double hours = 24 * (doubleValue - daysElapsed);

        int hoursElapsed = hours.intValue();

        Double minutes = 60 * (hours - hoursElapsed);

        int minutesElapsed = minutes.intValue();

        Double secondsElapsed = 60 * (minutes - minutesElapsed);

        Calendar c1 = Calendar.getInstance();
        c1.set(1899, 11, 30, 0, 0, 0);
        c1.set(Calendar.MILLISECOND, 0);
        c1.add(Calendar.DAY_OF_MONTH, daysElapsed);
        c1.add(Calendar.HOUR, hoursElapsed);
        c1.add(Calendar.MINUTE, minutesElapsed);
        c1.add(Calendar.SECOND, secondsElapsed.intValue());

        return c1.getTime();

    }


}
