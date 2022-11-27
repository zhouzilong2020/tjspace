package com.tjspace.msmservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MSMMessage {
    private String code;
    private String content;
}
