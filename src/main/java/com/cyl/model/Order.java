package com.cyl.model;

/**
 * @author : Liu
 * @Date : 2019/10/26 下午 11:14
 * @Description :
 */

public class Order {

    private int id;
    private String orderNo;
    private int orderMoney;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(int orderMoney) {
        this.orderMoney = orderMoney;
    }
}
