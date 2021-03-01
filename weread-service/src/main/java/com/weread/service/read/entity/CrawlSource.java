package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 爬虫源表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("crawl_source")
public class CrawlSource extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 源站名
     */
	@TableField("source_name")
	private String sourceName;
    /**
     * 爬取规则（json串）
     */
	@TableField("crawl_rule")
	private String crawlRule;
    /**
     * 爬虫源状态，0：关闭，1：开启
     */
	@TableField("source_status")
	private Integer sourceStatus;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getCrawlRule() {
		return crawlRule;
	}

	public void setCrawlRule(String crawlRule) {
		this.crawlRule = crawlRule;
	}

	public Integer getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(Integer sourceStatus) {
		this.sourceStatus = sourceStatus;
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
		return "CrawlSource{" +
			", id=" + id +
			", sourceName=" + sourceName +
			", crawlRule=" + crawlRule +
			", sourceStatus=" + sourceStatus +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
