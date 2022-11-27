package com.tjspace.infoservice.controller;


import com.tjspace.infoservice.entity.DO.InfoDept;
import com.tjspace.infoservice.service.InfoDeptService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
@RestController
@RequestMapping("/infoservice")
public class InfoDeptController {
    @Autowired
    private InfoDeptService infoDeptService;

    @ApiOperation("查询所有院系")
    @GetMapping("depts/names")
    public UniformResult getDeptNames() {
        List<InfoDept> deptNames = infoDeptService.getDeptNames();
        return UniformResult.ok().data("deptNameList", deptNames);
    }
}

