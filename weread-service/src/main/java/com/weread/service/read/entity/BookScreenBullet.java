package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 小说弹幕表; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("book_screen_bullet")
public class BookScreenBullet extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 小说内容ID
     */
	@TableField("content_id")
	private Long contentId;
    /**
     * 小说弹幕内容
     */
	@TableField("screen_bullet")
	private String screenBullet;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContentId() {
		return contentId;
	}

	public void setContentId(Long contentId) {
		this.contentId = contentId;
	}

	public String getScreenBullet() {
		return screenBullet;
	}

	public void setScreenBullet(String screenBullet) {
		this.screenBullet = screenBullet;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "BookScreenBullet{" +
			", id=" + id +
			", contentId=" + contentId +
			", screenBullet=" + screenBullet +
			", createTime=" + createTime +
			"}";
	}
}
