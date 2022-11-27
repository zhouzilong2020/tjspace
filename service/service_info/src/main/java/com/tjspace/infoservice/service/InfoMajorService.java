package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.DO.InfoDept;
import com.tjspace.infoservice.entity.DO.InfoMajor;

import java.util.List;

public interface InfoMajorService extends IService<InfoMajor> {

    /**
     * 按院系ID查询专业名称
     *
     * @param deptId  院系ID
     * @return  专业名称列表
     */
    List<InfoMajor> getMajorNames(String deptId);
}
