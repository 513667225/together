package com.together.modules.region.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 行政区域表
 * </p>
 *
 * @author 
 * @since 2020-07-06
 */
@TableName("region")
public class RegionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0
     */
    private Integer pid;

    /**
     * 行政区域名称
     */
    private String name;

    /**
     * 行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县
     */
    private Integer type;

    /**
     * 行政区域编码
     */
    private Integer code;

    public Integer getId() {
        return id;
    }

    public RegionEntity setId(Integer id) {
        this.id = id;
        return this;
    }
    public Integer getPid() {
        return pid;
    }

    public RegionEntity setPid(Integer pid) {
        this.pid = pid;
        return this;
    }
    public String getName() {
        return name;
    }

    public RegionEntity setName(String name) {
        this.name = name;
        return this;
    }
    public Integer getType() {
        return type;
    }

    public RegionEntity setType(Integer type) {
        this.type = type;
        return this;
    }
    public Integer getCode() {
        return code;
    }

    public RegionEntity setCode(Integer code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "RegionEntity{" +
            "id=" + id +
            ", pid=" + pid +
            ", name=" + name +
            ", type=" + type +
            ", code=" + code +
        "}";
    }
}
