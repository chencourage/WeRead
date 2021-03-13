package com.weread.service.read.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weread.service.read.entity.BookComment;
import com.weread.service.read.vo.BookCommentVO;

/**
 * <p>
  * 小说评论表; InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface BookCommentMapper extends BaseMapper<BookComment> {
	
	List<BookCommentVO> listCommentByPage(@Param("userId") Long userId, @Param("bookId") Long bookId);

}