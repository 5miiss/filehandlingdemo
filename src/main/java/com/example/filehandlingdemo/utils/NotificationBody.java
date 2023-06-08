package com.example.filehandlingdemo.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Builder
@ToString
@Data
public class NotificationBody {

private String app_id;
private List<String> include_player_ids;
private Headings headings;
private Map<String, Object> data;
private  Map<String, String> contents;
private Map<String, Object> additional_properties = new LinkedHashMap<String, Object>();;



}