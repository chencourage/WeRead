package com.weread.service.read.service.impl;

import com.weread.service.read.entity.Author;
import com.weread.service.read.mapper.AuthorMapper;
import com.weread.service.read.service.IAuthorService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 作者表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class AuthorServiceImpl extends BaseService<AuthorMapper, Author> implements IAuthorService {

	@Override
	public Boolean checkPenName(String penName) {
		return null;
	}
	
}
