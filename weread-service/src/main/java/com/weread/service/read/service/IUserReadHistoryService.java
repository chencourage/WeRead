package com.weread.service.read.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.base.IBaseService;
import com.weread.service.read.entity.UserReadHistory;
import com.weread.service.read.vo.BookReadHistoryVO;

/**
 * <p>
 * 用户阅读记录表; InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IUserReadHistoryService extends IBaseService<UserReadHistory> {
	public List<BookReadHistoryVO> listReadHistory(Page page,Long userId);
}
