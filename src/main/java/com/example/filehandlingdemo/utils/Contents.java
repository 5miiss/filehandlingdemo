package com.example.filehandlingdemo.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Contents {

private String en;
private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

public String getEn() {
return en;
}

public void setEn(String en) {
this.en = en;
}

public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}