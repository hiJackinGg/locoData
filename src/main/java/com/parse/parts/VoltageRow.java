package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.Arrays;

/**
 * Клас, що зберігає напругу у контактній мережі,
 * энапругу акумуляторної батареї, ток збудження ТЕД.
 */
public class VoltageRow extends Row {

    private short excitationCurrent;
    private short catenaryVoltage;
    private short batteryVoltage;

    public VoltageRow(byte[] bytes) {
        super(bytes);
        catenaryVoltage = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 14, 16));
        batteryVoltage = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 16, 18));
        excitationCurrent = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 18, 20));
    }

    @Override
    public JSONObject getJSONData() {
        JSONObject result = super.getJSONData();
        result.put("Uc", catenaryVoltage);
        result.put("Ub", batteryVoltage);
        result.put("Ie", excitationCurrent);
        return result;
    }
}
