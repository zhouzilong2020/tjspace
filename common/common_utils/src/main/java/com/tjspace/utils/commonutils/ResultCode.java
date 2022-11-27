package com.tjspace.utils.commonutils;

/**
 * @author zhouzilong
 */
public interface ResultCode {
    Integer SUCCESS = 200;
    Integer NO_CONTENT = 206;

    Integer BAD_REQUEST = 400;
    Integer UNAUTHORIZED=401;
    Integer FORBIDDEN=403;
    Integer NOT_FOUND=404;
    Integer INTERNET_SERVER_ERROR = 500;

}
