package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 数据权限管理; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("sys_data_perm")
public class SysDataPerm extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 权限名称
     */
	private String name;
    /**
     * 数据表名称
     */
	@TableField("table_name")
	private String tableName;
    /**
     * 所属模块
     */
	@TableField("module_name")
	private String moduleName;
    /**
     * 用户权限控制属性名
     */
	@TableField("crl_attr_name")
	private String crlAttrName;
    /**
     * 数据表权限控制列名
     */
	@TableField("crl_column_name")
	private String crlColumnName;
    /**
     * 权限code，all_开头表示查看所有数据的权限，sup_开头表示查看下级数据的权限，own_开头表示查看本级数据的权限
     */
	@TableField("perm_code")
	private String permCode;
    /**
     * 排序
     */
	@TableField("order_num")
	private Integer orderNum;
    /**
     * 创建时间
     */
	@TableField("gmt_create")
	private Date gmtCreate;
    /**
     * 修改时间
     */
	@TableField("gmt_modified")
	private Date gmtModified;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getCrlAttrName() {
		return crlAttrName;
	}

	public void setCrlAttrName(String crlAttrName) {
		this.crlAttrName = crlAttrName;
	}

	public String getCrlColumnName() {
		return crlColumnName;
	}

	public void setCrlColumnName(String crlColumnName) {
		this.crlColumnName = crlColumnName;
	}

	public String getPermCode() {
		return permCode;
	}

	public void setPermCode(String permCode) {
		this.permCode = permCode;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	@Override
	public String toString() {
		return "SysDataPerm{" +
			", id=" + id +
			", name=" + name +
			", tableName=" + tableName +
			", moduleName=" + moduleName +
			", crlAttrName=" + crlAttrName +
			", crlColumnName=" + crlColumnName +
			", permCode=" + permCode +
			", orderNum=" + orderNum +
			", gmtCreate=" + gmtCreate +
			", gmtModified=" + gmtModified +
			"}";
	}
}
