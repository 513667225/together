package com.together.modules.user.utli;

import com.together.modules.user.entity.UserEntity;
import com.together.modules.user.entity.UserEntityDo;

public class UserEntityToUserEntityDoUtli {

    public static UserEntityDo userEntityToUserEntityDoUtli(UserEntity userEntity){
        if(userEntity!=null){
            UserEntityDo userEntityDo=new UserEntityDo();
            userEntityDo.setGoupSize(userEntity.getGoupSize());
            userEntityDo.setUserId(userEntity.getUserId());
            userEntityDo.setMajordomoSize(userEntity.getMajordomoSize());
            userEntityDo.setManagerSize(userEntity.getManagerSize());
            userEntityDo.setTeammanagerSize(userEntity.getTeammanagerSize());
            userEntityDo.setMemberSize(userEntity.getMemberSize());
            userEntityDo.setUserNickname(userEntity.getUserNickname());
            userEntityDo.setUserReferrer(userEntity.getUserReferrer());
            userEntityDo.setUserLevel(userEntity.getUserLevel());
            userEntityDo.setUnderlingSize(userEntity.getUnderlingSize());
            userEntityDo.setUserMobile(userEntity.getUserMobile());
            userEntityDo.setUserAvatar(userEntity.getUserAvatar());
            return userEntityDo;
        }
        return null;
    }
}
