package com.tjspace.oss.controller;


import com.tjspace.oss.service.OssService;
import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * CrossOrigin：解决跨域问题
 *
 * @author zhouzilong
 */
@RestController
@RequestMapping("/infooss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像的方法
     *
     * @param file
     * @return
     */
    @PostMapping("avatar/{id}")
    public UniformResult uploadOssFile(
            MultipartFile file,
            @PathVariable(name = "id") String id) {
        // 返回上传到oss后的url
        String url = ossService.uploadFileAvatar(file);
        return UniformResult.ok().data("url", url);
    }
}
