package com.weread.service.read.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.read.entity.UserReadHistory;
import com.weread.service.read.vo.BookReadHistoryVO;

/**
 * <p>
  * 用户阅读记录表; InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface UserReadHistoryMapper extends BaseMapper<UserReadHistory> {
	
	public List<BookReadHistoryVO> listReadHistory(@Param("page")Page page,@Param("userId") Long userId);

}