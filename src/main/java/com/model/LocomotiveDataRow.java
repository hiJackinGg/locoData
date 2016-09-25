package com.model;


import org.json.simple.JSONObject;


/**
 * Class which represents locomotive data row.
 */
public class LocomotiveDataRow {

    private int locomotiveId;
    private int parameterId;
    private boolean isSectionA;
    private long time;
    private int value;


    public LocomotiveDataRow(int locomotiveId, int parameterId, boolean isSectionA, long time, int value) {
        this.locomotiveId = locomotiveId;
        this.parameterId = parameterId;
        this.isSectionA = isSectionA;
        this.time = time;
        this.value = value;
    }

    public int getLocomotiveId() {
        return locomotiveId;
    }

    public void setLocomotiveId(int locomotiveId) {
        this.locomotiveId = locomotiveId;
    }

    public int getParameterId() {
        return parameterId;
    }

    public void setParameterId(int parameterId) {
        this.parameterId = parameterId;
    }

    public boolean isSectionA() {
        return isSectionA;
    }

    public void setSectionA(boolean sectionA) {
        isSectionA = sectionA;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public JSONObject toJSON() {
        JSONObject result = new JSONObject();
        result.put("locomotiveId", locomotiveId);
        result.put("parameterId", parameterId);
        result.put("isSectionA", isSectionA);
        result.put("time", time);
        result.put("value", value);
        return result;
    }
}
