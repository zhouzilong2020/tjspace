package com.tjspace.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.tjspace.oss.service.OssService;
import com.tjspace.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author zhouzilong
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传头像到oss
     * 需要进行分类存放 --> 年月日
     * 以及防止重名存放！
     *
     * @param file 上传的文件
     * @return oss中的位置
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // 通过工具类获取值
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;

        // 上传文件流。

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            // 获取上传文件流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称
            String fileName = file.getOriginalFilename();


            // 1. 添加随机值， 使得所有文件都不相同, 防止同名覆盖
            String uuid = UUID.randomUUID().toString();
            fileName = uuid + "-" + fileName;

            // 2. 按照日期存放分类 2020/11/01/uuid+fileName
            String datePate = new DateTime().toString("yyyy/MM/dd");
            fileName = datePate + "/" + fileName;

            // 上传，第二个为上传到oss中的路径，也可以直邮文件名
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();

            // 返回在oss中的路径， 需要手动拼接！
            // example：https://tjspace-2020.oss-cn-shanghai.aliyuncs.com/TJU.png
            return "https://" + bucketName + "." + endpoint + "/" + fileName;
    
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
