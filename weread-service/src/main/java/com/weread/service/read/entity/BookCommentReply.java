package com.weread.service.read.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 小说评论回复表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("book_comment_reply")
public class BookCommentReply extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 评论ID
     */
	@TableField("comment_id")
	private Long commentId;
    /**
     * 回复内容
     */
	@TableField("reply_content")
	private String replyContent;
    /**
     * 审核状态，0：待审核，1：审核通过，2：审核不通过
     */
	@TableField("audit_status")
	private Integer auditStatus;
    /**
     * 回复用户ID
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 回复时间
     */
	@TableField("create_user_id")
	private Long createUserId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
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
		return "BookCommentReply{" +
			", id=" + id +
			", commentId=" + commentId +
			", replyContent=" + replyContent +
			", auditStatus=" + auditStatus +
			", createTime=" + createTime +
			", createUserId=" + createUserId +
			"}";
	}
}
