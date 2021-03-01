package com.weread.service.read.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.weread.service.base.BaseEntity;

/**
 * <p>
 * 充值订单; InnoDB free: 6144 kB
 * </p>
 *
 * @author Chenk
 * @since 2021-02-28
 */
@TableName("order_pay")
public class OrderPay extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 商户订单号
     */
	@TableField("out_trade_no")
	private Long outTradeNo;
    /**
     * 支付宝/微信交易号
     */
	@TableField("trade_no")
	private String tradeNo;
    /**
     * 支付渠道，1：支付宝，2：微信
     */
	@TableField("pay_channel")
	private Integer payChannel;
    /**
     * 交易金额(单位元)
     */
	@TableField("total_amount")
	private Integer totalAmount;
    /**
     * 支付用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 支付状态：0：支付失败，1：支付成功，2：待支付
     */
	@TableField("pay_status")
	private Integer payStatus;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(Long outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Integer getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(Integer payChannel) {
		this.payChannel = payChannel;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
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

	@Override
	public String toString() {
		return "OrderPay{" +
			", id=" + id +
			", outTradeNo=" + outTradeNo +
			", tradeNo=" + tradeNo +
			", payChannel=" + payChannel +
			", totalAmount=" + totalAmount +
			", userId=" + userId +
			", payStatus=" + payStatus +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
