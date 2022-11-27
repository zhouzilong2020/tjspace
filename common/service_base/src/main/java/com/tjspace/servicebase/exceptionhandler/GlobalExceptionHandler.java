package com.tjspace.servicebase.exceptionhandler;

import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.UniformResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理类
 * Slf4j异常记录日志！
 *
 * @author zhouzilong
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 全局所有异常处理
     *
     * @param e 异常类型
     * @return 统一格式
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // 为了能够返回数据
    public UniformResult error(Exception e) {
        // 将异常信息记录到日志文件中
        log.error(e.getMessage());
        e.printStackTrace();
        return UniformResult.error().message("服务器内部错误");
    }

    /**
     * 系统特殊异常处理,用的比较少
     *
     * @param e 异常类型
     * @return 统一格式
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public UniformResult error(ArithmeticException e) {
        e.printStackTrace();
        return UniformResult.error().message("系统自定义：：服务器内部错误");
    }

    /**
     * 用户自定异常处理
     *
     * @param e 异常类型
     * @return 统一格式
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public UniformResult error(MyException e) {
        e.printStackTrace();
        // 返回用户自定义的异常规则！
        return UniformResult.error().code(e.getCode()).message(e.getMsg());
    }
}
