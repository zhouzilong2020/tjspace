package com.tjspace.ucenterservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjspace.ucenterservice.entity.InfoUser;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-12-03
 */
public interface InfoUserMapper extends BaseMapper<InfoUser> {
    /**
     * 查询注册人数
     *
     * @param Date 天
     * @return 人数
     */
    Integer countRegisterDay(@Param("date") String Date);
}
