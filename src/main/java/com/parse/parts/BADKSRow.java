package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.Arrays;

/**
 * Temperature of the block BAD.
 */
public class BADKSRow extends Row {

    private short temperature;

    public BADKSRow(byte[] bytes) {
        super(bytes);
        temperature = BinaryUtils.getShort(Arrays.copyOfRange(bytes, 19, 21));
    }

    @Override
    public JSONObject getJSONData() {
        JSONObject result = super.getJSONData();
        result.put("tb", temperature);
        return result;
    }
}
