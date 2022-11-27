package com.tjspace.infoservice.entity.BO;

import lombok.Builder;
import lombok.Data;

/**
 * 课程的起始、结束时间
 *
 * @author zhouzilong
 */
@Data
@Builder
public class StartEndDate {
    private Integer start;
    private Integer end;
}
