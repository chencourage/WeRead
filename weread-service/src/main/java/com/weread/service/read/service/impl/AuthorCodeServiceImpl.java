package com.weread.service.read.service.impl;

import com.weread.service.read.entity.AuthorCode;
import com.weread.service.read.mapper.AuthorCodeMapper;
import com.weread.service.read.service.IAuthorCodeService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作家邀请码表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class AuthorCodeServiceImpl extends BaseService<AuthorCodeMapper, AuthorCode> implements IAuthorCodeService {
	
}
