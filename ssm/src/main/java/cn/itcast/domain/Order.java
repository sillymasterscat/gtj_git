package cn.itcast.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fail
 * @create 2021-03-25 19:42
 */
public class Order {
    private String orderId;
    private Date datetime;
    private BigDecimal price;
    // 0 待发货  1派送中   2到达请签收
    private Integer status;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date datetime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.datetime = datetime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", datetime=" + datetime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
