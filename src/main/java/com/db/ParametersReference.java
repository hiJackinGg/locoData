package com.db;

import com.datastax.driver.core.Session;
import com.model.Parameter;
import org.json.simple.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * /**
 * Loads all indicator parameters from database.
 */
public class ParametersReference {
    private static ParametersReference instance = null;

    private ParametersReference(Session session) {
        parameters.addAll(
                session.execute("SELECT id, name, alias FROM locomotives.parameters").all().stream().map(row ->
                        new Parameter(
                                row.getInt("id"),
                                row.getString("name"),
                                row.getString("alias")))
                        .collect(Collectors.toList()));
    }

    public static ParametersReference getInstance(Session session) {
        if (instance == null) {
            instance = new ParametersReference(session);
        }
        return instance;
    }

    private List<Parameter> parameters = new ArrayList<>(100);

    public int getIdByAlias(String alias) {
//        parameters.stream().forEach(row -> System.out.println(row.getAlias()));

        return parameters.stream()
                .filter(parameter -> parameter.getAlias().equals(alias))
                .findFirst()
                .get().getId();
    }

    public String toJSON() {
        JSONArray result = new JSONArray();
        parameters.stream().forEach(parameter -> result.add(parameter.toJSON()));
        return result.toJSONString();
    }
}
