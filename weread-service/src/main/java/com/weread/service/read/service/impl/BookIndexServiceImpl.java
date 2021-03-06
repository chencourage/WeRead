package com.weread.service.read.service.impl;

import com.weread.service.read.entity.BookIndex;
import com.weread.service.read.mapper.BookIndexMapper;
import com.weread.service.read.service.IBookIndexService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说目录表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class BookIndexServiceImpl extends BaseService<BookIndexMapper, BookIndex> implements IBookIndexService {
	
}
