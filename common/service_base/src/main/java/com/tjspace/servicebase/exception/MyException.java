package com.tjspace.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义异常
 *
 * @author zhouzilong
 * @AllArgsConstructor 有参数构造
 * @NoArgsConstructor 无参构造
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException {
    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常消息
     */
    private String msg;

}
