package com.tjspace.evlservice.entity.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class CommentVO extends NewCommentVO{
    @JsonProperty("commentId")
    private String id;

    private Integer positiveCount;

    private Integer negativeCount;

    private String userId;

    private String nickname;

    private Boolean positive = false;

    private Boolean negative = false;

    @JsonProperty("createTime")
    private Date gmtCreate;

    public static final Map<String,String> ATTRIBUTE_MAP = new HashMap<>();

    static {
        ATTRIBUTE_MAP.put("commentId","id");
        ATTRIBUTE_MAP.put("userId","user_id");
        ATTRIBUTE_MAP.put("teachingScore","teaching_score");
        ATTRIBUTE_MAP.put("contentScore","content_score");
        ATTRIBUTE_MAP.put("gradingScore","grading_score");
        ATTRIBUTE_MAP.put("workloadScore","workload_score");
        ATTRIBUTE_MAP.put("totScore","tot_score");
        ATTRIBUTE_MAP.put("positiveCount","positive_count");
        ATTRIBUTE_MAP.put("negativeCount","negative_count");
        ATTRIBUTE_MAP.put("differentCount","different_count");
        ATTRIBUTE_MAP.put("totCount","tot_count");
        ATTRIBUTE_MAP.put("createTime","gmt_create");
        ATTRIBUTE_MAP.put("latestTime","gmt_modified");
    }
}
