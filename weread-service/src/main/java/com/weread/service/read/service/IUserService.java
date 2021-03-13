package com.weread.service.read.service;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.weread.service.base.IBaseService;
import com.weread.service.read.entity.User;
import com.weread.service.read.entity.UserBuyRecord;
import com.weread.service.read.vo.BookReadHistoryVO;
import com.weread.service.read.vo.BookShelfVO;
import com.weread.service.read.vo.UserFeedbackVO;

/**
 * <p>
 * InnoDB free: 6144 kB 服务类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public interface IUserService extends IBaseService<User> {
	
	/**
     * 用户注册
     * @param user 用户注册信息类
     * @return jwt载体信息类
     * @throws Exception 
     * */
    UserDetails register(User user) throws Exception;

    /**
     * 用户登陆
     * @param user 用户登陆信息类
     * @return jwt载体信息类
     * @throws Exception 
     * */
    UserDetails login(User user) throws Exception;

    /**
     * 查询小说是否已加入书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * @return true:已加入书架，未加入书架
     * */
    Boolean queryIsInShelf(Long userId, Long bookId);

    /**
     * 加入书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * @param preContentId 阅读的内容ID
     * */
    void addToBookShelf(Long userId, Long bookId, Long preContentId);

    /**
     * 移出书架
     * @param userId 用户ID
     * @param bookId 小说ID
     * */
    void removeFromBookShelf(Long userId, Long bookId);

    /**
     * 查询书架
     * @param userId 用户ID
     * @param page
     * @param pageSize
     * @return 书架集合
     * */
    List<BookShelfVO> listBookShelfByPage(Long userId, int page, int pageSize);

    /**
     * 添加阅读记录
     * @param userId 用户id
     * @param bookId 书籍id
     * @param preContentId 阅读的目录id
     * */
    void addReadHistory(Long userId, Long bookId, Long preContentId);

    /**
     * 添加反馈
     * @param userId 用户id
     * @param content 反馈内容
     * */
    void addFeedBack(Long userId, String content);

    /**
     * 分页查询我的反馈列表
     * @param userId 用户ID
     * @param page 页码
     * @param pageSize 分页大小
     * @return 反馈集合
     * */
    List<UserFeedbackVO> listUserFeedBackByPage(Long userId, int page, int pageSize);

    /**
     * 查询个人信息
     * @param userId 用户id
     * @return 用户信息
     * */
    User userInfo(Long userId);

    /**
     * 分页查询阅读记录
     * @param userId 用户id
     * @param page 页码
     * @param pageSize 分页大小
     * @return
     * */
    List<BookReadHistoryVO> listReadHistoryByPage(Long userId, int page, int pageSize);

    /**
     * 更新个人信息
     * @param userId 用户id
     * @param user 需要更新的信息
     * */
    void updateUserInfo(Long userId, User user);

    /**
     * 更新密码
     * @param userId 用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @throws Exception 
     * */
    void updatePassword(Long userId, String oldPassword, String newPassword) throws Exception;


    /**
     * 增加用户余额
     * @param userId 用户ID
     * @param amount 增加的余额 */
    void addAmount(Long userId, int amount);

    /**
     * 判断用户是否购买过该小说章节
     * @param userId 用户ID
     * @param bookIndexId 章节目录ID
     * @return true:购买过，false:没购买
     * */
    boolean queryIsBuyBookIndex(Long userId, Long bookIndexId);

    /**
     * 购买小说章节
     * @param userId 用户ID
     * @param buyRecord 购买信息
     * */
    void buyBookIndex(Long userId, UserBuyRecord buyRecord);

    /**
     * 查询作品时间段内的订阅人数
     * @param bookId 作品ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订阅人数
     */
    int queryBuyMember(Long bookId, Date startTime, Date endTime);

    /**
     * 查询作品时间段内的订阅次数
     * @param bookId 作品ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订阅次数
     */
    int queryBuyCount(Long bookId, Date startTime, Date endTime);

    /**
     * 查询作品时间段内的订阅总额（屋币）
     * @param bookId 作品ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订阅总额（屋币）
     */
    int queryBuyAccount(Long bookId, Date startTime, Date endTime);

    /**
     * 查询作者时间段内的订阅人数
     * @param bookIds z作者的所有作品ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 订阅人数
     */
    int queryBuyTotalMember(List<Long> bookIds, Date startTime, Date endTime);
	
}
