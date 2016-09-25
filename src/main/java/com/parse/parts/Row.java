package com.parse.parts;

import org.json.simple.JSONObject;
import com.parse.BinaryUtils;
import com.parse.Section;

import java.util.Arrays;
import java.util.Date;

/**
 * Клас предзначений для зберігання рядка, який вилучається з файлу,
 * що зберігає параметри датчиків локомотива.
 */
public class Row {
    //дата записи
    private Date date;

    //секция
    private Section section;

    //массив нерассшифрованных параметров
    private byte[] data;

    public Row(byte[] bytes) {
        data = bytes;
        date = BinaryUtils.getOLETimeFromBytes(Arrays.copyOfRange(bytes, 0, 8));
        section = Section.getSection(bytes[8]);
    }

    public JSONObject getJSONData() {
        JSONObject header = new JSONObject();
        header.put("d", date.getTime() / 1000);//prevent
        header.put("s", section);
        return header;
    }

    public Date getDate() {
        return date;
    }

    public Section getSection() {
        return section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Row)) return false;

        Row that = (Row) o;

        if (!date.equals(that.date)) return false;
        if (section != that.section) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + section.toString().hashCode();
        return result;
    }
}
