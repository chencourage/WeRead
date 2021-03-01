package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 稿费收入明细统计表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("author_income_detail")
public class AuthorIncomeDetail extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 作家ID
     */
	@TableField("author_id")
	private Long authorId;
    /**
     * 作品ID,0表示全部作品
     */
	@TableField("book_id")
	private Long bookId;
    /**
     * 收入日期
     */
	@TableField("income_date")
	private Date incomeDate;
    /**
     * 订阅总额
     */
	@TableField("income_account")
	private Integer incomeAccount;
    /**
     * 订阅次数
     */
	@TableField("income_count")
	private Integer incomeCount;
    /**
     * 订阅人数
     */
	@TableField("income_number")
	private Integer incomeNumber;
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Date getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
	}

	public Integer getIncomeAccount() {
		return incomeAccount;
	}

	public void setIncomeAccount(Integer incomeAccount) {
		this.incomeAccount = incomeAccount;
	}

	public Integer getIncomeCount() {
		return incomeCount;
	}

	public void setIncomeCount(Integer incomeCount) {
		this.incomeCount = incomeCount;
	}

	public Integer getIncomeNumber() {
		return incomeNumber;
	}

	public void setIncomeNumber(Integer incomeNumber) {
		this.incomeNumber = incomeNumber;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AuthorIncomeDetail{" +
			", id=" + id +
			", userId=" + userId +
			", authorId=" + authorId +
			", bookId=" + bookId +
			", incomeDate=" + incomeDate +
			", incomeAccount=" + incomeAccount +
			", incomeCount=" + incomeCount +
			", incomeNumber=" + incomeNumber +
			", createTime=" + createTime +
			"}";
	}
}
