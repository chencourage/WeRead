package com.weread.service.read.service.impl;

import com.weread.service.read.entity.UserReadHistory;
import com.weread.service.read.mapper.UserReadHistoryMapper;
import com.weread.service.read.service.IUserReadHistoryService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

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
	
}
