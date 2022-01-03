package me.oglass.hotslicerrpg;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Set;

public class CustomBlockData {

    private HashMap<String, Object> DataMap;
    public CustomBlockData() {
        DataMap = new HashMap<>();
    }

    public void setInteger(String keyName, Integer Value) {
        DataMap.put(keyName, Value);
    }
    public void setDouble(String keyName, Double Value) {
        DataMap.put(keyName, Value);
    }
    public void setBoolean(String keyName, Boolean Value) {
        DataMap.put(keyName, Value);
    }
    public void setString(String keyName, String Value) {
        DataMap.put(keyName, Value);
    }

    public Integer getInteger(String keyName) {
        if (DataMap.get(keyName) instanceof Integer) {
            return (Integer) DataMap.get(keyName);
        } else return null;
    }
    public Double getDouble(String keyName) {
        if (DataMap.get(keyName) instanceof Double) {
            return (Double) DataMap.get(keyName);
        } else return null;
    }
    public Boolean getBoolean(String keyName) {
        if (DataMap.get(keyName) instanceof Boolean) {
            return (Boolean) DataMap.get(keyName);
        } else return false;
    }
    public String getString(String keyName) {
        if (DataMap.get(keyName) instanceof String) {
            return (String) DataMap.get(keyName);
        } else return null;
    }
    public Set<String> getKeys() {
        return DataMap.keySet();
    }
    public HashMap<String, Object> getDataMap() {
        return DataMap;
    }

    public void removeKey(String keyName) {
        DataMap.remove(keyName);
    }
}
