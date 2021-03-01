package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 小说评论表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("book_comment")
public class BookComment extends BaseEntity {

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
     * 评价内容
     */
	@TableField("comment_content")
	private String commentContent;
    /**
     * 回复数量
     */
	@TableField("reply_count")
	private Integer replyCount;
    /**
     * 审核状态，0：待审核，1：审核通过，2：审核不通过
     */
	@TableField("audit_status")
	private Integer auditStatus;
    /**
     * 评价时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 评价人
     */
	@TableField("create_user_id")
	private Long createUserId;


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

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	@Override
	public String toString() {
		return "BookComment{" +
			", id=" + id +
			", bookId=" + bookId +
			", commentContent=" + commentContent +
			", replyCount=" + replyCount +
			", auditStatus=" + auditStatus +
			", createTime=" + createTime +
			", createUserId=" + createUserId +
			"}";
	}
}
