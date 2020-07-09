//JavaScript代码区域
layui.use('element', function () {
    var $ = layui.jquery;
    var element = layui.element;
    $.ajax({
        url: "/shopMenu/getMenu",
        type: "get",
        dataType: "json",
        success: function (data) {
            var menuHtml = "";
            $.each(data.data, function () {
                var _data = this;
                if (_data.children != null) {
                    menuHtml += "<li class='layui-nav-item layui-nav-itemed'><a  href='javascript:;'>" + _data.name + "</a><dl class='layui-nav-child'>";
                    $.each(_data.children, function () {
                        menuHtml += "<dd><a onclick='addTab(\"" + this.name + "\",\"" + this.id + "\",\"" + this.href + "\")' href='javascript:;' data-url='"+this.href+"' data-id='"+this.id+"' data-title='"+this.name+"''>" + this.name + "</a></dd>";
                    })
                    menuHtml += "</dl></li>"
                } else {
                    menuHtml += "<li class='layui-nav-item'><a href='javascript:;'>" + _data.name + "</a></li>";
                }
            })
            $("#menuBody").html(menuHtml);
            element.render("nav");
        }
    });

    //首页选项卡
    var tabheight = layui.jquery(window).height() - 194;
    contentTxt = '<iframe src="/page/shop/index.html" scrolling="no" width="100%" height="' + (tabheight) + 'PX"></iframe>';
    layui.element.tabAdd('tabDemo', {
        title: '首页',
        content: contentTxt,
        id: '首页',
    });

    layui.element.tabChange('tabDemo', '首页');
});

/*触发动态选项卡事件*/
function addTab(name, id, href) {
    if(layui.jquery(".layui-tab-title li[lay-id='" + name + "']").length > 0) {
        //选项卡已经存在
        layui.element.tabChange('tabDemo', name);
        /*layer.msg('切换-' + name)*/
    } else {
        //动态控制iframe高度
        var tabheight = layui.jquery(window).height() - 194;
        contentTxt = '<iframe src="' + href + '" scrolling="no" width="100%" height="' + (tabheight) + 'PX"></iframe>';
        //新增一个Tab项
        layui.element.tabAdd('tabDemo', {
            title: name,
            content: contentTxt,
            id: name
        })
        //切换刷新
        layui.element.tabChange('tabDemo', name)
    }
}