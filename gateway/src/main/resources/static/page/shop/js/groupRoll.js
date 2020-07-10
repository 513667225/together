var data;
var shopId = '1';
layui.use(['table','form'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form
    form.render();

    form.on('submit(serchBtn)', function(data) {
        table.reload('groupRoll_table', {
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
        elem: '#groupRoll_table',
        id:'groupRoll_table',
        url: '/groupRoll/selectGroupRollByShopId',
        method: 'get',
        toolbar: '#toolbarDemo',
        where:{
            shopId:shopId,
        },
        cols: [[
            {field: 'grouprollId', width: 100, title: 'ID'},
            {field: 'money', width: 110, title: '拼团券面额(元)', align: 'center'},
            {field: 'shopPublishMoney', width: 110, title: '店铺实际收入金额', align: 'center'},
            {field: 'shopAddress', title: '店铺地址', align: 'center'},
            {field: 'remark', title: '备注', align: 'center'},
            {field: 'inventory', width: 110, title: '库存', align: 'center'},
            {
                field: 'addTime', width: 150, title: '创建时间',
                templet: function (d) {
                    return createTime(d.addTime);
                }
            },
            {field: 'groupRollDetail', title: '查看使用详情', width: 100, toolbar: '#checkGroupRollDetail', align: 'center'},

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

    table.on('tool(groupRoll_table)', function (obj) {
        data = obj.data;
        if (obj.event === 'checkGroupRollDetail') {
            var index = layer.open({
                type:2,
                title:'查看拼团券详情',
                area : ['1000px' , '800px'],
                shadeClose:true,
                content: "groupRollDetail.html"
            });
            layer.full(index);
        }
    });


})

function addGroupRoll() {
    var index = layer.open({
        type:2,
        title:'发行拼团券',
        area : ['1000px' , '800px'],
        shadeClose:true,
        content: "groupRollAdd.html"
    });
    layer.full(index);
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