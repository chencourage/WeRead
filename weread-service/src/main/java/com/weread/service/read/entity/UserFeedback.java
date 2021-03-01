package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("user_feedback")
public class UserFeedback extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户id
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 反馈内容
     */
	private String content;
    /**
     * 反馈时间
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "UserFeedback{" +
			", id=" + id +
			", userId=" + userId +
			", content=" + content +
			", createTime=" + createTime +
			"}";
	}
}
