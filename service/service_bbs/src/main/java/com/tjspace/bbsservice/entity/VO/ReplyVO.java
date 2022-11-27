package com.tjspace.bbsservice.entity.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReplyVO {
    @JsonProperty("replyId")
    private String id;

    private String userId;

    private String content;

    @JsonProperty("createTime")
    private Date gmtCreate;

    private List<BbsSubreply> subreplyList;
}
