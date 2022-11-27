package com.tjspace.infoservice.mapper;

import com.tjspace.infoservice.entity.DO.InfoUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-29
 */
@Mapper
public interface InfoUserMapper extends BaseMapper<InfoUser> {
    @MapKey("userId")
    Map<String, UserPublicInfoDTO> selectUsersPublicInfo(@Param("userIds") Set<String> userIds, @Param("attributeSet") Set<String> attributeSet);
}
