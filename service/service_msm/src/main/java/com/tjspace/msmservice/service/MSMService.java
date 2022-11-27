package com.tjspace.msmservice.service;

import java.util.Map;

/**
 * @author zhouzilong
 */
public interface MSMService {
    Boolean send(Map<String, Object> param, String phone);
}
