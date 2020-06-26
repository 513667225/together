package com.together.modules.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-06-26
 */
@TableName("product")
public class ProductEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品价格
     */
    private String productPrice;

    /**
     * 商品折扣
     */
    private Double productDiscount;

    /**
     * 商品库存
     */
    private String productStock;

    /**
     * 店铺ID
     */
    private String shopId;

    /**
     * 销售额
     */
    private Integer productSales;

    /**
     * 商品图片
     */
    private String productImg;

    /**
     * 商品详情
     */
    private String productDetails;

    public String getProductId() {
        return productId;
    }

    public ProductEntity setProductId(String productId) {
        this.productId = productId;
        return this;
    }
    public String getProductName() {
        return productName;
    }

    public ProductEntity setProductName(String productName) {
        this.productName = productName;
        return this;
    }
    public String getProductPrice() {
        return productPrice;
    }

    public ProductEntity setProductPrice(String productPrice) {
        this.productPrice = productPrice;
        return this;
    }
    public Double getProductDiscount() {
        return productDiscount;
    }

    public ProductEntity setProductDiscount(Double productDiscount) {
        this.productDiscount = productDiscount;
        return this;
    }
    public String getProductStock() {
        return productStock;
    }

    public ProductEntity setProductStock(String productStock) {
        this.productStock = productStock;
        return this;
    }
    public String getShopId() {
        return shopId;
    }

    public ProductEntity setShopId(String shopId) {
        this.shopId = shopId;
        return this;
    }
    public Integer getProductSales() {
        return productSales;
    }

    public ProductEntity setProductSales(Integer productSales) {
        this.productSales = productSales;
        return this;
    }
    public String getProductImg() {
        return productImg;
    }

    public ProductEntity setProductImg(String productImg) {
        this.productImg = productImg;
        return this;
    }
    public String getProductDetails() {
        return productDetails;
    }

    public ProductEntity setProductDetails(String productDetails) {
        this.productDetails = productDetails;
        return this;
    }

    @Override
    public String toString() {
        return "ProductEntity{" +
            "productId=" + productId +
            ", productName=" + productName +
            ", productPrice=" + productPrice +
            ", productDiscount=" + productDiscount +
            ", productStock=" + productStock +
            ", shopId=" + shopId +
            ", productSales=" + productSales +
            ", productImg=" + productImg +
            ", productDetails=" + productDetails +
        "}";
    }
}
