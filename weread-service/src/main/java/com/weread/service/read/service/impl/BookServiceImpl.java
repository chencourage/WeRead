package com.weread.service.read.service.impl;

import com.weread.service.read.entity.Book;
import com.weread.service.read.mapper.BookMapper;
import com.weread.service.read.service.IBookService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class BookServiceImpl extends BaseService<BookMapper, Book> implements IBookService {
	
}
