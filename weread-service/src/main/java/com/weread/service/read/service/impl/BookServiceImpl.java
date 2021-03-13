package com.weread.service.read.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.weread.common.base.ResponseStatus;
import com.weread.common.base.SystemConfig;
import com.weread.common.exception.REDException;
import com.weread.common.redis.IRedisService;
import com.weread.common.utils.BeanUtil;
import com.weread.common.utils.JsonUtil;
import com.weread.common.utils.StringUtil;
import com.weread.service.base.BaseService;
import com.weread.service.base.req.book.BookSP;
import com.weread.service.read.entity.Author;
import com.weread.service.read.entity.Book;
import com.weread.service.read.entity.BookAuthor;
import com.weread.service.read.entity.BookCategory;
import com.weread.service.read.entity.BookComment;
import com.weread.service.read.entity.BookContent;
import com.weread.service.read.entity.BookIndex;
import com.weread.service.read.entity.BookSetting;
import com.weread.service.read.mapper.BookMapper;
import com.weread.service.read.mapper.BookSettingMapper;
import com.weread.service.read.service.FileService;
import com.weread.service.read.service.IAuthorService;
import com.weread.service.read.service.IBookAuthorService;
import com.weread.service.read.service.IBookCategoryService;
import com.weread.service.read.service.IBookCommentService;
import com.weread.service.read.service.IBookContentService;
import com.weread.service.read.service.IBookIndexService;
import com.weread.service.read.service.IBookService;
import com.weread.service.read.service.IBookSettingService;
import com.weread.service.read.vo.BookCommentVO;
import com.weread.service.read.vo.BookSettingVO;
import com.weread.service.read.vo.BookVO;

