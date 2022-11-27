package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.infoservice.entity.DO.InfoDept;
import com.tjspace.infoservice.entity.DO.InfoMajor;
import com.tjspace.infoservice.mapper.InfoDeptMapper;
import com.tjspace.infoservice.mapper.InfoMajorMapper;
import com.tjspace.infoservice.service.InfoDeptService;
import com.tjspace.infoservice.service.InfoMajorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoMajorServiceImpl extends ServiceImpl<InfoMajorMapper, InfoMajor> implements InfoMajorService {
    @Override
    public List<InfoMajor> getMajorNames(String deptId) {
        QueryWrapper<InfoMajor> wrapper = new QueryWrapper<>();
        wrapper.select(InfoMajor.NAME,InfoMajor.ID)
                .eq(InfoMajor.DEPT_ID,deptId);
        return this.list(wrapper);
    }
}
