package com.weread.service.read.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 代码生成表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("sys_gen_table")
public class SysGenTable extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 表名
     */
	@TableField("table_name")
	private String tableName;
    /**
     * 实体类名称
     */
	@TableField("class_name")
	private String className;
    /**
     * 表说明
     */
	private String comments;
    /**
     * 分类：0：数据表，1：树表
     */
	private Integer category;
    /**
     * 生成包路径
     */
	@TableField("package_name")
	private String packageName;
    /**
     * 生成模块名
     */
	@TableField("module_name")
	private String moduleName;
    /**
     * 生成子模块名
     */
	@TableField("sub_module_name")
	private String subModuleName;
    /**
     * 生成功能名，用于类描述
     */
	@TableField("function_name")
	private String functionName;
    /**
     * 生成功能名（简写），用于功能提示，如“保存xx成功”
     */
	@TableField("function_name_simple")
	private String functionNameSimple;
    /**
     * 生成功能作者
     */
	private String author;
    /**
     * src目录
     */
	@TableField("src_dir")
	private String srcDir;
    /**
     * 其它生成选项
     */
	private String options;
    /**
     * 创建者
     */
	@TableField("create_by")
	private Long createBy;
    /**
     * 创建时间
     */
	@TableField("create_date")
	private Date createDate;
    /**
     * 更新者
     */
	@TableField("update_by")
	private Long updateBy;
    /**
     * 更新时间
     */
	@TableField("update_date")
	private Date updateDate;
    /**
     * 备注信息
     */
	private String remarks;


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

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSubModuleName() {
		return subModuleName;
	}

	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionNameSimple() {
		return functionNameSimple;
	}

	public void setFunctionNameSimple(String functionNameSimple) {
		this.functionNameSimple = functionNameSimple;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSrcDir() {
		return srcDir;
	}

	public void setSrcDir(String srcDir) {
		this.srcDir = srcDir;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SysGenTable{" +
			", id=" + id +
			", tableName=" + tableName +
			", className=" + className +
			", comments=" + comments +
			", category=" + category +
			", packageName=" + packageName +
			", moduleName=" + moduleName +
			", subModuleName=" + subModuleName +
			", functionName=" + functionName +
			", functionNameSimple=" + functionNameSimple +
			", author=" + author +
			", srcDir=" + srcDir +
			", options=" + options +
			", createBy=" + createBy +
			", createDate=" + createDate +
			", updateBy=" + updateBy +
			", updateDate=" + updateDate +
			", remarks=" + remarks +
			"}";
	}
}
