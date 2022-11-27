package com.tjspace.bbsservice.entity.VO;

import lombok.Data;

@Data
public class PostBriefVO {
    private String userId;
    private String title;
    private Integer positiveCount;
    private Integer negativeCount;
    private Boolean positive = false;
    private Boolean negative = false;
}
