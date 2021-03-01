package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 稿费收入统计表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("author_income")
public class AuthorIncome extends BaseEntity {

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
     * 作品ID
     */
	@TableField("book_id")
	private Long bookId;
    /**
     * 收入月份
     */
	@TableField("income_month")
	private Date incomeMonth;
    /**
     * 税前收入（分）
     */
	@TableField("pre_tax_income")
	private Long preTaxIncome;
    /**
     * 税后收入（分）
     */
	@TableField("after_tax_income")
	private Long afterTaxIncome;
    /**
     * 支付状态，0：待支付，1：已支付
     */
	@TableField("pay_status")
	private Integer payStatus;
    /**
     * 稿费确认状态，0：待确认，1：已确认
     */
	@TableField("confirm_status")
	private Integer confirmStatus;
    /**
     * 详情
     */
	private String detail;
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

	public Date getIncomeMonth() {
		return incomeMonth;
	}

	public void setIncomeMonth(Date incomeMonth) {
		this.incomeMonth = incomeMonth;
	}

	public Long getPreTaxIncome() {
		return preTaxIncome;
	}

	public void setPreTaxIncome(Long preTaxIncome) {
		this.preTaxIncome = preTaxIncome;
	}

	public Long getAfterTaxIncome() {
		return afterTaxIncome;
	}

	public void setAfterTaxIncome(Long afterTaxIncome) {
		this.afterTaxIncome = afterTaxIncome;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "AuthorIncome{" +
			", id=" + id +
			", userId=" + userId +
			", authorId=" + authorId +
			", bookId=" + bookId +
			", incomeMonth=" + incomeMonth +
			", preTaxIncome=" + preTaxIncome +
			", afterTaxIncome=" + afterTaxIncome +
			", payStatus=" + payStatus +
			", confirmStatus=" + confirmStatus +
			", detail=" + detail +
			", createTime=" + createTime +
			"}";
	}
}
