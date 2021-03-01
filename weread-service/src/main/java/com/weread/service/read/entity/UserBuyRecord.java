package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 用户消费记录表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("user_buy_record")
public class UserBuyRecord extends BaseEntity {

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
     * 购买的小说ID
     */
	@TableField("book_id")
	private Long bookId;
    /**
     * 购买的小说名
     */
	@TableField("book_name")
	private String bookName;
    /**
     * 购买的章节ID
     */
	@TableField("book_index_id")
	private Long bookIndexId;
    /**
     * 购买的章节名
     */
	@TableField("book_index_name")
	private String bookIndexName;
    /**
     * 购买使用的屋币数量
     */
	@TableField("buy_amount")
	private Integer buyAmount;
    /**
     * 购买时间
     */
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

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getBookIndexId() {
		return bookIndexId;
	}

	public void setBookIndexId(Long bookIndexId) {
		this.bookIndexId = bookIndexId;
	}

	public String getBookIndexName() {
		return bookIndexName;
	}

	public void setBookIndexName(String bookIndexName) {
		this.bookIndexName = bookIndexName;
	}

	public Integer getBuyAmount() {
		return buyAmount;
	}

	public void setBuyAmount(Integer buyAmount) {
		this.buyAmount = buyAmount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserBuyRecord{" +
			", id=" + id +
			", userId=" + userId +
			", bookId=" + bookId +
			", bookName=" + bookName +
			", bookIndexId=" + bookIndexId +
			", bookIndexName=" + bookIndexName +
			", buyAmount=" + buyAmount +
			", createTime=" + createTime +
			"}";
	}
}
