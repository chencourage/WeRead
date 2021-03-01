package com.weread.service.read.service.impl;

import com.weread.service.read.entity.BookComment;
import com.weread.service.read.mapper.BookCommentMapper;
import com.weread.service.read.service.IBookCommentService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 小说评论表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class BookCommentServiceImpl extends BaseService<BookCommentMapper, BookComment> implements IBookCommentService {
	
}
