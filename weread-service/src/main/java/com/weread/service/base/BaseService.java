package com.weread.service.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

public class BaseService<M extends BaseMapper<E>,E extends BaseEntity> extends ServiceImpl<BaseMapper<E>, E> implements IBaseService<E>{

	protected Logger log = LoggerFactory.getLogger(getClass());
}
