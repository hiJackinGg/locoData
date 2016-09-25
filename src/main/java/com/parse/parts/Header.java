package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;

import java.util.Arrays;
import java.util.Date;

/**
 * Клас, що зберігає заголовок файлу.
 * Цей заголовок містить інформацію про кількість рядків у файлі, та дату запису першого рядка.
 */
public class Header {
    /**
     * size of the file
     */
    private int size;
    private Date date;

    public Header(byte[] bytes) {
        size = BinaryUtils.getInt(Arrays.copyOfRange(bytes, 0, 8));
        date = BinaryUtils.getOLETimeFromBytes(Arrays.copyOfRange(bytes, 8, 16));
    }

    public JSONObject getJSONData() {
        JSONObject header = new JSONObject();
        header.put("date", date);
        header.put("size", size);
        return header;
    }

    public int getSize() {
        return size;
    }
}