import io.jsonwebtoken.lang.Collections;

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
	
	@Autowired
	private IBookAuthorService bookAuthorService;
	
	@Autowired
	private IAuthorService authorService;
	
	@Autowired
	private FileService fileService;
	
	@Value("")
	private String picSavePath;
	
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
		return bookCommentService.listCommentByPage(userId, bookId);
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
			throw new REDException(ResponseStatus.HAS_COMMENTS.getMsg());
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
		Wrapper<BookAuthor> wrapper = new EntityWrapper<BookAuthor>();
		wrapper.eq("pen_name", authorName);
		List<BookAuthor> bookAuthors = bookAuthorService.selectList(wrapper);
		if (bookAuthors.size() > 0) {
            //作者存在
            authorId = bookAuthors.get(0).getId();
        } else {
            //作者不存在，先创建作者
            Date currentDate = new Date();
            BookAuthor bookAuthor = new BookAuthor();
            bookAuthor.setPenName(authorName);
            bookAuthor.setWorkDirection(new Integer(workDirection));
            bookAuthor.setStatus(new Integer(1));
            bookAuthor.setCreateTime(currentDate);
            bookAuthor.setUpdateTime(currentDate);
            bookAuthorService.insert(bookAuthor);
            authorId = bookAuthor.getId();
        }
        return authorId;
	}

	@Override
	public Long queryIdByNameAndAuthor(String bookName, String author) {
		//查询小说ID
        /*SelectStatementProvider selectStatement = select(id)
                .from(book)
                .where(BookDynamicSqlSupport.bookName, isEqualTo(bookName))
                .and(BookDynamicSqlSupport.authorName, isEqualTo(authorName))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        List<Book> books = bookMapper.selectMany(selectStatement);
        if (books.size() > 0) {
            return books.get(0).getId();
        }
        return null;*/
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.eq("book_name", bookName);
		wrapper.eq("author_name", author);
		List<Book> books = this.selectList(wrapper);
		if (books.size() > 0) {
            return books.get(0).getId();
        }
        return null;
	}

	@Override
	public List<Integer> queryIndexNumByBookId(Long bookId) {
		/*SelectStatementProvider selectStatement = select(BookIndexDynamicSqlSupport.indexNum)
                .from(BookIndexDynamicSqlSupport.bookIndex)
                .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                .build()
                .render(RenderingStrategies.MYBATIS3);

        return bookIndexMapper.selectMany(selectStatement).stream().map(BookIndex::getIndexNum).collect(Collectors.toList());*/
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
		wrapper.eq("book_id", bookId);
		List<BookIndex> indexList = bookIndexService.selectList(wrapper);
		if(!Collections.isEmpty(indexList)){
			return indexList.stream().map(BookIndex::getIndexNum).collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public List<Book> queryNetworkPicBooks(String localPicPrefix, Integer limit) {
		return bookMapper.queryNetworkPicBooks(localPicPrefix,limit);
	}

	@Override
	public void updateBookPicToLocal(String picUrl, Long bookId) {
		
		/*picUrl = fileService.transFile(picUrl, picSavePath);

        bookMapper.update(update(book)
                .set(BookDynamicSqlSupport.picUrl)
                .equalTo(picUrl)
                .set(updateTime)
                .equalTo(new Date())
                .where(id, isEqualTo(bookId))
                .build()
                .render(RenderingStrategies.MYBATIS3));*/
		
		picUrl = fileService.transFile(picUrl, picSavePath);
		Book book = new Book();
		book.setPicUrl(picUrl);
		book.setUpdateTime(new Date());
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.eq("id", bookId);
		this.update(book, wrapper);
	}

	@Override
	public List<Book> listBookPageByUserId(Long userId, int page, int pageSize) {
		/*PageHelper.startPage(page, pageSize);

        SelectStatementProvider selectStatement = select(id, bookName, picUrl, catName, visitCount, yesterdayBuy, lastIndexUpdateTime, updateTime, wordCount, lastIndexName, status)
                .from(book)
                .where(authorId, isEqualTo(authorService.queryAuthor(userId).getId()))
                .orderBy(BookDynamicSqlSupport.createTime.descending())
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return bookMapper.selectMany(selectStatement);*/
		Page<Book> pageq = new Page<Book>(page,pageSize);
		Author author = authorService.queryAuthor(userId);
		
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.eq("author_id", author.getId());
		wrapper.orderBy("create_time", false);
		this.selectPage(pageq,wrapper);
		return pageq.getRecords();
	}

	@Override
	public void addBook(Book book, Long authorId, String penName) {
		//判断小说名是否存在
        /*if (queryIdByNameAndAuthor(book.getBookName(), penName) != null) {
            //该作者发布过此书名的小说
            throw new BusinessException(ResponseStatus.BOOKNAME_EXISTS);
        }
        ;
        book.setAuthorName(penName);
        book.setAuthorId(authorId);
        book.setVisitCount(0L);
        book.setWordCount(0);
        book.setScore(6.5f);
        book.setLastIndexName("");
        book.setCreateTime(new Date());
        book.setUpdateTime(book.getCreateTime());
        bookMapper.insertSelective(book);*/
		Long id = this.queryIdByNameAndAuthor(book.getBookName(), penName);
		if(null!=id){
			//该作者发布过此书名的小说
            throw new REDException(ResponseStatus.BOOKNAME_EXISTS.getMsg());
		}
		book.setAuthorName(penName);
        book.setAuthorId(authorId);
        book.setVisitCount(0L);
        book.setWordCount(0);
        book.setScore(6.5f);
        book.setLastIndexName("");
        book.setCreateTime(new Date());
        book.setUpdateTime(book.getCreateTime());
        this.insert(book);
	}

	@Override
	public void updateBookStatus(Long bookId, Byte status, Long authorId) {
		/*bookMapper.update(update(book)
                .set(BookDynamicSqlSupport.status)
                .equalTo(status)
                .where(id, isEqualTo(bookId))
                .and(BookDynamicSqlSupport.authorId, isEqualTo(authorId))
                .build()
                .render(RenderingStrategies.MYBATIS3));*/
		
		Book book = new Book();
		book.setStatus(new Integer(status));
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.eq("id", bookId);
		wrapper.eq("author_id", authorId);
		this.update(book, wrapper);
		
	}

	@Override
	public void addBookContent(Long bookId, String indexName, String content, Byte isVip, Long authorId) {
		/*Book book = queryBookDetail(bookId);
        if (!authorId.equals(book.getAuthorId())) {
            //并不是更新自己的小说
            return;
        }
        Long lastIndexId = new IdWorker().nextId();
        Date currentDate = new Date();
        int wordCount = StringUtil.getStrValidWordCount(content);

        //更新小说主表信息
        bookMapper.update(update(BookDynamicSqlSupport.book)
                .set(BookDynamicSqlSupport.lastIndexId)
                .equalTo(lastIndexId)
                .set(BookDynamicSqlSupport.lastIndexName)
                .equalTo(indexName)
                .set(BookDynamicSqlSupport.lastIndexUpdateTime)
                .equalTo(currentDate)
                .set(BookDynamicSqlSupport.wordCount)
                .equalTo(book.getWordCount() + wordCount)
                .where(id, isEqualTo(bookId))
                .and(BookDynamicSqlSupport.authorId, isEqualTo(authorId))
                .build()
                .render(RenderingStrategies.MYBATIS3));

        //计算价格
        int bookPrice = new BigDecimal(wordCount).divide(bookPriceConfig.getWordCount()).multiply(bookPriceConfig.getValue()).intValue();

        //更新小说目录表
        int indexNum = 0;
        if (book.getLastIndexId() != null) {
            indexNum = queryBookIndex(book.getLastIndexId()).getIndexNum() + 1;
        }
        BookIndex lastBookIndex = new BookIndex();
        lastBookIndex.setId(lastIndexId);
        lastBookIndex.setWordCount(wordCount);
        lastBookIndex.setIndexName(indexName);
        lastBookIndex.setIndexNum(indexNum);
        lastBookIndex.setBookId(bookId);
        lastBookIndex.setIsVip(isVip);
        lastBookIndex.setBookPrice(bookPrice);
        lastBookIndex.setCreateTime(currentDate);
        lastBookIndex.setUpdateTime(currentDate);
        bookIndexMapper.insertSelective(lastBookIndex);

        //更新小说内容表
        BookContent bookContent = new BookContent();
        bookContent.setIndexId(lastIndexId);
        bookContent.setContent(content);
        bookContentMapper.insertSelective(bookContent);*/
		Book book = queryBookDetail(bookId);
		if (!authorId.equals(book.getAuthorId())) {
            //并不是更新自己的小说
            return;
        }
		//Long lastIndexId = new IdWorker().nextId();
        Date currentDate = new Date();
        int wordCount = content.length();//StringUtil.getStrValidWordCount(content);
        
        //int bookPrice = new BigDecimal(wordCount).divide(bookPriceConfig.getWordCount()).multiply(bookPriceConfig.getValue()).intValue();
        int bookPrice = 0;
        int indexNum = queryBookIndex(book.getLastIndexId()).getIndexNum() + 1;
        BookIndex lastBookIndex = new BookIndex();
        //lastBookIndex.setId(lastIndexId);
        lastBookIndex.setWordCount(wordCount);
        lastBookIndex.setIndexName(indexName);
        lastBookIndex.setIndexNum(indexNum);
        lastBookIndex.setBookId(bookId);
        lastBookIndex.setIsVip(new Integer(isVip));
        lastBookIndex.setBookPrice(bookPrice);
        lastBookIndex.setCreateTime(currentDate);
        lastBookIndex.setUpdateTime(currentDate);
        bookIndexService.insert(lastBookIndex);
        
        Long lastIndexId = lastBookIndex.getId();
        Book upBook = new Book();
        upBook.setLastIndexId(lastIndexId);
        upBook.setLastIndexName(indexName);
        upBook.setLastIndexUpdateTime(currentDate);
        upBook.setWordCount(book.getWordCount()+wordCount);
        Wrapper<Book> wrapper = new EntityWrapper<Book>();
        wrapper.eq("id", bookId);
        wrapper.eq("author_id", authorId);
        this.update(upBook, wrapper);
        
        BookContent bookContent = new BookContent();
        bookContent.setIndexId(lastIndexId);
        bookContent.setContent(content);
        bookContentService.insert(bookContent);
	}

	@Override
	public List<Book> queryBookByUpdateTimeByPage(Date startDate, int limit) {
		/*return bookMapper.selectMany(select(book.allColumns())
                .from(book)
                .where(updateTime, isGreaterThan(startDate))
                .and(lastIndexId,isNotNull())
                .orderBy(updateTime)
                .limit(limit)
                .build()
                .render(RenderingStrategies.MYBATIS3));*/
		
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.gt("update_time", startDate);
		wrapper.isNotNull("last_index_id");
		wrapper.orderBy("update_time");
		wrapper.last("limit "+limit);
		
		return this.selectList(wrapper);
	}

	@Override
	public List<Book> queryBookList(Long authorId) {
		/*return bookMapper.selectMany(select(id, bookName)
                .from(book)
                .where(BookDynamicSqlSupport.authorId,isEqualTo(authorId))
                .build()
                .render(RenderingStrategies.MYBATIS3));*/
		
		Wrapper<Book> wrapper = new EntityWrapper<Book>();
		wrapper.eq("author_id", authorId);
		return this.selectList(wrapper);
	}

	@Override
	public void deleteIndex(Long indexId, Long authorId) {
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
		wrapper.eq("id", indexId);
		List<BookIndex> bookIndices = bookIndexService.selectList(wrapper);
		if(bookIndices.size()>0){
			BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            Book book = this.selectById(bookId);
            int wordCount = book.getWordCount();
            //作者ID相同，表明该小说是登录用户发布，可以删除
            if (authorId.equals(book.getAuthorId())) {
            	bookIndexService.deleteById(indexId);
            	
            	Wrapper<BookContent> bookwrapper = new EntityWrapper<BookContent>();
            	bookwrapper.eq("index_id", indexId);
            	bookContentService.delete(bookwrapper);
            	
            	//更新总字数
                wordCount = wordCount - bookIndex.getWordCount();
                //更新最新章节
                Long lastIndexId = null;
                String lastIndexName = null;
                Date lastIndexUpdateTime = null;
                
                Wrapper<BookIndex> indexwrapper = new EntityWrapper<BookIndex>();
                indexwrapper.eq("book_id", bookId);
                indexwrapper.orderBy("index_num", false);
                indexwrapper.last("limit 1");
                List<BookIndex> lastBookIndices = bookIndexService.selectList(indexwrapper);
                if (lastBookIndices.size() > 0) {
                    BookIndex lastBookIndex = lastBookIndices.get(0);
                    lastIndexId = lastBookIndex.getId();
                    lastIndexName = lastBookIndex.getIndexName();
                    lastIndexUpdateTime = lastBookIndex.getCreateTime();

                    Book upbook = new Book();
                    upbook.setWordCount(wordCount);
                    upbook.setUpdateTime(new Date());
                    upbook.setLastIndexId(lastIndexId);
                    upbook.setLastIndexName(lastIndexName);
                    upbook.setLastIndexUpdateTime(lastIndexUpdateTime);
                    
                    Wrapper<Book> upbookwrapper = new EntityWrapper<Book>();
                    upbookwrapper.eq("id", bookId);
                    this.update(upbook, upbookwrapper);
                }
            }
		}
		//查询小说章节表信息
        /*List<BookIndex> bookIndices = bookIndexMapper.selectMany(
                select(BookIndexDynamicSqlSupport.bookId, BookIndexDynamicSqlSupport.wordCount)
                        .from(bookIndex)
                        .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId)).build().render(RenderingStrategy.MYBATIS3));
        if (bookIndices.size() > 0) {
            BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            //查询小说表信息
            List<Book> books = bookMapper.selectMany(
                    select(wordCount, BookDynamicSqlSupport.authorId)
                            .from(book)
                            .where(id, isEqualTo(bookId))
                            .build()
                            .render(RenderingStrategy.MYBATIS3));
            if (books.size() > 0) {
                Book book = books.get(0);
                int wordCount = book.getWordCount();
                //作者ID相同，表明该小说是登录用户发布，可以删除
                if (book.getAuthorId().equals(authorId)) {
                    //删除目录表和内容表记录
                    bookIndexMapper.deleteByPrimaryKey(indexId);
                    bookContentMapper.delete(deleteFrom(bookContent).where(BookContentDynamicSqlSupport.indexId, isEqualTo(indexId)).build()
                            .render(RenderingStrategies.MYBATIS3));
                    //更新总字数
                    wordCount = wordCount - bookIndex.getWordCount();
                    //更新最新章节
                    Long lastIndexId = null;
                    String lastIndexName = null;
                    Date lastIndexUpdateTime = null;
                    List<BookIndex> lastBookIndices = bookIndexMapper.selectMany(
                            select(BookIndexDynamicSqlSupport.id, BookIndexDynamicSqlSupport.indexName, BookIndexDynamicSqlSupport.createTime)
                                    .from(BookIndexDynamicSqlSupport.bookIndex)
                                    .where(BookIndexDynamicSqlSupport.bookId, isEqualTo(bookId))
                                    .orderBy(BookIndexDynamicSqlSupport.indexNum.descending())
                                    .limit(1)
                                    .build()
                                    .render(RenderingStrategy.MYBATIS3));
                    if (lastBookIndices.size() > 0) {
                        BookIndex lastBookIndex = lastBookIndices.get(0);
                        lastIndexId = lastBookIndex.getId();
                        lastIndexName = lastBookIndex.getIndexName();
                        lastIndexUpdateTime = lastBookIndex.getCreateTime();

                    }
                    //更新小说主表信息
                    bookMapper.update(update(BookDynamicSqlSupport.book)
                            .set(BookDynamicSqlSupport.wordCount)
                            .equalTo(wordCount)
                            .set(updateTime)
                            .equalTo(new Date())
                            .set(BookDynamicSqlSupport.lastIndexId)
                            .equalTo(lastIndexId)
                            .set(BookDynamicSqlSupport.lastIndexName)
                            .equalTo(lastIndexName)
                            .set(BookDynamicSqlSupport.lastIndexUpdateTime)
                            .equalTo(lastIndexUpdateTime)
                            .where(id, isEqualTo(bookId))
                            .build()
                            .render(RenderingStrategies.MYBATIS3));


                }
            }


        }*/
		
	}

	@Override
	public void updateIndexName(Long indexId, String indexName, Long authorId) {
		//查询小说章节表信息
		Wrapper<BookIndex> wrapper = new EntityWrapper<BookIndex>();
		wrapper.eq("id", indexId);
		List<BookIndex> bookIndices = bookIndexService.selectList(wrapper);
		
		if (bookIndices.size() > 0) {
            BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            //查询小说表信息
            Book book = this.selectById(bookId);
            //作者ID相同，表明该小说是登录用户发布，可以修改
            if (book.getAuthorId().equals(authorId)) {
            	BookIndex upIndex = new BookIndex();
            	upIndex.setIndexName(indexName);
            	upIndex.setUpdateTime(new Date());
            	bookIndexService.update(upIndex, wrapper);
            }
		}
        /*List<BookIndex> bookIndices = bookIndexMapper.selectMany(
                select(BookIndexDynamicSqlSupport.bookId, BookIndexDynamicSqlSupport.wordCount)
                        .from(bookIndex)
                        .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId)).build().render(RenderingStrategy.MYBATIS3));
        if (bookIndices.size() > 0) {
            BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            //查询小说表信息
            List<Book> books = bookMapper.selectMany(
                    select(wordCount, BookDynamicSqlSupport.authorId)
                            .from(book)
                            .where(id, isEqualTo(bookId))
                            .build()
                            .render(RenderingStrategy.MYBATIS3));
            if (books.size() > 0) {
                Book book = books.get(0);
                //作者ID相同，表明该小说是登录用户发布，可以修改
                if (book.getAuthorId().equals(authorId)) {

                    bookIndexMapper.update(
                            update(BookIndexDynamicSqlSupport.bookIndex)
                                    .set(BookIndexDynamicSqlSupport.indexName)
                                    .equalTo(indexName)
                                    .set(BookIndexDynamicSqlSupport.updateTime)
                                    .equalTo(new Date())
                                    .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId))
                                    .build()
                                    .render(RenderingStrategy.MYBATIS3));


                }
            }


        }*/
		
	}

	@Override
	public String queryIndexContent(Long indexId, Long authorId) {
		//查询小说章节表信息
		BookIndex bookIndex = bookIndexService.selectById(indexId);
		Long bookId = bookIndex.getBookId();
		Book book = this.selectById(bookId);
		//作者ID相同，表明该小说是登录用户发布
        if (book.getAuthorId().equals(authorId)) {
        	
        	Wrapper<BookContent> contenwrapper = new EntityWrapper<BookContent>();
        	contenwrapper.eq("index_id", indexId);
        	contenwrapper.last("limit 1");
        	List<BookContent> contentList = bookContentService.selectList(contenwrapper);
        	if(!Collections.isEmpty(contentList)){
        		return contentList.get(0).getContent();
        	}
        }
        /*List<BookIndex> bookIndices = bookIndexMapper.selectMany(
                select(BookIndexDynamicSqlSupport.bookId, BookIndexDynamicSqlSupport.wordCount)
                        .from(bookIndex)
                        .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId)).build().render(RenderingStrategy.MYBATIS3));
        if (bookIndices.size() > 0) {
            BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            //查询小说表信息
            List<Book> books = bookMapper.selectMany(
                    select(wordCount, BookDynamicSqlSupport.authorId)
                            .from(book)
                            .where(id, isEqualTo(bookId))
                            .build()
                            .render(RenderingStrategy.MYBATIS3));
            if (books.size() > 0) {
                Book book = books.get(0);
                //作者ID相同，表明该小说是登录用户发布
                if (book.getAuthorId().equals(authorId)) {
                    return bookContentMapper.selectMany(
                            select(content)
                                    .from(bookContent)
                                    .where(BookContentDynamicSqlSupport.indexId, isEqualTo(indexId))
                                    .limit(1)
                                    .build().render(RenderingStrategy.MYBATIS3))
                            .get(0).getContent();
                }

            }
        }*/
        return "";
	}

	@Override
	public void updateBookContent(Long indexId, String indexName, String content, Long authorId) {
		//查询小说章节表信息
		BookIndex bookIndex = bookIndexService.selectById(indexId);
		Long bookId = bookIndex.getBookId();
		Book book = this.selectById(bookId);
		
		//作者ID相同，表明该小说是登录用户发布，可以修改
        if (book.getAuthorId().equals(authorId)) {
        	Date currentDate = new Date();
            int wordCount = StringUtil.getStrValidWordCount(content);

            //计算价格
            //int bookPrice = new BigDecimal(wordCount).divide(bookPriceConfig.getWordCount()).multiply(bookPriceConfig.getValue()).intValue();
            int bookPrice = 0;
            bookIndex.setIndexName(indexName);
            bookIndex.setWordCount(wordCount);
            bookIndex.setBookPrice(bookPrice);
            bookIndex.setUpdateTime(currentDate);
            bookIndexService.updateById(bookIndex);
            
            BookContent bookContent = new BookContent();
            bookContent.setContent(content);
            Wrapper<BookContent> wrapper = new EntityWrapper<BookContent>();
            wrapper.eq("index_id", indexId);
            bookContentService.update(bookContent, wrapper);
        }
		
        /*List<BookIndex> bookIndices = bookIndexMapper.selectMany(
                select(BookIndexDynamicSqlSupport.bookId, BookIndexDynamicSqlSupport.wordCount)
                        .from(bookIndex)
                        .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId)).build().render(RenderingStrategy.MYBATIS3));
        if (bookIndices.size() > 0) {
            BookIndex bookIndex = bookIndices.get(0);
            //获取小说ID
            Long bookId = bookIndex.getBookId();
            //查询小说表信息
            List<Book> books = bookMapper.selectMany(
                    select(wordCount, BookDynamicSqlSupport.authorId)
                            .from(book)
                            .where(id, isEqualTo(bookId))
                            .build()
                            .render(RenderingStrategy.MYBATIS3));
            if (books.size() > 0) {
                Book book = books.get(0);
                //作者ID相同，表明该小说是登录用户发布，可以修改
                if (book.getAuthorId().equals(authorId)) {
                    Date currentDate = new Date();
                    int wordCount = StringUtil.getStrValidWordCount(content);

                    //计算价格
                    int bookPrice = new BigDecimal(wordCount).divide(bookPriceConfig.getWordCount()).multiply(bookPriceConfig.getValue()).intValue();


                    //更新小说目录表
                    int update = bookIndexMapper.update(
                            update(BookIndexDynamicSqlSupport.bookIndex)
                                    .set(BookIndexDynamicSqlSupport.indexName)
                                    .equalTo(indexName)
                                    .set(BookIndexDynamicSqlSupport.wordCount)
                                    .equalTo(wordCount)
                                    .set(BookIndexDynamicSqlSupport.bookPrice)
                                    .equalTo(bookPrice)
                                    .set(BookIndexDynamicSqlSupport.updateTime)
                                    .equalTo(currentDate)
                                    .where(BookIndexDynamicSqlSupport.id, isEqualTo(indexId))
                                    .build().render(RenderingStrategy.MYBATIS3));

                    //更新小说内容表
                    bookContentMapper.update(
                            update(BookContentDynamicSqlSupport.bookContent)
                                    .set(BookContentDynamicSqlSupport.content)
                                    .equalTo(content)
                                    .where(BookContentDynamicSqlSupport.indexId, isEqualTo(indexId))
                                    .build().render(RenderingStrategy.MYBATIS3));

                }
            }

        }*/
		
	}
	
}
