package com.weread.service.read.service.impl;

import com.weread.service.read.entity.CrawlBatchTask;
import com.weread.service.read.mapper.CrawlBatchTaskMapper;
import com.weread.service.read.service.ICrawlBatchTaskService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 批量抓取任务表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class CrawlBatchTaskServiceImpl extends BaseService<CrawlBatchTaskMapper, CrawlBatchTask> implements ICrawlBatchTaskService {
	
}
