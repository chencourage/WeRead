package com.weread.service.read.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 代码生成表列; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("sys_gen_table_column")
public class SysGenTableColumn extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 表id
     */
	@TableField("table_id")
	private Long tableId;
    /**
     * 列名
     */
	@TableField("column_name")
	private String columnName;
    /**
     * 列排序（升序）
     */
	@TableField("column_sort")
	private BigDecimal columnSort;
    /**
     * 类型
     */
	@TableField("column_type")
	private String columnType;
    /**
     * 列标签名
     */
	@TableField("column_label")
	private String columnLabel;
    /**
     * 列备注说明
     */
	private String comments;
    /**
     * 类的属性名
     */
	@TableField("attr_name")
	private String attrName;
    /**
     * 类的属性类型
     */
	@TableField("attr_type")
	private String attrType;
    /**
     * 是否主键
     */
	@TableField("is_pk")
	private String isPk;
    /**
     * 是否可为空
     */
	@TableField("is_null")
	private String isNull;
    /**
     * 是否插入字段
     */
	@TableField("is_insert")
	private String isInsert;
    /**
     * 是否更新字段
     */
	@TableField("is_update")
	private String isUpdate;
    /**
     * 是否列表字段
     */
	@TableField("is_list")
	private String isList;
    /**
     * 是否查询字段
     */
	@TableField("is_query")
	private String isQuery;
    /**
     * 查询方式
     */
	@TableField("query_type")
	private String queryType;
    /**
     * 是否编辑字段
     */
	@TableField("is_edit")
	private String isEdit;
    /**
     * 表单类型
     */
	@TableField("show_type")
	private String showType;
    /**
     * 其它生成选项
     */
	private String options;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public BigDecimal getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(BigDecimal columnSort) {
		this.columnSort = columnSort;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getAttrType() {
		return attrType;
	}

	public void setAttrType(String attrType) {
		this.attrType = attrType;
	}

	public String getIsPk() {
		return isPk;
	}

	public void setIsPk(String isPk) {
		this.isPk = isPk;
	}

	public String getIsNull() {
		return isNull;
	}

	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}

	public String getIsInsert() {
		return isInsert;
	}

	public void setIsInsert(String isInsert) {
		this.isInsert = isInsert;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getIsList() {
		return isList;
	}

	public void setIsList(String isList) {
		this.isList = isList;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	@Override
	public String toString() {
		return "SysGenTableColumn{" +
			", id=" + id +
			", tableId=" + tableId +
			", columnName=" + columnName +
			", columnSort=" + columnSort +
			", columnType=" + columnType +
			", columnLabel=" + columnLabel +
			", comments=" + comments +
			", attrName=" + attrName +
			", attrType=" + attrType +
			", isPk=" + isPk +
			", isNull=" + isNull +
			", isInsert=" + isInsert +
			", isUpdate=" + isUpdate +
			", isList=" + isList +
			", isQuery=" + isQuery +
			", queryType=" + queryType +
			", isEdit=" + isEdit +
			", showType=" + showType +
			", options=" + options +
			"}";
	}
}
