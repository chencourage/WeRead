package com.weread.service.sys.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 
 * </p>
 *
 * @author Chenk
 * @since 2020-06-09
 */
@TableName("read_book")
public class ReadBook extends BaseEntity {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 书名
     */
	@TableField("book_name")
	private String bookName;
    /**
     * 作者
     */
	@TableField("book_writer")
	private String bookWriter;
    /**
     * 出版社
     */
	@TableField("book_publisher")
	private String bookPublisher;
    /**
     * 出版日期
     */
	@TableField("release_date")
	private String releaseDate;
    /**
     * ISBN号
     */
	private String isbn;
    /**
     * 主题
     */
	private String topic;
    /**
     * 内容概述
     */
	@TableField("book_description")
	private String bookDescription;
	@TableField("publisher_resource")
	private String publisherResource;
    /**
     * 关于作者
     */
	@TableField("about_publisher")
	private String aboutPublisher;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 修改时间
     */
	@TableField("update_time")
	private Date updateTime;
    /**
     * 状态
     */
	private String state;
    /**
     * 图片链接
     */
	@TableField("img_url")
	private String imgUrl;
    /**
     * 下载状态01:允许下载
     */
	@TableField("down_state")
	private String downState;
    /**
     * 阅读状态01:允许阅读,02不允许
     */
	@TableField("read_state")
	private String readState;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookWriter() {
		return bookWriter;
	}

	public void setBookWriter(String bookWriter) {
		this.bookWriter = bookWriter;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getPublisherResource() {
		return publisherResource;
	}

	public void setPublisherResource(String publisherResource) {
		this.publisherResource = publisherResource;
	}

	public String getAboutPublisher() {
		return aboutPublisher;
	}

	public void setAboutPublisher(String aboutPublisher) {
		this.aboutPublisher = aboutPublisher;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDownState() {
		return downState;
	}

	public void setDownState(String downState) {
		this.downState = downState;
	}

	public String getReadState() {
		return readState;
	}

	public void setReadState(String readState) {
		this.readState = readState;
	}

	@Override
	public String toString() {
		return "ReadBook{" +
			", id=" + id +
			", bookName=" + bookName +
			", bookWriter=" + bookWriter +
			", bookPublisher=" + bookPublisher +
			", releaseDate=" + releaseDate +
			", isbn=" + isbn +
			", topic=" + topic +
			", bookDescription=" + bookDescription +
			", publisherResource=" + publisherResource +
			", aboutPublisher=" + aboutPublisher +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			", state=" + state +
			", imgUrl=" + imgUrl +
			", downState=" + downState +
			", readState=" + readState +
			"}";
	}
}
