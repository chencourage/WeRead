package com.weread.service.read.service;

import com.weread.service.base.IBaseService;
import com.weread.service.read.entity.Author;

/**
 * <p>
 * 作者表; InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IAuthorService extends IBaseService<Author> {
	
	public Boolean checkPenName(String penName);
	
}
