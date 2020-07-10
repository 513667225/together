var data;
var shopId = '1';
layui.use(['table', 'form','element','jquery'], function () {
    var table = layui.table;
    var $ = layui.jquery;
    var form = layui.form;
    var element = layui.element;
    form.render();

    form.verify({
        calc: function (v,i) {
            var beforeValue = $("#money").val();
            if(v>beforeValue*0.5){
                return '拼团券成本不能大于拼团券面额的百分之五十!';
            }
        }
    });

    form.on('submit(groupRollSubmit)',function (data) {

    })
})