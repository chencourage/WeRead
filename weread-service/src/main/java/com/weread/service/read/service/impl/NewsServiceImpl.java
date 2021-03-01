package com.weread.service.read.service.impl;

import com.weread.service.read.entity.News;
import com.weread.service.read.mapper.NewsMapper;
import com.weread.service.read.service.INewsService;
import com.weread.service.base.BaseService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class NewsServiceImpl extends BaseService<NewsMapper, News> implements INewsService {
	
}
