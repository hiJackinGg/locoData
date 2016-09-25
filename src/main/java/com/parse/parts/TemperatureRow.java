package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.Arrays;

/**
 * Клас, що зберігає температуру у двигунах БАД та номер сенсору.
 */
public class TemperatureRow extends Row {

    private int sensorNumber;
    private short temperature1;
    private short temperature2;

    public TemperatureRow(byte[] bytes, int sensorNumber) {
        super(bytes);
        this.sensorNumber = sensorNumber;
        temperature1 = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 14, 16));
        temperature2 = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 17, 19));
    }

    @Override
    public JSONObject getJSONData() {
        JSONObject result = super.getJSONData();
        int number = sensorNumber * 2;
        result.put("t" + (number - 1), temperature1);
        result.put("t" + number, temperature2);
        return result;
    }
}
