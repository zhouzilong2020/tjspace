package com.tjspace.bbsservice.entity.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class PostVO extends PostBriefVO {
    @JsonProperty("postId")
    private String id;

    private String content;

    private String nickname;

    private Integer replyCount;

    @JsonProperty("createTime")
    private Date gmtCreate;

    @JsonProperty("latestTime")
    private Date gmtModified;

    //映射到数据库字段
    static final public Map<String, String> ATTRIBUTE_MAP = new HashMap<>();
    static {
        ATTRIBUTE_MAP.put("userId", "user_id");
        ATTRIBUTE_MAP.put("positiveCount", "positive_count");
        ATTRIBUTE_MAP.put("negativeCount", "negative_count");
        ATTRIBUTE_MAP.put("replyCount", "reply_count");
        ATTRIBUTE_MAP.put("postId", "id");
        ATTRIBUTE_MAP.put("createTime", "gmt_create");
        ATTRIBUTE_MAP.put("latestTime", "gmt_modified");
    }

}
