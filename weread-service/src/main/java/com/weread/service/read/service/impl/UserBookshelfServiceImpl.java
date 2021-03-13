package com.weread.service.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.base.BaseService;
import com.weread.service.read.entity.UserBookshelf;
import com.weread.service.read.mapper.UserBookshelfMapper;
import com.weread.service.read.service.IUserBookshelfService;
import com.weread.service.read.vo.BookShelfVO;

/**
 * <p>
 * 用户书架表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class UserBookshelfServiceImpl extends BaseService<UserBookshelfMapper, UserBookshelf> implements IUserBookshelfService {

	@Autowired
	private UserBookshelfMapper userBookshelfMapper;
	
	@Override
	public List<BookShelfVO> listBookShelf(Page page, Long userId) {
		return userBookshelfMapper.listBookShelf(page, userId);
	}
	
}
