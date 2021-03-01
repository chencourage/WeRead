package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 小说表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 作品方向，0：男频，1：女频'
     */
	@TableField("work_direction")
	private Integer workDirection;
    /**
     * 分类ID
     */
	@TableField("cat_id")
	private Integer catId;
    /**
     * 分类名
     */
	@TableField("cat_name")
	private String catName;
    /**
     * 小说封面
     */
	@TableField("pic_url")
	private String picUrl;
    /**
     * 小说名
     */
	@TableField("book_name")
	private String bookName;
    /**
     * 作者id
     */
	@TableField("author_id")
	private Long authorId;
    /**
     * 作者名
     */
	@TableField("author_name")
	private String authorName;
    /**
     * 书籍描述
     */
	@TableField("book_desc")
	private String bookDesc;
    /**
     * 评分，预留字段
     */
	private Float score;
    /**
     * 书籍状态，0：连载中，1：已完结
     */
	@TableField("book_status")
	private Integer bookStatus;
    /**
     * 点击量
     */
	@TableField("visit_count")
	private Long visitCount;
    /**
     * 总字数
     */
	@TableField("word_count")
	private Integer wordCount;
    /**
     * 评论数
     */
	@TableField("comment_count")
	private Integer commentCount;
    /**
     * 昨日订阅数
     */
	@TableField("yesterday_buy")
	private Integer yesterdayBuy;
    /**
     * 最新目录ID
     */
	@TableField("last_index_id")
	private Long lastIndexId;
    /**
     * 最新目录名
     */
	@TableField("last_index_name")
	private String lastIndexName;
    /**
     * 最新目录更新时间
     */
	@TableField("last_index_update_time")
	private Date lastIndexUpdateTime;
    /**
     * 是否收费，1：收费，0：免费
     */
	@TableField("is_vip")
	private Integer isVip;
    /**
     * 状态，0：入库，1：上架
     */
	private Integer status;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 爬虫源站ID
     */
	@TableField("crawl_source_id")
	private Integer crawlSourceId;
    /**
     * 抓取的源站小说ID
     */
	@TableField("crawl_book_id")
	private String crawlBookId;
    /**
     * 最后一次的抓取时间
     */
	@TableField("crawl_last_time")
	private Date crawlLastTime;
    /**
     * 是否已停止更新，0：未停止，1：已停止
     */
	@TableField("crawl_is_stop")
	private Integer crawlIsStop;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWorkDirection() {
		return workDirection;
	}

	public void setWorkDirection(Integer workDirection) {
		this.workDirection = workDirection;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Integer getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Integer getYesterdayBuy() {
		return yesterdayBuy;
	}

	public void setYesterdayBuy(Integer yesterdayBuy) {
		this.yesterdayBuy = yesterdayBuy;
	}

	public Long getLastIndexId() {
		return lastIndexId;
	}

	public void setLastIndexId(Long lastIndexId) {
		this.lastIndexId = lastIndexId;
	}

	public String getLastIndexName() {
		return lastIndexName;
	}

	public void setLastIndexName(String lastIndexName) {
		this.lastIndexName = lastIndexName;
	}

	public Date getLastIndexUpdateTime() {
		return lastIndexUpdateTime;
	}

	public void setLastIndexUpdateTime(Date lastIndexUpdateTime) {
		this.lastIndexUpdateTime = lastIndexUpdateTime;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCrawlSourceId() {
		return crawlSourceId;
	}

	public void setCrawlSourceId(Integer crawlSourceId) {
		this.crawlSourceId = crawlSourceId;
	}

	public String getCrawlBookId() {
		return crawlBookId;
	}

	public void setCrawlBookId(String crawlBookId) {
		this.crawlBookId = crawlBookId;
	}

	public Date getCrawlLastTime() {
		return crawlLastTime;
	}

	public void setCrawlLastTime(Date crawlLastTime) {
		this.crawlLastTime = crawlLastTime;
	}

	public Integer getCrawlIsStop() {
		return crawlIsStop;
	}

	public void setCrawlIsStop(Integer crawlIsStop) {
		this.crawlIsStop = crawlIsStop;
	}

	@Override
	public String toString() {
		return "Book{" +
			", id=" + id +
			", workDirection=" + workDirection +
			", catId=" + catId +
			", catName=" + catName +
			", picUrl=" + picUrl +
			", bookName=" + bookName +
			", authorId=" + authorId +
			", authorName=" + authorName +
			", bookDesc=" + bookDesc +
			", score=" + score +
			", bookStatus=" + bookStatus +
			", visitCount=" + visitCount +
			", wordCount=" + wordCount +
			", commentCount=" + commentCount +
			", yesterdayBuy=" + yesterdayBuy +
			", lastIndexId=" + lastIndexId +
			", lastIndexName=" + lastIndexName +
			", lastIndexUpdateTime=" + lastIndexUpdateTime +
			", isVip=" + isVip +
			", status=" + status +
			", updateTime=" + updateTime +
			", createTime=" + createTime +
			", crawlSourceId=" + crawlSourceId +
			", crawlBookId=" + crawlBookId +
			", crawlLastTime=" + crawlLastTime +
			", crawlIsStop=" + crawlIsStop +
			"}";
	}
}
