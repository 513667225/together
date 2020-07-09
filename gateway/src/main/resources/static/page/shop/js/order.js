var data;
layui.use(['table','form'], function () {
    var $ = layui.jquery;
    var table = layui.table;
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;
    var order_id;

    form.render();

    form.on('submit(shipSubmit)', function(data) {
        $.ajax({
            url: '/orders/updateOrders',
            type: 'get',
            dataType: 'json',
            data: {'order_id':order_id,'ship_sn': data.field.shipSn,'ship_channel':data.field.shipChannel},
            success: function (suc) {
                console.log("------"+suc);
            }
        })
    })

    table.render({
        elem: '#orders_table',
        url: '/orders/getShopOrders',
        method: 'get',
        toolbar: '#toolbarDemo',
        where:{
            shop_id:'1',
            order_status:101,
        },
        cols: [[
            {type: 'checkbox'},
            {field: 'orderId', width: 80, title: 'id'},
            {field: 'orderTel', width: 150, title: '会员号码'},
            {field: 'orderSn', width: 120, title: '订单编号', sort: true},
            {field: 'orderStatus', width: 80, title: '状态', sort: true},
            {field: 'orderConsignee', width: 100,title: '收货人'},
            {field: 'orderAddress', title: '具体地址'},
            {field: 'orderMessage', title: '用户留言'},
            {field: 'orderDetail', title: '订单详情', width: 120, toolbar: '#orderDetail', align: 'center'},
            {fixed: 'right', title: '操作', toolbar: '#shipBar', align: 'center'}
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
                "count": res.count,
                "data": res.data
            };
        },
        request: {
            pageName: 'page',
            limitName: 'limit'
        },
    });

    table.on('tool(orders_table)', function (obj) {
        data = obj.data;
        if (obj.event === 'orderDetail') {
            var orderId =data.orderId;
            var index = layer.open({
                type:2,
                title:'查看订单详情',
                area : ['1000px' , '800px'],
                shadeClose:true,
                content: "orderDetail.html?orderId="+orderId
            });
        }else if(obj.event === 'editShip'){
            order_id = data.orderId;
            layer.open({
                type:1,
                title:'上传物流信息',
                area : ['420px', '250px'],
                shadeClose:true,
                content: $("#upShipMessage")
            });
        }
    });

    form.on('radio(shipStatus)', function(data){
        var status = data.value;
        if(status === '101'){
            table.reload('orders_table', {
                method: 'get',
                where: {
                    shop_id:'1',
                    order_status:status,
                },
                page: {
                    curr: 1
                }
            });
            $("#ship101").attr('checked','checked');
        }else if(status === '102'){
            table.reload('orders_table', {
                method: 'get',
                where: {
                    shop_id:'1',
                    order_status:status,
                },
                page: {
                    curr: 1
                }
            });
            $("#ship102").attr('checked','checked');
        }
    });
})