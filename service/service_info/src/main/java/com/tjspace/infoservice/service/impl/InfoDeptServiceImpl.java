package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.DO.InfoDept;
import com.tjspace.infoservice.mapper.InfoDeptMapper;
import com.tjspace.infoservice.service.InfoDeptService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class InfoDeptServiceImpl extends ServiceImpl<InfoDeptMapper, InfoDept> implements InfoDeptService {

    @Override
    public List<InfoDept> getDeptNames() {
        QueryWrapper<InfoDept> wrapper = new QueryWrapper<>();
        wrapper.select(InfoDept.ID,InfoDept.NAME);
        return this.list(wrapper);
    }
}
