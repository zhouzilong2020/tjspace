package com.tjspace.servicebase.config;

public class AliyunOssConfig {
    static private final String endpoint = "oss-cn-shanghai.aliyuncs.com";
    static private final String accessKeyId = "SOMEKEYID";
    static private final String accessKeySecrete = "SOMESECRETE";
    static private final String bucketName = "tjspace-2020";

    static public String getEndpoint() {
        return endpoint;
    }

    static public String getAccessKeyId() {
        return accessKeyId;
    }

    static public String getAccessKeySecrete() {
        return accessKeySecrete;
    }

    static public String getBucketName() {
        return bucketName;
    }
}
