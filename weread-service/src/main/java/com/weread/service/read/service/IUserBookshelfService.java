package com.weread.service.read.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.base.IBaseService;
import com.weread.service.read.entity.UserBookshelf;
import com.weread.service.read.vo.BookShelfVO;

/**
 * <p>
 * 用户书架表; InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IUserBookshelfService extends IBaseService<UserBookshelf> {
	List<BookShelfVO> listBookShelf(Page page,Long userId);
}
