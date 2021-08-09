package com.ww.spring.model;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Transaction {

    // 产品编号 
    // 不能为空
    @NotNull
    private Long productId;
    
    // 用户编号
    // 不能为空
    @NotNull
    private Long userId;
    
    // 交易日期
    // 将来日期
    @Future
    // 日期格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    // 不能为空
    @NotNull
    private Date date;
    
    // 价格
    // 不能为空
    @NotNull
    // 最小值0.1元
    @DecimalMin(value = "0.1")
    private Double price;
    
    // 数量
    // 最小值为1
    @Min(1)
    // 最大值
    @Max(100)
    // 不能为空
    @NotNull
    private Integer quantity;
    
    // 交易日期
    // 不能为空
    @NotNull
    // 最小交易金额1元
    @DecimalMin("1.00")
    // 最大金额为5万元
    @DecimalMax("500000.00")
    private Double amount;
    
    // 邮件
    @Pattern(regexp = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$", message = "不符合邮件格式")
    private String email;
    
    // 备注
    // 0到255个字符
    @Size(min = 0, max = 256)
    private String note;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
