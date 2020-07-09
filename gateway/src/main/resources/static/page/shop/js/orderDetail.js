
var orderBasicData=parent.window.data;

layui.use('table', function () {
    var $ = layui.jquery;
    var table = layui.table;
    table.render({
        elem: '#details_table',
        url: '/orders/getOrderGoods?order_id='+orderBasicData.orderId,
        method: 'get',
        cols: [[
            {field: 'goodsName', title: '商品名字'},
            {field: 'goodsPrice', title: '商品单价'},
            {field: 'goodsCount', title: '商品数量'},
        ]],
    });
});

//window.location.search获取URL路径?及其后面的字符串
function getRequest() {
    var url = window.location.search;
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}

var param = getRequest();

layui.use(['form', 'layedit', 'laydate'], function(){
    var $ = layui.jquery;
    var form = layui.form
        ,layer = layui.layer
        ,layedit = layui.layedit
        ,laydate = layui.laydate;

    //表单赋值
    function init(){
        form.val('order', {
            "order_address": orderBasicData.orderAddress
            ,"order_message": orderBasicData.orderMessage
            ,"order_sn": orderBasicData.orderSn
            ,"order_status": orderBasicData.orderStatus
            ,"goods_price": orderBasicData.goodsPrice
            ,"freight_price": orderBasicData.freightPrice
            ,"pindou_price": orderBasicData.pindouPrice
            ,"order_price":orderBasicData.orderPrice
            ,"actual_price":orderBasicData.actualPrice
            ,"order_tel":orderBasicData.orderTel
            ,"order_consignee":orderBasicData.orderConsignee
        });

        form.val('ship', {
            "ship_sn": orderBasicData.shipSn,
            "ship_channel": parseShipChannel(orderBasicData.shipChannel),
            "ship_time": createTime(orderBasicData.shipTime),
        });

        $.ajax({
            url: '/shop/getShipByShipSn',
            type: 'get',
            dataType: 'json',
            data: {'ship_channel': orderBasicData.shipChannel,'ship_sn':orderBasicData.shipSn},
            success: function (suc) {
                if(suc.code === '0'){
                    var lisLen = suc.data.Traces.length;
                    var tracesHtml = '';
                    var count = 0;
                    for(var i = lisLen-1;i >= 0; i--) {
                        if(count === 0){
                            tracesHtml += '<li class="layui-timeline-item">'
                            tracesHtml += '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'
                            tracesHtml += '<div class="layui-timeline-content layui-text">'
                            tracesHtml += '<h3 class="layui-timeline-title" style="color:#FF0000">'
                            tracesHtml += '<i class="layui-icon layui-icon-notice" style="font-size: 20px; color:#FF0000;"></i>'+suc.data.Traces[i].AcceptTime+'</h3>'
                            tracesHtml += '<p style="color:#FF0000">'+suc.data.Traces[i].AcceptStation+'</p></div></li>'
                        }else{
                            tracesHtml += '<li class="layui-timeline-item">'
                            tracesHtml += '<i class="layui-icon layui-timeline-axis">&#xe63f;</i>'
                            tracesHtml += '<div class="layui-timeline-content layui-text">'
                            tracesHtml += '<h3 class="layui-timeline-title">'+suc.data.Traces[i].AcceptTime+'</h3>'
                            tracesHtml += '<p>'+suc.data.Traces[i].AcceptStation+'</p></div></li>'
                        }
                        count = count+1;
                    }
                    $("#shipMessage").html(tracesHtml);
                }else{
                    layer.msg("操作失败", {icon: 5});
                }
            }
        });
    }

    init();

    //表单取值
    layui.$('#LAY-component-form-getval').on('click', function(){
        var data = form.val('example');
        alert(JSON.stringify(data));
    });

});

function parseShipChannel(value) {
    if (value === 'YTO'){
        return '圆通速递';
    } else if (value === 'SF'){
        return '顺丰快递';
    }
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