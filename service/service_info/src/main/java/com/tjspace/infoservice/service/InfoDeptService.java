package com.tjspace.infoservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tjspace.infoservice.entity.DO.InfoDept;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
public interface InfoDeptService extends IService<InfoDept> {
    /**
     * 查询所有院系名称
     *
     * @return  所有院系名称列表
     */
    List<InfoDept> getDeptNames();
}
