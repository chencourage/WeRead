package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 抓取单本小说任务表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("crawl_single_task")
public class CrawlSingleTask extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 爬虫源ID
     */
	@TableField("source_id")
	private Integer sourceId;
    /**
     * 爬虫源名
     */
	@TableField("source_name")
	private String sourceName;
    /**
     * 源站小说ID
     */
	@TableField("source_book_id")
	private String sourceBookId;
    /**
     * 分类ID
     */
	@TableField("cat_id")
	private Integer catId;
    /**
     * 爬取的小说名
     */
	@TableField("book_name")
	private String bookName;
    /**
     * 爬取的小说作者名
     */
	@TableField("author_name")
	private String authorName;
    /**
     * 任务状态，0：失败，1：成功，2；未执行
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 已经执行次数，最多执行5次
     */
	@TableField("exc_count")
	private Integer excCount;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceBookId() {
		return sourceBookId;
	}

	public void setSourceBookId(String sourceBookId) {
		this.sourceBookId = sourceBookId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getExcCount() {
		return excCount;
	}

	public void setExcCount(Integer excCount) {
		this.excCount = excCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "CrawlSingleTask{" +
			", id=" + id +
			", sourceId=" + sourceId +
			", sourceName=" + sourceName +
			", sourceBookId=" + sourceBookId +
			", catId=" + catId +
			", bookName=" + bookName +
			", authorName=" + authorName +
			", taskStatus=" + taskStatus +
			", excCount=" + excCount +
			", createTime=" + createTime +
			"}";
	}
}
