package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 批量抓取任务表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("crawl_batch_task")
public class CrawlBatchTask extends BaseEntity {

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
     * 成功抓取数量
     */
	@TableField("crawl_count_success")
	private Integer crawlCountSuccess;
    /**
     * 目标抓取数量
     */
	@TableField("crawl_count_target")
	private Integer crawlCountTarget;
    /**
     * 任务状态，1：正在运行，0已停止
     */
	@TableField("task_status")
	private Integer taskStatus;
    /**
     * 任务开始时间
     */
	@TableField("start_time")
	private Date startTime;
    /**
     * 任务结束时间
     */
	@TableField("end_time")
	private Date endTime;


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

	public Integer getCrawlCountSuccess() {
		return crawlCountSuccess;
	}

	public void setCrawlCountSuccess(Integer crawlCountSuccess) {
		this.crawlCountSuccess = crawlCountSuccess;
	}

	public Integer getCrawlCountTarget() {
		return crawlCountTarget;
	}

	public void setCrawlCountTarget(Integer crawlCountTarget) {
		this.crawlCountTarget = crawlCountTarget;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "CrawlBatchTask{" +
			", id=" + id +
			", sourceId=" + sourceId +
			", crawlCountSuccess=" + crawlCountSuccess +
			", crawlCountTarget=" + crawlCountTarget +
			", taskStatus=" + taskStatus +
			", startTime=" + startTime +
			", endTime=" + endTime +
			"}";
	}
}
