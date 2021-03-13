package com.weread.service.read.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.read.entity.UserBookshelf;
import com.weread.service.read.vo.BookShelfVO;

/**
 * <p>
  * 用户书架表; InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface UserBookshelfMapper extends BaseMapper<UserBookshelf> {
	
	List<BookShelfVO> listBookShelf(@Param("page")Page page,@Param("userId") Long userId);

}