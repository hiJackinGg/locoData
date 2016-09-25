package com.parse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import com.parse.parts.Header;
import com.parse.parts.Row;
import com.parse.parts.RowFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Клас-парсер, що займається конвертацією вхідного файлу (вл11м6) у файл формату JSON.
 *
 */
public class LocomotiveDataParser {
    private static final int HEADER_SIZE = 32;
    private static final int ROW_SIZE = 23;

    /**
     * Run to choose file for parsing and then to save it as json.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String fileName = "G:\\Diploma\\Титан Хартрон\\Titan\\Архивы ВЛ11М6\\№503\\2015\\Январь\\01-01-2015.вл11м6";
        String result = parse(fileName);
        PrintWriter writer = new PrintWriter(
                fileName + ".json", "UTF-8");
        writer.print(result);
        writer.close();
    }

    /**
     *
     * @param path путь к файлу для парсинга
     * @return строку в формате JSON
     * @throws IOException
     */
    public static String parse(String path) throws IOException {
        RandomAccessFile in = new RandomAccessFile(path, "r");

        byte[] byteArray = new byte[(int) in.length()];
        in.readFully(byteArray);

        return JSONValue.toJSONString(parseByteArray(byteArray));

    }

    /**
     *
     * @param byteArray байт, которыйы нужно рассшифровать
     * @return списов JSONObject объектов
     */
    public static List<JSONObject> parseByteArray(byte[] byteArray) {
        long startTime = System.currentTimeMillis();
        Header header = new Header(Arrays.copyOfRange(byteArray, 0, HEADER_SIZE));

        List<JSONObject> rows = new ArrayList<>(header.getSize() / 4);
        List<byte[]> failedToParse = Collections.synchronizedList(new LinkedList());
        try {
            rows.addAll(parseConcurrent(byteArray, Runtime.getRuntime().availableProcessors(),
                    header.getSize(), failedToParse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Collections.sort(rows, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject jsonObject, JSONObject jsonObject2) {
                try {
                    long date1 = (long) jsonObject.get("d");
                    long date2 = (long) jsonObject2.get("d");
                    if (date1 > date2) return 1;
                    if (date1 < date2) return -1;
                    else return 0;
                } catch (Exception e) {
                    return 0;
                }
            }

            @Override
            public boolean equals(Object o) {
                return false;
            }
        });

        System.out.println("---");
        System.out.println("Parsed " + rows.size() + " unique rows (" + (System.currentTimeMillis() - startTime) + "ms)");
        System.out.println("Failed to parse: " + failedToParse.size() + " ( " + (failedToParse.size() * 100) / header.getSize() + "% )");


        return rows;
    }

    public static Collection<JSONObject> parseConcurrent(final byte[] data, int threadsAmount, int rowsAmount,
                                                         final List<byte[]> failedToParse) throws InterruptedException {
        final int rowsPerThread = rowsAmount / threadsAmount;
        Map<Integer, JSONObject> rowsMap = new ConcurrentHashMap<>();

        executeConcurrent(threadsAmount, threadNumber -> {
            int rowsStart = threadNumber * rowsPerThread;
            for (int i = rowsStart; i < rowsStart + rowsPerThread; i++) {
                int offset = HEADER_SIZE + (ROW_SIZE * i);
                byte[] rowBytes = Arrays.copyOfRange(data, offset, offset + ROW_SIZE);
                try {
                    Row row = RowFactory.getRow(rowBytes);
                    if (!row.getSection().equals(Section.SECTION_UNKNOWN)) {
                        JSONObject rowsData = rowsMap.get(row.hashCode());
                        if (rowsData == null) {
                            rowsData = row.getJSONData();
                            rowsMap.put(row.hashCode(), rowsData);
                        } else {
                            rowsData.putAll(row.getJSONData());
                        }
                    }
                } catch (Exception e) {
                    failedToParse.add(rowBytes);
                }
            }
        });

        return rowsMap.values();
    }

    public static void executeConcurrent(int threadsAmount, final ThreadAction action) throws InterruptedException {
        final List<Thread> threads = new ArrayList<>(threadsAmount);
        for (int threadNumber = 0; threadNumber < threadsAmount; threadNumber++) {
            final int finalThreadNumber = threadNumber;
            Thread newThread = new Thread(() -> action.execute(finalThreadNumber));
            newThread.start();
            threads.add(newThread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}


