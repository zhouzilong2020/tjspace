package com.tjspace.infoservice.entity.BO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HistoryTeachingBO {
    private Integer semester;
    public static final String SEMESTER;

    private Integer year;
    public static final String YEAR;

    private Integer ratedNumber;
    public static final String RATED_NUMBER;

    private Integer selectedNumber;
    public static final String SELECTED_NUMBER;

    private String startStopWeek;
    public static final String START_STOP_WEEK;

    private String schoolTime;
    public static final String SCHOOL_TIME;

    public static final String HISTORY_TEACHING;

    public static final Map<String,String> ATTRIBUTE_MAP = new HashMap<>();
    static {
        SEMESTER = "SEMESTER";
        YEAR = "year";
        RATED_NUMBER = "rated_number";
        SELECTED_NUMBER = "selected_number";
        START_STOP_WEEK = "start_stop_week";
        SCHOOL_TIME = "school_time";
        HISTORY_TEACHING="*";

        ATTRIBUTE_MAP.put("semester",SEMESTER);
        ATTRIBUTE_MAP.put("year",YEAR);
        ATTRIBUTE_MAP.put("ratedNumber",RATED_NUMBER);
        ATTRIBUTE_MAP.put("selectedNumber",SELECTED_NUMBER);
        ATTRIBUTE_MAP.put("startStopWeek",START_STOP_WEEK);
        ATTRIBUTE_MAP.put("schoolTime",SCHOOL_TIME);
        ATTRIBUTE_MAP.put("historyTeaching",HISTORY_TEACHING);
    }
}
