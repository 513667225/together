layui.use(['table','form'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    form.render();

    form.on('submit(serchBtn)', function(data) {
        table.reload('goods_table', {
            method: 'get',
            where: {
                shopId:'1',
                searchOption: data.field.searchOption,
                searchKeywords:data.field.searchKeywords
            },
            page: {
                curr: 1
            }
        });
        return false;
    })



    function tableReload(){
        table.reload('goods_table', {
            method: 'get',
            where: {
                shopId:'1'
            },
            page: {
                curr: 1
            }
        });

    }

    table.render({
        elem: '#goods_table',
        id:'goods_table',
        url: '/goods/getGoodsPage',
        method: 'get',
        toolbar: '#toolbarDemo',
        where:{
            shopId:'1',
        },
        cols: [[
            {field: 'goodsId', width: 100, title: 'ID'},
            {field: 'goodsName', title: '商品名称', sort: true},
            {field: 'categoryName', width: 110, title: '商品分类', sort: true, align: 'center'},
            {field: 'goodsGallery', title: '宣传图片', width: 120, toolbar: '#checkGoodsPicDetail', align: 'center'},
            {field: 'goodsPrice', width: 80, title: '价格(元)', align: 'center'},
            {field: 'goodsBrief', title: '商品简介'},
            {field: 'isOnSale', width: 89, title: '上架', align: 'center',
                templet: function (d) {
                    if(d.isOnSale==false){
                        return "<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"upSale\">已下架</a>"
                    }else {
                        return "<a class=\"layui-btn layui-btn-xs\" lay-event=\"disSale\">已上架</a>"
                    }
                }},
            {
                field: 'picUrl',
                width: 165,
                title: '首页图',
                align: 'center',
                templet: function (data) {
                    var url = data.picUrl;
                    return "<div onclick='showImg(\"/upload/shop/" + url + "\")'><img src='" + url + "' style='height:35px;width=35px'></div>";
                }
            },
            {field: 'goodsUnit', width: 70, title: '单位', align: 'center'},
            {field: 'goodsDetail', title: '商品详情页', width: 100, toolbar: '#checkGoodsDetail', align: 'center'},
            // {field: 'spokesman_id', width: 135, title: '代言人ID'},预留字段---主播
            {
                field: 'addTime', width: 150, title: '创建时间',
                templet: function (d) {
                    return createTime(d.addTime);
                }
            },
            {
                field: 'goodsNature', width: 70, title: '热门',
                templet: function (d) {
                    if(d.goodsNature==3){
                        return "<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"unrecommend\">取消</a>"
                    }else {
                        return "<a class=\"layui-btn layui-btn-xs\" lay-event=\"recommend\">推荐</a>"
                    }
                    //return createTime(d.addTime);
                }
            }, {
                field: 'goodsNature', width: 70, title: '拼团',
                templet: function (d) {
                    if(d.goodsNature==2){
                        return "<a class=\"layui-btn layui-btn-danger layui-btn-xs\" lay-event=\"unmass\">取消</a>"
                    }else {
                        return "<a class=\"layui-btn layui-btn-xs\" lay-event=\"mass\">拼团</a>"
                    }
                    //return createTime(d.addTime);
                }
            },
            {fixed: 'right', title: '操作', toolbar: '#goodsBar', align: 'center'}
        ]],
        page: true,
        limits: [5, 10, 20, 50],
        limit: 5,
        loading: true,
        count: 1000,
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.total,
                "data": res.data
            };
        },
        request: {
            pageName: 'page',
            limitName: 'limit'
        },
    });

    table.on('tool(goods_table)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确定删除当前商品?', function (index) {
                $.ajax({
                    url: '/goods/delGoods',
                    type: 'get',
                    dataType: 'json',
                    data: {'goods_id': data.goodsId},
                    success: function (suc) {
                        if (suc.code == 0) {
                            obj.del();
                            layer.close(index);
                            layer.msg(suc.msg, {icon: 1});
                        } else {
                            layer.msg(suc.msg, {icon: 5});
                        }
                    }
                });
            });
        } else if (obj.event === 'edit') {

        } else if (obj.event === 'checkGoodsDetail') {
            var index = layer.open({
                type: 1,
                title: '查看商品详情',
                area: ['auto', '800px'],
                // maxmin: true,
                shadeClose: true,
                content: obj.data.goodsDetail,
            });
        } else if (obj.event === 'checkGoodsPicDetail') {
            console.log(data.goodsGallery);
            var jsonResult  = $.parseJSON(data.goodsGallery);
            console.log(jsonResult);
            layer.photos({
                photos: jsonResult,
                anim: 5
            });
        }else if(obj.event=='recommend'){//修改商品为热门推荐商品
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'goods_nature':3},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("商品推荐成功", {icon: 1});
                    }
                }
            })

        }else if(obj.event=='unrecommend'){//修改商品为热门推荐商品
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'goods_nature':1},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("商品取消推荐成功", {icon: 1});

                    }
                }
            })

        }else if(obj.event=='mass'){//修改商品为热门推荐商品
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'goods_nature':2},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("推荐拼团成功", {icon: 1});

                    }
                }
            })

        }else if(obj.event=='unmass'){//修改商品为热门推荐商品
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'goods_nature':1},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("取消推荐拼团成功", {icon: 1});

                    }
                }
            })
        }else if(obj.event=='disSale'){//修改商品下架
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'is_on_sale':false},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("下架成功", {icon: 1});
                    }
                }
            })

        }else if(obj.event=='upSale'){//修改商品为上架
            $.ajax({
                url: '/goods/updateGoods',
                type: 'get',
                dataType: 'json',
                data: {'goods_id': data.goodsId,'is_on_sale':true},
                success: function (suc) {
                    if(suc.code=='0'){
                        tableReload();
                        layer.msg("下架成功", {icon: 1});
                    }
                }
            })

        }

    });
})

function addGoods() {
    var index = layer.open({
        type:2,
        title:'增加商品',
        area : ['1000px' , '800px'],
        shadeClose:true,
        content: "goodsDetail.html"
    });
    layer.full(index);
}

function showImg(url) {
    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        area: ['auto'],
        skin: 'layui-layer-nobg',
        shadeClose: true,
        content: "<img src='/upload/shop/" + url + "'>"
    });
}

function createTime(v) {
    var date = new Date(v);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? '0' + m : m;
    var d = date.getDate();
    d = d < 10 ? ("0" + d) : d;
    var h = date.getHours();
    h = h < 10 ? ("0" + h) : h;
    var M = date.getMinutes();
    M = M < 10 ? ("0" + M) : M;
    var str = y + "-" + m + "-" + d + " " + h + ":" + M;
    return str;
}
