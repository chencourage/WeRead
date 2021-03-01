package com.weread.service.read.service.impl;

import com.weread.service.read.entity.BookContent;
import com.weread.service.read.mapper.BookContentMapper;
import com.weread.service.read.service.IBookContentService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说内容表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class BookContentServiceImpl extends BaseService<BookContentMapper, BookContent> implements IBookContentService {
	
}
