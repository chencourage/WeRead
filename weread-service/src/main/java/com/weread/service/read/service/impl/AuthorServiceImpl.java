package com.weread.service.read.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.weread.service.base.BaseService;
import com.weread.service.read.entity.Author;
import com.weread.service.read.entity.AuthorCode;
import com.weread.service.read.entity.AuthorIncome;
import com.weread.service.read.entity.AuthorIncomeDetail;
import com.weread.service.read.mapper.AuthorMapper;
import com.weread.service.read.service.IAuthorCodeService;
import com.weread.service.read.service.IAuthorService;

/**
 * <p>
 * 作者表; InnoDB free: 6144 kB 服务实现类
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@Service
public class AuthorServiceImpl extends BaseService<AuthorMapper, Author> implements IAuthorService {
	
	@Autowired
	private IAuthorCodeService authorCodeService;

	/**
	 * 判断作者笔名
	 */
	@Override
	public Boolean checkPenName(String penName) {
		EntityWrapper<Author> autherWrapper = new EntityWrapper<Author>();
		autherWrapper.eq("pen_name", penName);
		int res = selectCount(autherWrapper);
		if(res>0) {
			return true;
		}
		return false;
	}

	@Override
	public String register(Long userId, Author author) {
		//判断邀请码是否存在
		Date currentDate = new Date();
		Wrapper<AuthorCode> authorCodeWrapper = new EntityWrapper<AuthorCode>();
		authorCodeWrapper.eq("invite_code", author.getInviteCode());
		authorCodeWrapper.eq("is_use", "0");
		authorCodeWrapper.gt("validity_time", currentDate);
		int countCode = authorCodeService.selectCount(authorCodeWrapper);
		if(countCode>0) {
			//邀请码有效
			author.setUserId(userId);
            author.setCreateTime(currentDate);
            this.insert(author);
            
            //修改邀请码已使用
            AuthorCode authorCode = new AuthorCode();
            authorCode.setIsUse(1);//已使用
            authorCodeWrapper = new EntityWrapper<AuthorCode>();
            authorCodeWrapper.eq("invite_code", author.getInviteCode());
            authorCodeService.update(authorCode, authorCodeWrapper);
            
            return "";
		}else {
			//邀请码无效
			return "邀请码无效！";
		}
	}

	@Override
	public Boolean isAuthor(Long userId) {
		Author author = this.selectById(userId);
		if(null!=author) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Author queryAuthor(Long userId) {
		return this.selectById(userId);
	}

	@Override
	public List<Author> queryAuthorList(int limit, Date maxAuthorCreateTime) {
		EntityWrapper<Author> autherWrapper = new EntityWrapper<Author>();
		autherWrapper.lt("create_time", maxAuthorCreateTime);
		autherWrapper.orderBy("create_time", false);
		autherWrapper.last("limit "+limit);
		return selectList(autherWrapper);
	}

	@Override
	public boolean queryIsStatisticsDaily(Long bookId, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void saveDailyIncomeSta(AuthorIncomeDetail authorIncomeDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean queryIsStatisticsMonth(Long bookId, Date incomeDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long queryTotalAccount(Long userId, Long bookId, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAuthorIncomeSta(AuthorIncome authorIncome) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean queryIsStatisticsDaily(Long authorId, Long bookId, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<AuthorIncomeDetail> listIncomeDailyByPage(int page, int pageSize, Long userId, Long bookId,
			Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AuthorIncome> listIncomeMonthByPage(int page, int pageSize, Long userId, Long bookId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
