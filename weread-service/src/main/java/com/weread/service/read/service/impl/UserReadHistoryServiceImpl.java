package com.weread.service.read.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.base.BaseService;
import com.weread.service.read.entity.UserReadHistory;
import com.weread.service.read.mapper.UserReadHistoryMapper;
import com.weread.service.read.service.IUserReadHistoryService;
import com.weread.service.read.vo.BookReadHistoryVO;

/**
 * <p>
 * 用户阅读记录表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class UserReadHistoryServiceImpl extends BaseService<UserReadHistoryMapper, UserReadHistory> implements IUserReadHistoryService {

	@Autowired
	private UserReadHistoryMapper userReadHistoryMapper;
	
	@Override
	public List<BookReadHistoryVO> listReadHistory(Page page, Long userId) {
		return userReadHistoryMapper.listReadHistory(page, userId);
	}
	
}
