package com.weread.service.sys.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chenk
 * @since 2020-06-09
 */
@TableName("sys_user")
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编号
     */
	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 系统编码
     */
	@TableField("sys_id")
	private String sysId;
    /**
     * 登录名称
     */
	@TableField("login_name")
	private String loginName;
    /**
     * 密码
     */
	private String password;
    /**
     * 手机号
     */
	private String phone;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 姓名
     */
	private String name;
    /**
     * 昵称
     */
	@TableField("nick_name")
	private String nickName;
    /**
     * 性别
     */
	private String sex;
	private String status;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("vip_status")
	private String vipStatus;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(String vipStatus) {
		this.vipStatus = vipStatus;
	}

	@Override
	public String toString() {
		return "SysUser{" +
			", id=" + id +
			", sysId=" + sysId +
			", loginName=" + loginName +
			", password=" + password +
			", phone=" + phone +
			", email=" + email +
			", name=" + name +
			", nickName=" + nickName +
			", sex=" + sex +
			", status=" + status +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", vipStatus=" + vipStatus +
			"}";
	}
}
