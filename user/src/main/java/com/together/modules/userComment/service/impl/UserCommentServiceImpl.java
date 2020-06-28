package com.together.modules.userComment.service.impl;

import com.together.modules.userComment.entity.UserCommentEntity;
import com.together.modules.userComment.mapper.UserCommentMapper;
import com.together.modules.userComment.service.IUserCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论表 服务实现类
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@Service
public class UserCommentServiceImpl extends ServiceImpl<UserCommentMapper, UserCommentEntity> implements IUserCommentService {

}
