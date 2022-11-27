package com.tjspace.infoservice.client;


import com.tjspace.utils.commonutils.UniformResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * 调用端接口， 调用OSS服务
 * OSS 客户端服务
 * 定义调用方法的路径，实现类在服务端的方法对应
 * 不需要实现类， 远程会调用对端服务的东西 PRC！
 * TODO:把client注入到相应的服务中
 *
 * @author zhouzilong
 */
//@FeignClient(name = "service-oss")
@Component
public interface OssClient {
    /**
     * 使用完全路径， 定义oss服务端的路径
     *
     * @param file 头像
     * @return 统一返回结果
     */
    @PostMapping("/infooss/fileoss/avatar/{id}")
    public UniformResult uploadOssFile(
            MultipartFile file,
            @PathVariable(name = "id") String id);

}
