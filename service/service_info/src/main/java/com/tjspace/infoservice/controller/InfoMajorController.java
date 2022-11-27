package com.tjspace.infoservice.controller;

import com.tjspace.infoservice.entity.DO.InfoMajor;
import com.tjspace.infoservice.service.InfoMajorService;
import com.tjspace.utils.commonutils.UniformResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Fu Lin
 * @since 2020-12-13
 */
@RestController
@RequestMapping("/infoservice")
public class InfoMajorController {
    @Autowired
    private InfoMajorService infoMajorService;

    @ApiOperation("按院系ID查询课程")
    @GetMapping("depts/{deptId}/majors")
    public UniformResult getMajorsByDeptId(
            @ApiParam(value = "院系ID", required = true)
            @RequestParam String deptId
    ) {
        List<InfoMajor> infoMajorList = infoMajorService.getMajorNames(deptId);
        return UniformResult.ok().data("infoMajorList", infoMajorList);
    }
}
