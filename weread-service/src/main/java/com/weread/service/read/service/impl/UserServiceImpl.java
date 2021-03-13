package com.weread.service.read.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.Charsets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.weread.common.base.ResponseStatus;
import com.weread.common.exception.REDException;
import com.weread.common.utils.MD5Util;
import com.weread.common.utils.StringUtil;
import com.weread.service.base.BaseService;
import com.weread.service.read.entity.User;
import com.weread.service.read.entity.UserBookshelf;
import com.weread.service.read.entity.UserBuyRecord;
import com.weread.service.read.entity.UserFeedback;
import com.weread.service.read.entity.UserReadHistory;
import com.weread.service.read.mapper.UserMapper;
import com.weread.service.read.service.IUserBookshelfService;
import com.weread.service.read.service.IUserBuyRecordService;
import com.weread.service.read.service.IUserFeedbackService;
import com.weread.service.read.service.IUserReadHistoryService;
import com.weread.service.read.service.IUserService;
import com.weread.service.read.vo.BookReadHistoryVO;
import com.weread.service.read.vo.BookShelfVO;
import com.weread.service.read.vo.UserFeedbackVO;

import io.jsonwebtoken.lang.Collections;

/**
 * <p>
 * InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class UserServiceImpl extends BaseService<UserMapper, User> implements IUserService {

	@Autowired
	private IUserBookshelfService userBookshelfService;
	
	@Autowired
	private IUserReadHistoryService userReadHistoryService;
	
	@Autowired
	private IUserFeedbackService userFeedbackService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserBuyRecordService userBuyRecordService;
	
	@Override
	public UserDetails register(User user) throws Exception {
		Wrapper<User> userWrapper = new EntityWrapper<User>();
		userWrapper.eq("username", user.getUsername());
		long count = this.selectCount(userWrapper);
		if (count > 0) {
            //用户名已注册
            throw new REDException(ResponseStatus.USERNAME_EXIST.getMsg());
        }
		User entity = new User();
        BeanUtils.copyProperties(user,entity);
        //数据库生成注册记录
        //Long id = new IdWorker().nextId();
        //entity.setId(id);
        entity.setNickName(entity.getUsername());
        Date currentDate = new Date();
        entity.setCreateTime(currentDate);
        entity.setUpdateTime(currentDate);
        entity.setPassword(MD5Util.MD5Encode(entity.getPassword(), Charsets.UTF_8.name()));
        this.insert(entity);
        //生成UserDetail对象并返回
        UserDetails userDetails = new UserDetails();
        userDetails.setId(id);
        userDetails.setUsername(entity.getUsername());
        userDetails.setNickName(entity.getNickName());
        return userDetails;
	}

	@Override
	public UserDetails login(User user) throws Exception {
		Wrapper<User> userWrapper = new EntityWrapper<User>();
		userWrapper.eq("username", user.getUsername());
		userWrapper.eq("password", MD5Util.MD5Encode(user.getPassword(), Charsets.UTF_8.name()));
		List<User> users = this.selectList(userWrapper);
		if (users.size() == 0) {
            throw new REDException(ResponseStatus.USERNAME_PASS_ERROR.getMsg());
        }
		
		//生成UserDetail对象并返回
        UserDetails userDetails = new UserDetails();
        user = users.get(0);
        userDetails.setId(user.getId());
        userDetails.setNickName(user.getNickName());
        userDetails.setUsername(user.getUsername());
        return userDetails;
	}

	@Override
	public Boolean queryIsInShelf(Long userId, Long bookId) {
		Wrapper<UserBookshelf> wrapper = new EntityWrapper<UserBookshelf>();
		wrapper.eq("user_id", userId);
		wrapper.eq("book_id", bookId);
		int count = userBookshelfService.selectCount(wrapper);
		return count>0;
	}

	/**
	 * 添加到书架
	 */
	@Override
	public void addToBookShelf(Long userId, Long bookId, Long preContentId) {
		if (!queryIsInShelf(userId, bookId)) {
            UserBookshelf shelf = new UserBookshelf();
            shelf.setUserId(userId);
            shelf.setBookId(bookId);
            shelf.setPreContentId(preContentId);
            shelf.setCreateTime(new Date());
            userBookshelfService.insert(shelf);
        }
	}

	/**
     * 移出书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * */
	@Override
	public void removeFromBookShelf(Long userId, Long bookId) {
		Wrapper<UserBookshelf> wrapper = new EntityWrapper<UserBookshelf>();
		wrapper.eq("user_id", userId);
		wrapper.eq("book_id", bookId);
		userBookshelfService.delete(wrapper);
	}

	/**
     * 查询书架
     * @param userId 用户ID
     * @param page
     * @param pageSize
     * @return 书架集合
     * */
	@Override
	public List<BookShelfVO> listBookShelfByPage(Long userId, int page, int pageSize) {
		Page pageq = new Page(page,pageSize);
		List<BookShelfVO> bookShelflist = userBookshelfService.listBookShelf(pageq,userId);
		return bookShelflist;
	}

	@Override
	public void addReadHistory(Long userId, Long bookId, Long preContentId) {
		Date currentDate = new Date();
		//删除该书以前的历史记录
		Wrapper<UserReadHistory> wrapper = new EntityWrapper<UserReadHistory>();
		wrapper.eq("user_id", userId);
		wrapper.eq("book_id", bookId);
		userReadHistoryService.delete(wrapper);
		
		//插入该书新的历史记录
        UserReadHistory userReadHistory = new UserReadHistory();
        userReadHistory.setBookId(bookId);
        userReadHistory.setUserId(userId);
        userReadHistory.setPreContentId(preContentId);
        userReadHistory.setCreateTime(currentDate);
        userReadHistory.setUpdateTime(currentDate);
        userReadHistoryService.insert(userReadHistory);
        
        //更新书架的阅读历史
        UserBookshelf userBookshelf = new UserBookshelf();
        userBookshelf.setPreContentId(preContentId);
        userBookshelf.setUpdateTime(currentDate);
        Wrapper<UserBookshelf> shelfwrapper = new EntityWrapper<UserBookshelf>();
        shelfwrapper.eq("user_id", userId);
        shelfwrapper.eq("book_id", bookId);
        userBookshelfService.update(userBookshelf, shelfwrapper);
	}

	/**
     * 添加反馈
     * @param userId 用户id
     * @param content 反馈内容
     * */
	@Override
	public void addFeedBack(Long userId, String content) {
		UserFeedback feedback = new UserFeedback();
        feedback.setUserId(userId);
        feedback.setContent(content);
        feedback.setCreateTime(new Date());
        userFeedbackService.insert(feedback);
	}

	@Override
	public List<UserFeedbackVO> listUserFeedBackByPage(Long userId, int page, int pageSize) {
		Page pageq = new Page(page,pageSize);
		Wrapper<UserFeedback> feedBackWrapper = new EntityWrapper<UserFeedback>();
		feedBackWrapper.eq("user_id", userId);
		Page<UserFeedback> feedBackList = userFeedbackService.selectPage(pageq,feedBackWrapper);
		return BeanUtil.copyList(feedBackList,UserFeedbackVO.class);;
	}

	@Override
	public User userInfo(Long userId) {
		return userService.selectById(userId);
	}

	@Override
	public List<BookReadHistoryVO> listReadHistoryByPage(Long userId, int page, int pageSize) {
		Page pageq = new Page(page,pageSize);
		List<BookReadHistoryVO> historyList = userReadHistoryService.listReadHistory(pageq, userId);
		return historyList;
	}

	@Override
	public void updateUserInfo(Long userId, User user) {
		user.setId(userId);
        user.setUpdateTime(new Date());
        userService.updateById(user);
	}

	@Override
	public void updatePassword(Long userId, String oldPassword, String newPassword) throws Exception {
		User user = userService.selectById(userId);
		if(!StringUtil.equals(MD5Util.MD5Encode(oldPassword, Charsets.UTF_8.name()), user.getPassword())){
			throw new REDException(ResponseStatus.OLD_PASSWORD_ERROR.getMsg());
		}
		user.setPassword(MD5Util.MD5Encode(newPassword, Charsets.UTF_8.name()));
		userService.updateById(user);
	}

	@Override
	public void addAmount(Long userId, int amount) {
		User user = userService.selectById(userId);
		user.setAccountBalance(user.getAccountBalance()+amount);
		userService.updateById(user);
	}

	@Override
	public boolean queryIsBuyBookIndex(Long userId, Long bookIndexId) {
		Wrapper<UserBuyRecord> wrapper = new EntityWrapper<UserBuyRecord>();
		wrapper.eq("user_id", userId);
		wrapper.eq("book_index_id", bookIndexId);
		int count = userBuyRecordService.selectCount(wrapper);
		return count>0;
	}

	@Override
	public void buyBookIndex(Long userId, UserBuyRecord buyRecord) {
		//查询用户余额
		User user = userInfo(userId);
        long balance = user.getAccountBalance();
        if(balance<buyRecord.getBuyAmount()){
            //余额不足
            throw new REDException(ResponseStatus.USER_NO_BALANCE.getMsg());
        }
        buyRecord.setUserId(userId);
        buyRecord.setCreateTime(new Date());
        //生成购买记录
        userBuyRecordService.insert(buyRecord);
        user.setAccountBalance(balance-buyRecord.getBuyAmount());
        userService.updateById(user);
	}

	@Override
	public int queryBuyMember(Long bookId, Date startTime, Date endTime) {
		Wrapper<UserBuyRecord> wrapper = new EntityWrapper<UserBuyRecord>();
		wrapper.eq("book_id", bookId);
		wrapper.ge("create_time", startTime);
		wrapper.le("create_time", endTime);
		int count = userBuyRecordService.selectCount(wrapper);
		return count;
	}

	@Override
	public int queryBuyCount(Long bookId, Date startTime, Date endTime) {
		Wrapper<UserBuyRecord> wrapper = new EntityWrapper<UserBuyRecord>();
		wrapper.eq("book_id", bookId);
		wrapper.ge("create_time", startTime);
		wrapper.le("create_time", endTime);
		List<UserBuyRecord> byRecordList = userBuyRecordService.selectList(wrapper);
		int res = 0;
		if(!Collections.isEmpty(byRecordList)){
			res = byRecordList.size();
		}
		return res;
	}

	@Override
	public int queryBuyAccount(Long bookId, Date startTime, Date endTime) {
		Wrapper<UserBuyRecord> wrapper = new EntityWrapper<UserBuyRecord>();
		wrapper.eq("book_id", bookId);
		wrapper.ge("create_time", startTime);
		wrapper.le("create_time", endTime);
		List<UserBuyRecord> byRecordList = userBuyRecordService.selectList(wrapper);
		int res = 0;
		if(!Collections.isEmpty(byRecordList)){
			res = byRecordList.stream().mapToInt(record -> record.getBuyAmount()).sum();
		}
		return res;
	}

	@Override
	public int queryBuyTotalMember(List<Long> bookIds, Date startTime, Date endTime) {
		Wrapper<UserBuyRecord> wrapper = new EntityWrapper<UserBuyRecord>();
		wrapper.in("book_id", bookIds);
		wrapper.ge("create_time", startTime);
		wrapper.le("create_time", endTime);
		List<UserBuyRecord> byRecordList = userBuyRecordService.selectList(wrapper);
		int res = 0;
		if(!Collections.isEmpty(byRecordList)){
			res = byRecordList.stream().map(UserBuyRecord::getUserId).distinct().collect(Collectors.toList()).size();
		}
		return res;
	}
	
}
