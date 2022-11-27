package com.tjspace.evlservice.entity.VO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@ApiModel(description = "新增评论对象")
public class NewCommentVO {
    @ApiModelProperty(value = "评论文字内容",example = "这个老师……")
    private String content;

    @ApiModelProperty(value = "教师教学评分",example = "5",required = true)
    private Integer teachingScore;

    @ApiModelProperty(value = "课程内容评分",example = "4",required = true)
    private Integer contentScore;

    @ApiModelProperty(value = "课程成绩评分",example = "3",required = true)
    private Integer gradingScore;

    @ApiModelProperty(value = "工作量评分",example = "2",required = true)
    private Integer workloadScore;
}
