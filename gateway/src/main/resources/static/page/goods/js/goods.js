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
            {field: 'goodsGallery', title: '商品宣传图片列表', width: 150, toolbar: '#checkGoodsPicDetail', align: 'center'},
            {field: 'goodsPrice', width: 110, title: '商品价格(元)', align: 'center'},
            {field: 'goodsBrief', title: '商品简介'},
            {field: 'isOnSale', width: 100, title: '是否上架', align: 'center'},
            {
                field: 'picUrl',
                width: 165,
                title: '商品页面商品图片',
                align: 'center',
                templet: function (data) {
                    var url = data.picUrl;
                    return "<div onclick='showImg(\"/upload/shop/" + url + "\")'><img src='" + url + "' style='height:35px;width=35px'></div>";
                }
            },
            {field: 'goodsUnit', width: 100, title: '商品单位', align: 'center'},
            {field: 'goodsDetail', title: '商品详情页', width: 100, toolbar: '#checkGoodsDetail', align: 'center'},
            // {field: 'spokesman_id', width: 135, title: '代言人ID'},预留字段---主播
            {
                field: 'addTime', width: 150, title: '创建时间',
                templet: function (d) {
                    return createTime(d.addTime);
                }
            },
            {
                field: 'updateTime', width: 150, title: '更新时间',
                templet: function (d) {
                    return createTime(d.updateTime);
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
