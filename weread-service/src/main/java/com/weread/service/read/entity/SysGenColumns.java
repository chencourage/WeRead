package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("sys_gen_columns")
public class SysGenColumns extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 表名
     */
	@TableField("table_name")
	private String tableName;
    /**
     * 列名
     */
	@TableField("column_name")
	private String columnName;
    /**
     * 列类型
     */
	@TableField("column_type")
	private String columnType;
    /**
     * 映射java类型
     */
	@TableField("java_type")
	private String javaType;
    /**
     * 列注释
     */
	@TableField("column_comment")
	private String columnComment;
    /**
     * 列排序（升序）
     */
	@TableField("column_sort")
	private Integer columnSort;
    /**
     * 鍒楁爣绛惧悕
     */
	@TableField("column_label")
	private String columnLabel;
    /**
     * 页面显示类型：1、文本框 2、下拉框 3、数值4、日期 5、文本域6、富文本 7、上传图片【单文件】 8、上传图片【多文件】9、上传文件【单文件】 10、上传文件【多文件】11、隐藏域 12、不显示
     */
	@TableField("page_type")
	private Integer pageType;
    /**
     * 是否必填
     */
	@TableField("is_required")
	private Integer isRequired;
    /**
     * 页面显示为下拉时使用，字典类型从字典表中取出
     */
	@TableField("dict_type")
	private String dictType;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnType() {
		return columnType;
	}

	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public Integer getColumnSort() {
		return columnSort;
	}

	public void setColumnSort(Integer columnSort) {
		this.columnSort = columnSort;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}

	public Integer getPageType() {
		return pageType;
	}

	public void setPageType(Integer pageType) {
		this.pageType = pageType;
	}

	public Integer getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Integer isRequired) {
		this.isRequired = isRequired;
	}

	public String getDictType() {
		return dictType;
	}

	public void setDictType(String dictType) {
		this.dictType = dictType;
	}

	@Override
	public String toString() {
		return "SysGenColumns{" +
			", id=" + id +
			", tableName=" + tableName +
			", columnName=" + columnName +
			", columnType=" + columnType +
			", javaType=" + javaType +
			", columnComment=" + columnComment +
			", columnSort=" + columnSort +
			", columnLabel=" + columnLabel +
			", pageType=" + pageType +
			", isRequired=" + isRequired +
			", dictType=" + dictType +
			"}";
	}
}
