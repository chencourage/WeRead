package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 作者表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("book_author")
public class BookAuthor extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 邀请码
     */
	@TableField("invite_code")
	private String inviteCode;
    /**
     * 笔名
     */
	@TableField("pen_name")
	private String penName;
    /**
     * 手机号码
     */
	@TableField("tel_phone")
	private String telPhone;
    /**
     * QQ或微信账号
     */
	@TableField("chat_account")
	private String chatAccount;
    /**
     * 电子邮箱
     */
	private String email;
    /**
     * 作品方向，0：男频，1：女频
     */
	@TableField("work_direction")
	private Integer workDirection;
    /**
     * 0：待审核，1：审核通过，正常，2：审核不通过
     */
	private Integer status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 申请人ID
     */
	@TableField("create_user_id")
	private Long createUserId;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 更新人ID
     */
	@TableField("update_user_id")
	private Long updateUserId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getChatAccount() {
		return chatAccount;
	}

	public void setChatAccount(String chatAccount) {
		this.chatAccount = chatAccount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getWorkDirection() {
		return workDirection;
	}

	public void setWorkDirection(Integer workDirection) {
		this.workDirection = workDirection;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(Long updateUserId) {
		this.updateUserId = updateUserId;
	}

	@Override
	public String toString() {
		return "BookAuthor{" +
			", id=" + id +
			", inviteCode=" + inviteCode +
			", penName=" + penName +
			", telPhone=" + telPhone +
			", chatAccount=" + chatAccount +
			", email=" + email +
			", workDirection=" + workDirection +
			", status=" + status +
			", createTime=" + createTime +
			", createUserId=" + createUserId +
			", updateTime=" + updateTime +
			", updateUserId=" + updateUserId +
			"}";
	}
}
