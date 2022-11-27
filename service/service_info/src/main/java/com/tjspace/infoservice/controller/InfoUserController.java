package com.tjspace.infoservice.controller;


import com.tjspace.infoservice.entity.VO.UserVO;
import com.tjspace.infoservice.service.InfoUserService;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;


/**
 * <p>
 * 前端控制器
 * </p>
 * RestController = controller，requestbody
 * REST 风格
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@RestController
@Api("用户信息管理")
@RequestMapping("/infoservice")
public class InfoUserController {
    /**
     * 把service注入, mybatis完成了很多封装（service-->mapper->wrapper）
     * /infoservice/info-user/findAll
     */
    @Autowired
    private InfoUserService infoUserService;

    @GetMapping("inner/public-infos")
    public Map<String, UserPublicInfoDTO> getUsersPublicInfo(
            @ApiParam(value = "若干用户ID", required = true)
            @RequestParam Set<String> userIds,
            @ApiParam(value = "筛选字段")
            @RequestParam(required = false) String... attributes
    ) {
        return infoUserService.getUsersPublicInfo(userIds, attributes);
    }

    @ApiOperation("更新用户信息")
    @PutMapping("personal/info")
    public UniformResult updateUserInfo(
            @RequestHeader("userId") String userId,
            @ApiParam(value = "用户信息对象", required = true)
            @RequestBody UserVO userVO
    ) {
        infoUserService.updateUserInfo(userId, userVO);
        return UniformResult.ok().data(userVO);
    }
}














