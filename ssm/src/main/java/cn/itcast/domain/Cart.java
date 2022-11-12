package cn.itcast.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车类
 *
 * @author fail
 * @create 2021-03-24 18:16
 */
public class Cart implements Serializable {
    private Integer cartId;
    private BigDecimal totalPrice;
    private Integer totalCount;
    private Integer userid;
    private List<CartItem> list;

    public Cart() {
    }

    public Cart(Integer cartId, BigDecimal totalPrice, Integer totalCount, Integer userid, List<CartItem> list) {
        this.cartId = cartId;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
        this.userid = userid;
        this.list = list;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalPrice=" + totalPrice +
                ", totalCount=" + totalCount +
                ", user_id=" + userid +
                ", list=" + list +
                '}';
    }
}
