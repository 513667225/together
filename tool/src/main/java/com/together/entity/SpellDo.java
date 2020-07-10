package com.together.entity;


import org.elasticsearch.index.VersionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;



@Document(indexName = "spell",type = "_doc",shards = 1, replicas = 0,createIndex = false,useServerConfiguration = true,versionType = VersionType.EXTERNAL)
public class SpellDo {

    @Id
    private  int info_id;
    @Field(type = FieldType.Text)
    //操作类型
    private String operationType;
    @Field(type = FieldType.Integer)
    //金额源头
    private  int user_id;
    @Field(type = FieldType.Integer)
    //金额流向
    private  int to_user_id;
    @Field(type = FieldType.Double)
    //金额数
    private  double money_number;
    @Field(type = FieldType.Text)
    //金额类型
    private  String money_type;
    @Field(type = FieldType.Long)
    //创建时间
    private long  create_time;
    @Field(type = FieldType.Long)
    //单号
    private  long odd;


    public int getInfo_id() {
        return info_id;
    }

    public void setInfo_id(int info_id) {
        this.info_id = info_id;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(int to_user_id) {
        this.to_user_id = to_user_id;
    }

    public double getMoney_number() {
        return money_number;
    }

    public void setMoney_number(double money_number) {
        this.money_number = money_number;
    }

    public String getMoney_type() {
        return money_type;
    }

    public void setMoney_type(String money_type) {
        this.money_type = money_type;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getOdd() {
        return odd;
    }

    public void setOdd(long odd) {
        this.odd = odd;
    }
}
