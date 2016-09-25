package com.model;

import org.json.simple.JSONObject;

/**
 * Class which represents indicator parameter of locomotive.
 */
public class Parameter {
    private int id;
    private String name;
    private String alias;

    public Parameter(int id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("id", id);
        result.put("name", name);
        result.put("alias", alias);
        return result;
    }
}
