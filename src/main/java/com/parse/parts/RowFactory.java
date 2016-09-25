package com.parse.parts;

import java.util.Arrays;


/**
 * Клас, що реалізує шаблон проектування – Фабрика.
 * На основі вхідного рядка у вигляді масиву байтів,
 * створює та повертає об’єкт класу, що наслідує клас Row
 */
public final class RowFactory {
//static int temp = 0;
    public static Row getRow(byte[] bytes) {
        byte[] typeBytes = Arrays.copyOfRange(bytes, 12, 14);

        if (compareBytesPairs(typeBytes, new byte[]{0x00, 0x70}) ||
                compareBytesPairs(typeBytes, new byte[]{0x00, 0x71})) {

            return new VoltageRow(bytes);
        }

        if (compareBytesPairs(typeBytes, new byte[]{0x00, 0x72}) ||
                compareBytesPairs(typeBytes, new byte[]{0x00, 0x73})) {

            return new ElectricCurrentRow(bytes);
        }

        if (compareBytesPairs(typeBytes, new byte[]{0x01, 0x44})) {

            return new BADKSRow(bytes);
        }

        if (compareBytesPairs(typeBytes, new byte[]{0x01, 0x30}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x31}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x32}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x33}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x34}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x35}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x36}) ||
                compareBytesPairs(typeBytes, new byte[]{0x01, 0x37})) {

            return new TemperatureRow(bytes, typeBytes[1] - 47);
        }

//        if (compareBytesPairs(typeBytes, new byte[]{0x04, 0x01})) {
//            return new BDD(bytes);
//        }

        // Types with unknown structure
        if (compareBytesPairs(typeBytes, new byte[]{0x01, 0x10}) ||
                compareBytesPairs(typeBytes, new byte[]{0x00, 0x75}) ||
                compareBytesPairs(typeBytes, new byte[]{0x00, 0x74}) ||
                compareBytesPairs(typeBytes, new byte[]{0x04, 0x00}) ||
                compareBytesPairs(typeBytes, new byte[]{0x04, 0x01}) ||
                compareBytesPairs(typeBytes, new byte[]{0x00, 0x00})) {

            return new Row(bytes);
        }


        System.out.println("Unknown row type:" + Arrays.toString(typeBytes));

        return new Row(bytes);
    }

    private static boolean compareBytesPairs(byte[] bytes1, byte[] bytes2) {
        return bytes1[0] == bytes2[0] && bytes1[1] == bytes2[1];
    }
}
