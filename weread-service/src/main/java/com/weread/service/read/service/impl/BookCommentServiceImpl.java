package com.weread.service.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weread.service.base.BaseService;
import com.weread.service.read.entity.BookComment;
import com.weread.service.read.mapper.BookCommentMapper;
import com.weread.service.read.service.IBookCommentService;
import com.weread.service.read.vo.BookCommentVO;

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

	@Autowired
	public BookCommentMapper bookCommentMapper;
	
	@Override
	public List<BookCommentVO> listCommentByPage(Long userId, Long bookId) {
		return bookCommentMapper.listCommentByPage(userId, bookId);
	}
	
}
