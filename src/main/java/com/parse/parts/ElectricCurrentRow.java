package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.Arrays;

/**
 * Клас, що зберігає струм якоря ТЕД 1-2, струм якоря ТЕД 3-4,
 * струм акумуляторної батареї.
 */
public class ElectricCurrentRow extends Row {

    private short armatureCurrent12;
    private short armatureCurrent34;
    private short batteryCurrent;

    public ElectricCurrentRow(byte[] bytes) {
        super(bytes);
        armatureCurrent12 = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 14, 16));
        armatureCurrent34 = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 16, 18));
        batteryCurrent = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 18, 20));
    }

    @Override
    public JSONObject getJSONData() {
        JSONObject result = super.getJSONData();
        result.put("Ia12", armatureCurrent12);
        result.put("Ia34", armatureCurrent34);
        result.put("Ib", batteryCurrent);
        return result;
    }
}
