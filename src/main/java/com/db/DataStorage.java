package com.db;

import com.datastax.driver.core.*;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.parse.LocomotiveDataParser;
import com.parse.Section;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class to  manage database.
 */
public class DataStorage {
    private static Cluster connection = Cluster.builder()
            .addContactPoint("localhost")
            .build();

    private static final String keyspace = "locomotives";
    private static final String locomotivesDataTable = "data";
    private static final String locomotiveIdField = "locomotive_id";
    private static final String parameterIdField = "parameter_id";
    private static final String sectionAField = "section_a";
    private static final String timeField = "time";
    private static final String valueField = "value";


    /**
     * Retrieves parameter values in indicated time range
     * @param locomotiveId
     * @param parameterId
     * @param isSectionA
     * @param startTime
     * @param endTime
     * @param valuesAmount amount of data (chart points) to be returned to show on a chart. If number of
     *                     indicated points is fewer then stored amount of data (values) in DB,
     *                     the retrieved values are grouped by its value then calculated the average.
     * @return
     */
    public static List<Integer> getValuesInTimeRange(int locomotiveId, int parameterId, boolean isSectionA, long startTime, long endTime, int valuesAmount) {
        Session session = DataStorage.connection.connect();

        Statement exampleQuery = QueryBuilder.select(valueField)
                .from(keyspace, locomotivesDataTable)
                .where(QueryBuilder.eq(locomotiveIdField, locomotiveId)).
                        and(QueryBuilder.eq(parameterIdField, parameterId)).
                        and(QueryBuilder.eq(sectionAField, isSectionA)).
                        and(QueryBuilder.gt(timeField, startTime)).
                        and(QueryBuilder.lt(timeField, endTime));

        ResultSet results = session.execute(exampleQuery);
        List<Integer> values = formValues(results, valuesAmount);

        session.close();

        return Collections.unmodifiableList(values);
    }

    private static List<Integer> formValues(ResultSet results, int valuesAmount){
        List<Row> resultRows = results.all();

        int rowCount = resultRows.size();
        int rowsPerValue = rowCount / valuesAmount;

        List<Integer> values = new ArrayList<>(valuesAmount);

        int i = 0;
        int temp[] = new int[rowsPerValue];

        for (Row row : resultRows) {

            temp[i] = row.getInt("value");
            i++;

            if (i == rowsPerValue) {
                i = 0;

                values.add(IntStream.of(temp).sum() / temp.length);

                if (values.size() >= valuesAmount)
                    break;
            }

        }
        System.out.println(values.size());
        return values;

    }

    /**
     * Retrieves parameters.
     * @return
     */
    public static String getParameters() {
        Session session = DataStorage.connection.connect();
        ParametersReference pr = ParametersReference.getInstance(session);
        session.close();
        return pr.toJSON();

    }

    /**
     * Parse and load data to DB from indicated file (.вл11м6).
     * @param file
     */
    public static void loadData(File file) {
        long time = System.currentTimeMillis();
        try (RandomAccessFile in = new RandomAccessFile(file, "r")){

            byte[] byteArray = new byte[(int) in.length()];
            in.readFully(byteArray);

            List<JSONObject> result = LocomotiveDataParser.parseByteArray(byteArray);
            String loco_id = file.getAbsolutePath().substring(file.getAbsolutePath().
                    indexOf("Архивы ВЛ11М6\\") + "Архивы ВЛ11М6\\".length() + 1, file.getAbsolutePath().
                    indexOf("Архивы ВЛ11М6\\") + "Архивы ВЛ11М6\\".length() + 4);

            insertData(result, Integer.valueOf(loco_id));
            System.out.println("done (" + (System.currentTimeMillis() - time) + "ms): " + file);
        } catch (IOException e) {
            System.out.println("fail: " + file);
            e.printStackTrace();
        }

    }

    /**
     * Insert data rows to DB.
     * Query is being built in StringBuilder. When its size is too large then the string is been inserted and cleaned.
     * Otherwise it can be heap overflow!
     * @param dataRows
     * @param loco_id
     */
    private static void insertData(List<JSONObject> dataRows, int loco_id) {
        Session session = DataStorage.connection.connect();
        ParametersReference parametersReference = ParametersReference.getInstance(session);
        StringBuilder query = new StringBuilder("");


        dataRows.stream().forEach(object -> object.keySet().stream().forEach(key -> {
            if (!key.equals("s") && !key.equals("d")) {
//                System.out.println("!"+parametersReference.getIdByAlias((String) key));
                query.append("INSERT INTO locomotives.data (locomotive_id, parameter_id, section_a, time, value) " +
                        "VALUES ("
                        + loco_id + ", "
                        + parametersReference.getIdByAlias((String) key) + ","
                        + object.get("s").equals(Section.SECTION_A) + ", "
                        + ((long) object.get("d") * 1000) + ", "
                        + object.get(key) + " );");
            }

            if (query.length() > 150000) {
                executeBatch(session, query);
                query.delete(0, query.length());
            }
        }));
//       session.execute("BEGIN BATCH " + query.toString() + " APPLY BATCH;");
//        session.execute(query.toString());
        if (query.length() > 0) {
            executeBatch(session, query);
        }

    }

    private static void executeBatch(Session session, StringBuilder query) {
        session.execute("BEGIN BATCH " + query.toString() + " APPLY BATCH;");
    }

    /**
     * Loads default parameters.
     * @param session
     */
    public static void loadParameters(Session session) {
        String[] queries = new String[]{
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (3, 'tb', 'температура в блоке БАД');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (4, 'Ia12', 'сила тока в двигателе 1-2');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (5, 'Ia34', 'сила тока в двигателе 3-4');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (6, 'Ib', 'сила тока в батареи');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (7, 'Uc', 'напряжение в сети');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (8, 'Ub', 'напряжение в батареи');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (9, 'Ie', 'сила тока возбуждения');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (10, 't1', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (11, 't2', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (12, 't3', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (13, 't4', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (14, 't5', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (15, 't6', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (16, 't7', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (17, 't8', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (18, 't9', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (19, 't10', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (20, 't11', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (21, 't12', 'температура');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (22, 't13', 'температура м1 (двигателя)');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (23, 't14', 'температура м2 (двигателя)');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (24, 't15', 'температура м3 (двигателя)');",
                "INSERT INTO locomotives.parameters (id, alias, name) VALUES (25, 't16', 'температура м4 (двигателя)');",
        };
        for (String query : queries) {
            session.execute(query);
        }

        System.out.println("parameters loaded");
    }

}
