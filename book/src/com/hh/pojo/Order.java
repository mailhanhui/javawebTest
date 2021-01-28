package com.hh.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author GeekHan Email:mailhanhui@gmail.com
 * @CreateDate 2021 - 01 - 23 - 12:46
 * <p>
 * Description:
 * 1.
 * 2.
 */
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    //0 未发货，1 已发货，2 已签收
    private Integer status=0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date createTime, BigDecimal price, Integer status, Integer userID) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userID;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserID() {
        return userId;
    }

    public void setUserID(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Oder{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userID=" + userId +
                '}';
    }
}
