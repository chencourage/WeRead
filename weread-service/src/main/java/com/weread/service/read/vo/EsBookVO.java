package com.weread.service.read.vo;

public class EsBookVO {
	private Long id;
    private Byte workDirection;
    private Integer catId;
    private String catName;
    private String picUrl;
    private String bookName;
    private Long authorId;
    private String authorName;
    private String bookDesc;
    private Float score;
    private Byte bookStatus;
    private Long visitCount;
    private Integer wordCount;
    private Integer commentCount;
    private Long lastIndexId;
    private String lastIndexName;
    private String lastIndexUpdateTime;
    private Byte isVip;
    private Byte status;
    private Integer crawlSourceId;
    private String crawlBookId;
    private Byte crawlIsStop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Byte getWorkDirection() {
		return workDirection;
	}

	public void setWorkDirection(Byte workDirection) {
		this.workDirection = workDirection;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getBookDesc() {
		return bookDesc;
	}

	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Byte getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Byte bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Long getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}

	public Integer getWordCount() {
		return wordCount;
	}

	public void setWordCount(Integer wordCount) {
		this.wordCount = wordCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}

	public Long getLastIndexId() {
		return lastIndexId;
	}

	public void setLastIndexId(Long lastIndexId) {
		this.lastIndexId = lastIndexId;
	}

	public String getLastIndexName() {
		return lastIndexName;
	}

	public void setLastIndexName(String lastIndexName) {
		this.lastIndexName = lastIndexName;
	}

	public String getLastIndexUpdateTime() {
		return lastIndexUpdateTime;
	}

	public void setLastIndexUpdateTime(String lastIndexUpdateTime) {
		this.lastIndexUpdateTime = lastIndexUpdateTime;
	}

	public Byte getIsVip() {
		return isVip;
	}

	public void setIsVip(Byte isVip) {
		this.isVip = isVip;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getCrawlSourceId() {
		return crawlSourceId;
	}

	public void setCrawlSourceId(Integer crawlSourceId) {
		this.crawlSourceId = crawlSourceId;
	}

	public String getCrawlBookId() {
		return crawlBookId;
	}

	public void setCrawlBookId(String crawlBookId) {
		this.crawlBookId = crawlBookId;
	}

	public Byte getCrawlIsStop() {
		return crawlIsStop;
	}

	public void setCrawlIsStop(Byte crawlIsStop) {
		this.crawlIsStop = crawlIsStop;
	}

}
