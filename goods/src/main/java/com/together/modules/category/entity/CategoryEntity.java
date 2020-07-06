package com.together.modules.category.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author 
 * @since 2020-06-28
 */
@TableName("category")
public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "category_id", type = IdType.AUTO)
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

    /**
     * 父类目ID
     */
    private Integer pid;

    /**
     * 类目图标
     */
    private String iconUrl;

    /**
     * 类目图片
     */
    private String picUrl;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 创建时间
     */
    private Date addTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    public Integer getCategoryId() {
        return categoryId;
    }

    public CategoryEntity setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }
    public String getCategoryName() {
        return categoryName;
    }

    public CategoryEntity setCategoryName(String categoryName) {
        this.categoryName = categoryName;
        return this;
    }
    public Integer getPid() {
        return pid;
    }

    public CategoryEntity setPid(Integer pid) {
        this.pid = pid;
        return this;
    }
    public String getIconUrl() {
        return iconUrl;
    }

    public CategoryEntity setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        return this;
    }
    public String getPicUrl() {
        return picUrl;
    }

    public CategoryEntity setPicUrl(String picUrl) {
        this.picUrl = picUrl;
        return this;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }

    public CategoryEntity setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }
    public Date getAddTime() {
        return addTime;
    }

    public CategoryEntity setAddTime(Date addTime) {
        this.addTime = addTime;
        return this;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public CategoryEntity setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
            "categoryId=" + categoryId +
            ", categoryName=" + categoryName +
            ", pid=" + pid +
            ", iconUrl=" + iconUrl +
            ", picUrl=" + picUrl +
            ", sortOrder=" + sortOrder +
            ", addTime=" + addTime +
            ", updateTime=" + updateTime +
        "}";
    }
}
