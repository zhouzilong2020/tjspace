package com.tjspace.bbsservice.entity.BO;

import com.tjspace.bbsservice.entity.DO.BbsSubreply;
import lombok.Data;

import java.util.List;

@Data
public class SubreplyListBO {
    private String replyId;
    private List<BbsSubreply> subreplyList;
}
