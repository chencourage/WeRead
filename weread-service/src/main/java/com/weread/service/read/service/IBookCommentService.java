package com.weread.service.read.service;

import java.util.List;

import com.weread.service.base.IBaseService;
import com.weread.service.read.entity.BookComment;
import com.weread.service.read.vo.BookCommentVO;

/**
 * <p>
 * 小说评论表; InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IBookCommentService extends IBaseService<BookComment> {
	
	List<BookCommentVO> listCommentByPage(Long userId,Long bookId);
	
}
