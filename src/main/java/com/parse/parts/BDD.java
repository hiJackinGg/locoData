package com.parse.parts;


import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.stream.IntStream;

/**
 * discrete parameters
 */
public class BDD extends Row  {

   
    byte[] bytes;

    public BDD(byte[] bytes) {
        super(bytes);
        this.bytes = bytes;
    }

    @Override
    public JSONObject getJSONData() {
        JSONObject result = super.getJSONData();

        int []voids = new int[] {2, 3, 10, 22, 33, 43, 47, 48, 49, 50, 51};
        int c = 0;
        int num = 1;

        for (int i = 15; i < 22; i++ )
            for (int j = 7; j >= 0; j--){
                c++;
                final int temp = c;
                if ( IntStream.of(voids).anyMatch(x -> x == temp) )
                    continue;
                result.put("BDD_param_" + (num++), BinaryUtils.getBit(bytes[i], j));
            }

        return result;
    }
}
