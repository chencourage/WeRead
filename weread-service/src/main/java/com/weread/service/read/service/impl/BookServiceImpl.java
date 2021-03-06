package com.weread.service.read.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weread.common.base.SystemConfig;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.JsonUtil;
import com.weread.service.base.BaseService;
import com.weread.service.base.req.book.BookSP;
import com.weread.service.read.entity.Book;
import com.weread.service.read.entity.BookCategory;
import com.weread.service.read.entity.BookComment;
import com.weread.service.read.entity.BookContent;
import com.weread.service.read.entity.BookIndex;
import com.weread.service.read.entity.BookSetting;
import com.weread.service.read.mapper.BookMapper;
import com.weread.service.read.mapper.BookSettingMapper;
import com.weread.service.read.service.IBookService;
import com.weread.service.read.service.IBookSettingService;
import com.weread.service.read.vo.BookCommentVO;
import com.weread.service.read.vo.BookSettingVO;
import com.weread.service.read.vo.BookVO;

/**
 * <p>
 * 小说表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class BookServiceImpl extends BaseService<BookMapper, Book> implements IBookService {
	
	@Autowired
	private BookMapper bookMapper;
	
	@Autowired
	private BookSettingMapper bookSettingMapper;
	
	@Autowired
	private IBookSettingService bookSettingService;
	
	@Autowired
	private IRedisService redisService;

	@Override
	public Map<Byte, List<BookSettingVO>> listBookSettingVO() throws Exception {
		String result = redisService.get(SystemConfig.INDEX_BOOK_SETTINGS_KEY);
        if (result == null || result.length() < SystemConfig.OBJECT_JSON_CACHE_EXIST_LENGTH) {
            List<BookSettingVO> list = bookSettingMapper.listVO();
            if (list.size() == 0) {
                //如果首页小说没有被设置，则初始化首页小说设置
                list = initIndexBookSetting();
            }
            result = JsonUtil.toJson(list.stream().collect(Collectors.groupingBy(BookSettingVO::getType)));
            redisService.set(SystemConfig.INDEX_BOOK_SETTINGS_KEY, result);
        }
        return JsonUtil.parseObject(result, Map.class);
	}
	
	/**
     * 初始化首页小说设置
     */
    private List<BookSettingVO> initIndexBookSetting() {
        Date currentDate = new Date();
        List<Book> books = bookMapper.selectIdsByScoreAndRandom(SystemConfig.INDEX_BOOK_SETTING_NUM);
        if (books.size() == SystemConfig.INDEX_BOOK_SETTING_NUM) {
            List<BookSetting> bookSettingList = new ArrayList<>(SystemConfig.INDEX_BOOK_SETTING_NUM);
            List<BookSettingVO> bookSettingVOList = new ArrayList<>(SystemConfig.INDEX_BOOK_SETTING_NUM);
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                int type;
                if (i < 4) {
                    type = 0;
                } else if (i < 14) {
                    type = 1;
                } else if (i < 20) {
                    type = 2;
                } else if (i < 26) {
                    type = 3;
                } else {
                    type = 4;
                }
                BookSettingVO bookSettingVO = new BookSettingVO();
                BookSetting bookSetting = new BookSetting();
                bookSetting.setType(type);
                bookSetting.setSort(i);
                bookSetting.setBookId(book.getId());
                bookSetting.setCreateTime(currentDate);
                bookSetting.setUpdateTime(currentDate);
                bookSettingList.add(bookSetting);

                BeanUtils.copyProperties(book, bookSettingVO);
                BeanUtils.copyProperties(bookSetting, bookSettingVO);
                bookSettingVOList.add(bookSettingVO);
            }
            bookSettingService.insertBatch(bookSettingList);
            return bookSettingVOList;
        }
        return new ArrayList<>(0);
    }

	@Override
	public List<Book> listClickRank() {
		return null;
	}

	@Override
	public List<Book> listNewRank() {
		return null;
	}

	@Override
	public List<BookVO> listUpdateRank() {
		return null;
	}

	@Override
	public Page<BookVO> searchByPage(BookSP params, int page, int pageSize) {
		return null;
	}

	@Override
	public List<BookCategory> listBookCategory() {
		return null;
	}

	@Override
	public Book queryBookDetail(Long id) {
		return null;
	}

	@Override
	public List<BookIndex> queryIndexList(Long bookId, String orderBy, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookIndex queryBookIndex(Long bookIndexId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long queryPreBookIndexId(Long bookId, Integer indexNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long queryNextBookIndexId(Long bookId, Integer indexNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookContent queryBookContent(Long bookIndexId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> listRank(Byte type, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addVisitCount(Long bookId, Integer visitCount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long queryIndexCount(Long bookId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Book> listRecBookByCatId(Integer catId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long queryFirstBookIndexId(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCommentVO> listCommentByPage(Long userId, Long bookId, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBookComment(Long userId, BookComment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getOrCreateAuthorIdByName(String authorName, Byte workDirection) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long queryIdByNameAndAuthor(String bookName, String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> queryIndexNumByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryNetworkPicBooks(String localPicPrefix, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBookPicToLocal(String picUrl, Long bookId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> listBookPageByUserId(Long userId, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book, Long authorId, String penName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBookStatus(Long bookId, Byte status, Long authorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBookContent(Long bookId, String indexName, String content, Byte isVip, Long authorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> queryBookByUpdateTimeByPage(Date startDate, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> queryBookList(Long authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteIndex(Long indexId, Long authorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIndexName(Long indexId, String indexName, Long authorId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String queryIndexContent(Long indexId, Long authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBookContent(Long indexId, String indexName, String content, Long authorId) {
		// TODO Auto-generated method stub
		
	}
	
}
