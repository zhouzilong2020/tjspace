package com.tjspace.infoservice.service.impl;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.tjspace.infoservice.entity.DO.InfoUser;
import com.tjspace.infoservice.entity.VO.UserVO;
import com.tjspace.infoservice.mapper.InfoUserMapper;
import com.tjspace.infoservice.service.InfoUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tjspace.servicebase.entity.DTO.UserPublicInfoDTO;
import com.tjspace.servicebase.exception.MyException;
import com.tjspace.utils.commonutils.Const;
import com.tjspace.utils.commonutils.ResultCode;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zilong Zhou
 * @since 2020-11-24
 */
@Service
public class InfoUserServiceImpl extends ServiceImpl<InfoUserMapper, InfoUser> implements InfoUserService {

    @Override
    public Map<String, UserPublicInfoDTO> getUsersPublicInfo(Set<String> userIds, String... attributes) {
        Set<String> attributeSet;
        if(!ArrayUtils.isEmpty(attributes)) {
            attributeSet = new HashSet<>(Arrays.asList(attributes));
        }else {
            attributeSet = new HashSet<>();
        }
        return baseMapper.selectUsersPublicInfo(userIds, attributeSet);
    }

    @Override
    public void updateUserInfo(String userId, UserVO userVO) {
        InfoUser infoUser = new InfoUser();
        BeanUtils.copyProperties(userVO,infoUser);
        if(userVO.getAvatar()!=null){
            infoUser.setAvatar(saveAvatar(userVO.getAvatar()));
        }
        infoUser.setId(userId);
        this.updateById(infoUser);
    }

    /**
     * 保存头像为图片文件
     *
     * @param base64Str  base64图片
     * @return  图片文件名
     */
    @Transactional(rollbackFor = {Exception.class})
    String saveAvatar(String base64Str){
        StringBuilder fileName = new StringBuilder();
        fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
        if (base64Str.contains("data:image/png;")) {
            base64Str = base64Str.replace("data:image/png;base64,", "");
            fileName.append(".png");
        } else if (base64Str.contains("data:image/jpeg;")) {
            base64Str = base64Str.replace("data:image/jpeg;base64,", "");
            fileName.append(".jpeg");
        }
        File file = new File(Const.AVATAR_PATH, fileName.toString());
        byte[] fileBytes = Base64.getDecoder().decode(base64Str);
        try {
            FileUtils.writeByteArrayToFile(file, fileBytes);
        } catch (IOException e) {
            throw new MyException(ResultCode.INTERNET_SERVER_ERROR,"保存头像失败");
        }
        return fileName.toString();
    }
}
