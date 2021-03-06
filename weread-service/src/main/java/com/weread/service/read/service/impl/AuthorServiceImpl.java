package com.weread.service.read.service.impl;

import com.weread.service.read.entity.Author;
import com.weread.service.read.entity.AuthorIncome;
import com.weread.service.read.entity.AuthorIncomeDetail;
import com.weread.service.read.mapper.AuthorMapper;
import com.weread.service.read.service.IAuthorService;
import com.weread.service.base.BaseService;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

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

	@Override
	public Boolean checkPenName(String penName) {
		return null;
	}

	@Override
	public String register(Long userId, Author author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isAuthor(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author queryAuthor(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> queryAuthorList(int limit, Date maxAuthorCreateTime) {
		// TODO Auto-generated method stub
		return null;
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
