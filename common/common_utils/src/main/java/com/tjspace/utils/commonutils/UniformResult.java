package com.tjspace.utils.commonutils;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果类
 *
 * @author zhouzilong
 */
@Data
public class UniformResult {
    @ApiModelProperty(value = "请求是否成功")
    private Boolean success;
    @ApiModelProperty(value = "返回码")
    private Integer code;
    @ApiModelProperty(value = "返回消息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private Object data=null;

    private UniformResult() {
    }

    public static UniformResult ok() {
        UniformResult ur = new UniformResult();
        ur.setCode(ResultCode.SUCCESS);
        ur.setSuccess(true);
        ur.setMessage("成功");
        return ur;
    }

    public static UniformResult error() {
        UniformResult ur = new UniformResult();
        ur.setCode(ResultCode.INTERNET_SERVER_ERROR);
        ur.setSuccess(false);
        ur.setMessage("失败");
        return ur;
    }

    public static UniformResult noContent(){
        UniformResult ur = new UniformResult();
        ur.setCode(ResultCode.NO_CONTENT);
        ur.setSuccess(true);
        ur.setMessage("成功");
        return ur;
    }

    @SuppressWarnings("unchecked")
    public UniformResult data(String key, Object value) {
        if(data == null || !(data instanceof HashMap)){
            data = new HashMap<String,Object>();
        }
        ((HashMap<String,Object>)data).put(key, value);
        return this;
    }

    public UniformResult data(Map<String, Object> data) {
        this.setData(data);
        return this;
    }

    public UniformResult data(Object data){
        this.data=data;
        return this;
    }

    public UniformResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public UniformResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public UniformResult success(Boolean success) {
        this.setSuccess(success);
        return this;
    }
}
