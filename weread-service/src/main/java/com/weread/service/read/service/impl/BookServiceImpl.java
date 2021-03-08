package com.weread.service.read.service.impl;

import static com.java2nb.novel.mapper.BookCategoryDynamicSqlSupport.bookCategory;
import static com.java2nb.novel.mapper.BookContentDynamicSqlSupport.bookContent;
import static com.java2nb.novel.mapper.BookDynamicSqlSupport.book;
import static com.java2nb.novel.mapper.BookDynamicSqlSupport.id;
import static com.java2nb.novel.mapper.BookIndexDynamicSqlSupport.bookIndex;
import static org.mybatis.dynamic.sql.SqlBuilder.count;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static org.mybatis.dynamic.sql.SqlBuilder.isLessThan;
import static org.mybatis.dynamic.sql.select.SelectDSL.select;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.github.pagehelper.PageHelper;
import com.java2nb.novel.mapper.BookCategoryDynamicSqlSupport;
import com.java2nb.novel.mapper.BookContentDynamicSqlSupport;
import com.java2nb.novel.mapper.BookIndexDynamicSqlSupport;
import com.weread.common.base.SystemConfig;
import com.weread.common.exception.REDException;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.JsonUtil;
import com.weread.common.utils.StringUtil;
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
import com.weread.service.read.service.IBookCategoryService;
import com.weread.service.read.service.IBookCommentService;
import com.weread.service.read.service.IBookContentService;
import com.weread.service.read.service.IBookIndexService;
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
	
	@Autowired
	private IBookCategoryService bookCategoryService;
	
	@Autowired
	private IBookIndexService bookIndexService;
	
	@Autowired
	private IBookContentService bookContentService;
	
	@Autowired
	private IBookCommentService bookCommentService;

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
		List<Book> result = redisService.getList(SystemConfig.INDEX_CLICK_BANK_BOOK_KEY,Book.class);
        if (result == null || result.size() == 0) {
            result = listRank((byte) 0, 10);
            redisService.setList(SystemConfig.INDEX_CLICK_BANK_BOOK_KEY, result);
            redisService.expire(SystemConfig.INDEX_CLICK_BANK_BOOK_KEY, 5000, TimeUnit.SECONDS);
        }
        return result;
	}

	@Override
	public List<Book> listNewRank() {
		List<Book> result = redisService.getList(SystemConfig.INDEX_NEW_BOOK_KEY,Book.class);
        if (result == null || result.size() == 0) {
            result = listRank((byte) 1, 10);
            redisService.setList(SystemConfig.INDEX_NEW_BOOK_KEY, result);
            redisService.expire(SystemConfig.INDEX_NEW_BOOK_KEY, 3600, TimeUnit.SECONDS);
        }
        return result;
	}

	@Override
	public List<BookVO> listUpdateRank() {
		List<BookVO> result = (List<BookVO>) redisService.getList(SystemConfig.INDEX_UPDATE_BOOK_KEY,BookVO.class);
        if (result == null || result.size() == 0) {
            List<Book> bookPOList = listRank((byte) 2, 23);
            result = BeanUtil.copyList(bookPOList, BookVO.class);
            redisService.setList(SystemConfig.INDEX_UPDATE_BOOK_KEY, result);
            redisService.expire(SystemConfig.INDEX_UPDATE_BOOK_KEY, 600, TimeUnit.SECONDS);
        }
        return result;
	}

	@Override
	public Page<BookVO> searchByPage(BookSP params, int page, int pageSize) {
		
		if (params.getUpdatePeriod() != null) {
            long cur = System.currentTimeMillis();
            long period = params.getUpdatePeriod() * 24 * 3600 * 1000;
            long time = cur - period;
            params.setUpdateTimeMin(new Date(time));
        }
		Page<BookVO> pageq = new Page<BookVO>(page,pageSize);
		List<BookVO> recoders = bookMapper.searchByPage(pageq,params);
		pageq.setRecords(recoders);
        return pageq;
	}

	@Override
	public List<BookCategory> listBookCategory() {
		Wrapper<BookCategory> wrapper = new EntityWrapper<BookCategory>();
		wrapper.orderBy("sort", false);
		bookCategoryService.selectList(wrapper);
        return bookCategoryService.selectList(wrapper);
	}

	@Override
	public Book queryBookDetail(Long id) {
        return selectById(id);
	}

	@Override
	public List<BookIndex> queryIndexList(Long bookId, String orderBy, Integer page, Integer pageSize) {
        Page pageq = new Page(page,pageSize);
        Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
        wrapper.eq("book_id", bookId);
        if(StringUtil.isNotEmpty(orderBy)){
        	wrapper.orderBy(orderBy);
        }
        bookIndexService.selectPage(pageq,wrapper);
        return pageq.getRecords();
	}

	@Override
	public BookIndex queryBookIndex(Long bookIndexId) {
		return bookIndexService.selectById(bookIndexId);
	}

	@Override
	public Long queryPreBookIndexId(Long bookId, Integer indexNum) {
        Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
        wrapper.eq("book_id", bookId);
        wrapper.lt("index_num", indexNum);
        wrapper.orderBy("index_num", false);
        wrapper.last("limit 1");
        List<BookIndex> list = bookIndexService.selectList(wrapper);
        if (list.size() == 0) {
            return 0L;
        } else {
            return list.get(0).getId();
        }
	}

	@Override
	public Long queryNextBookIndexId(Long bookId, Integer indexNum) {
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
        wrapper.eq("book_id", bookId);
        wrapper.gt("index_num", indexNum);
        wrapper.orderBy("index_num",true);
        wrapper.last("limit 1");
        List<BookIndex> list = bookIndexService.selectList(wrapper);
        if (list.size() == 0) {
            return 0L;
        } else {
            return list.get(0).getId();
        }
	}

	@Override
	public BookContent queryBookContent(Long bookIndexId) {
		Wrapper<BookContent> wrapper = new EntityWrapper<BookContent>();
		wrapper.eq("index_id", bookIndexId);
		wrapper.last("limit 1");
		List<BookContent> contentList = bookContentService.selectList(wrapper);
		if (contentList.size() == 0) {
            return null;
        } else {
            return contentList.get(0);
        }
	}

	@Override
	public List<Book> listRank(Byte type, Integer limit) {
        Wrapper<Book> wrapper = new EntityWrapper<Book>();
        wrapper.gt("word_count", 0);
        if(type == 1){
        	wrapper.orderBy("create_time",false);
        }else if(type == 2){
        	wrapper.orderBy("last_index_update_time",false);
        }else if(type == 3){
        	wrapper.orderBy("comment_count",false);
        }
        return this.selectList(wrapper);
	}

	@Override
	public void addVisitCount(Long bookId, Integer visitCount) {
		bookMapper.addVisitCount(bookId, visitCount);
	}

	@Override
	public long queryIndexCount(Long bookId) {
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
		wrapper.eq("book_id", bookId);
        return bookIndexService.selectCount(wrapper);
	}

	@Override
	public List<Book> listRecBookByCatId(Integer catId) {
		return bookMapper.listRecBookByCatId(catId);
	}

	@Override
	public Long queryFirstBookIndexId(Long bookId) {
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
		wrapper.eq("book_id", bookId);
		wrapper.orderBy("index_num");
		wrapper.last("limit 1");
		List<BookIndex> list = bookIndexService.selectList(wrapper);
		if (list.size() == 0) {
            return 0L;
        } else {
            return list.get(0).getId();
        }
	}

	@Override
	public List<BookCommentVO> listCommentByPage(Long userId, Long bookId, int page, int pageSize) {
		/*PageHelper.startPage(page, pageSize);
        OrderByHelper.orderBy("t1.create_time desc");
        return bookCommentMapper.listCommentByPage(userId, bookId);*/
		
		return null;
	}

	@Override
	public void addBookComment(Long userId, BookComment comment) {
		//判断该用户是否已评论过该书籍
        /*SelectStatementProvider selectStatement = select(count(BookCommentDynamicSqlSupport.id))
                .from(bookComment)
                .where(BookCommentDynamicSqlSupport.createUserId, isEqualTo(userId))
                .and(BookCommentDynamicSqlSupport.bookId, isEqualTo(comment.getBookId()))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        if (bookCommentMapper.count(selectStatement) > 0) {
            throw new BusinessException(ResponseStatus.HAS_COMMENTS);
        }
        //增加评论
        comment.setCreateUserId(userId);
        comment.setCreateTime(new Date());
        bookCommentMapper.insertSelective(comment);
        //增加书籍评论数
        bookMapper.addCommentCount(comment.getBookId());*/
		
		Wrapper<BookComment> wrapper = new EntityWrapper<BookComment>();
		wrapper.eq("create_user_id", userId);
		wrapper.eq("book_id", comment.getBookId());
		int count = bookCommentService.selectCount(wrapper);
		if(count>0) {
			throw new REDException("您已经评论过！");
		}
		comment.setCreateUserId(userId);
		comment.setCreateTime(new Date());
		bookCommentService.insert(comment);
		bookMapper.addCommentCount(comment.getBookId());
		
	}

	@Override
	public Long getOrCreateAuthorIdByName(String authorName, Byte workDirection) {
		Long authorId;
        /*SelectStatementProvider selectStatement = select(BookAuthorDynamicSqlSupport.id)
                .from(BookAuthorDynamicSqlSupport.bookAuthor)
                .where(BookAuthorDynamicSqlSupport.penName, isEqualTo(authorName))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<BookAuthor> bookAuthors = bookAuthorMapper.selectMany(selectStatement);
        if (bookAuthors.size() > 0) {
            //作者存在
            authorId = bookAuthors.get(0).getId();
        } else {
            //作者不存在，先创建作者
            Date currentDate = new Date();
            authorId = new IdWorker().nextId();
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setId(authorId);
            bookAuthor.setPenName(authorName);
            bookAuthor.setWorkDirection(workDirection);
            bookAuthor.setStatus((byte) 1);
            bookAuthor.setCreateTime(currentDate);
            bookAuthor.setUpdateTime(currentDate);
            bookAuthorMapper.insertSelective(bookAuthor);
        }
        return authorId;*/
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
