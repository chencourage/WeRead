package com.weread.service.read.mapper;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.weread.service.read.entity.BookSetting;
import com.weread.service.read.vo.BookSettingVO;

/**
 * <p>
  * 首页小说设置表; InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface BookSettingMapper extends BaseMapper<BookSetting> {
	
	/**
	 * 查询首页设置
	 * @return
	 */
	public List<BookSettingVO> listVO();

}