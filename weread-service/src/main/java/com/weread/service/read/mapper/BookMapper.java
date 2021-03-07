package com.weread.service.read.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.weread.service.base.req.book.BookSP;
import com.weread.service.read.entity.Book;
import com.weread.service.read.vo.BookVO;

/**
 * <p>
  * 小说表; InnoDB free: 6144 kB Mapper 接口
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface BookMapper extends BaseMapper<Book> {
	
	List<BookVO> searchByPage(Page page,@Param("book")BookSP params);

    void addVisitCount(@Param("bookId") Long bookId, @Param("visitCount") Integer visitCount);

    List<Book> listRecBookByCatId(@Param("catId") Integer catId);

    void addCommentCount(@Param("bookId") Long bookId);

    List<Book> queryNetworkPicBooks(@Param("localPicPrefix") String localPicPrefix, @Param("limit") Integer limit);
    /**
     * 按评分随机查询小说集合
     * @param limit 查询条数
     * @return 小说集合
     * */
    List<Book> selectIdsByScoreAndRandom(@Param("limit") int limit);

}