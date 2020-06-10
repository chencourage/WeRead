package com.weread.service.base;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

public abstract class DataEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@TableField(value = "remarks")
	protected String remarks; // 备注
	@TableField(value = "create_by", el = "createBy.id", fill = FieldFill.INSERT)
	//protected SysUser createBy; // 创建者
	//@TableField(value = "create_date", fill = FieldFill.INSERT)
	protected Date createDate; // 创建日期
	//@TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.UPDATE)
	//protected SysUser updateBy; // 更新者
	@TableField(value = "update_date", fill = FieldFill.UPDATE)
	protected Date updateDate; // 更新日期

	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
//	public SysUser getCreateBy() {
//		return createBy;
//	}
//	public void setCreateBy(SysUser createBy) {
//		this.createBy = createBy;
//	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
//	public SysUser getUpdateBy() {
//		return updateBy;
//	}
//	public void setUpdateBy(SysUser updateBy) {
//		this.updateBy = updateBy;
//	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
