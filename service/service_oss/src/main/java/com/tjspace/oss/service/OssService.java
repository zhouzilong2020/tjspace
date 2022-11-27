package com.tjspace.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zhouzilong
 */
public interface OssService {


    String uploadFileAvatar(MultipartFile file);
}
