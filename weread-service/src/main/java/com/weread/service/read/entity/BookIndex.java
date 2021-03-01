package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 小说目录表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("book_index")
public class BookIndex extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 小说ID
     */
	@TableField("book_id")
	private Long bookId;
    /**
     * 目录号
     */
	@TableField("index_num")
	private Integer indexNum;
    /**
     * 目录名
     */
	@TableField("index_name")
	private String indexName;
    /**
     * 字数
     */
	@TableField("word_count")
	private Integer wordCount;
    /**
     * 是否收费，1：收费，0：免费
     */
	@TableField("is_vip")
	private Integer isVip;
    /**
     * 章节费用（屋币）
     */
	@TableField("book_price")
	private Integer bookPrice;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public Integer getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(Integer indexNum) {
		this.indexNum = indexNum;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Integer bookPrice) {
		this.bookPrice = bookPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "BookIndex{" +
			", id=" + id +
			", bookId=" + bookId +
			", indexNum=" + indexNum +
			", indexName=" + indexName +
			", wordCount=" + wordCount +
			", isVip=" + isVip +
			", bookPrice=" + bookPrice +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
