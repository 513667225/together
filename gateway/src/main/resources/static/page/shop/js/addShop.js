var goodsGrallyList={};
goodsGrallyList.title="xxx";
goodsGrallyList.id="xxx";
goodsGrallyList.start=0;
var dataArray = new Array();
var specificationPicFilePath;
var picUrlFilePathFilePath;


layui.extend({tinymce: '/js/tinymce/tinymce'}).use(['jquery', 'form', 'util', 'layer', 'tinymce','upload'], function () {
    var $ = layui.jquery;
    var form = layui.form,
        layer = layui.layer,
        layedit = layui.layedit,
        upload = layui.upload,
        laydate = layui.laydate;
    var util = layui.util;
    var tinymce = layui.tinymce;

    //表单渲染
    form.render();

    //初始化tinymce
    var goods_detail = tinymce.render({
        elem: "#goodsDetail",
        height: 300,
        width: '99%',
        images_upload_url: '/goods/uploadGoods',
    }, (opt) => {
        //加载完成后回调
    });

    //初始化
    init();

    //商品参数设置相关
    var attributeIndex;
    //添加商品参数
    $("#addAttribute").click(function(){
        attributeIndex = layer.open({
            type:1,
            title:'添加商品参数信息',
            area : ['370px', '270px'],
            shadeClose:true,
            content: $("#attributeMessage")
        });
    });
    //提交商品参数信息
    form.on('submit(attributeSubmit)', function(data) {
        layer.close(attributeIndex);
        $("#attributeName").val("");
        $("#attributeValue").val("");
        var attributeTrHtml = "<tr>"
        attributeTrHtml += "<td>"+data.field.attributeName+"</td>"
        attributeTrHtml += "<td>"+data.field.attributeValue+"</td>"
        attributeTrHtml += "<td align:'center'><button class='layui-btn layui-btn-primary layui-btn-sm attributeDelete'>删除</button></td>"
        attributeTrHtml += "</tr>"
        $("#attributeTable").append(attributeTrHtml);
        return false;
    })
    //删除商品参数
    $("#attributeTable").on('click', '.attributeDelete', function () {
        $(this).closest('tr').remove();
    });
    //商品参数表格转json
    function table2Json(id) {
        var titles = $("#" + id).find("tr:first td");
        var json = "[" + $("#" + id).find("tr:not(:first)").map(function(i, e) {
            return "{" + $(e).children("td").map(function(j, el) {
                return $(titles[j]).html() + ':"' + $(el).html() +'"';
            }).get().join(",") + "}";
        }).get().join(",") + "]";
        var reg1 = new RegExp('参数名','g');
        json = json.replace(reg1,'"attributeName"');
        var reg2 = new RegExp('值','g');
        json = json.replace(reg2,'"attributeValue"');
        var reg3 = new RegExp(',操作:"<button class="layui-btn layui-btn-primary layui-btn-sm attributeDelete">删除</button>"','g');
        json = json.replace(reg3,'');
        return json;
    }


    //商品宣传图片上传 限制5张
    upload.render({
        elem: '#goodsGalleryListUpload',
        url: '/goods/uploadGoods',
        multiple: true,
        acceptMime: 'image/*',
        auto: false,
        size: 5120,
        bindAction: '#submitBtn',//submitBtn
        choose:function(obj){
            // obj.preview(function(index, file, result){
            //     $('#goodsGalleryPreview').append('<img id="goodsGrally'+index+'" style="width: 256px;height: 144px;margin-left: 10px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
            // });
            var files = obj.pushFile();
            var len = getJsonLength(files);
            obj.preview(function (index, file, result) {
                if(parseInt(len)>5){
                    layer.msg("上传商品宣传图片数量不能超过五张", {icon: 5});
                    delete files[index];
                    len = getJsonLength(files);
                }else{
                    $('#goodsGalleryPreview').append('<img id="goodsGrallyRemove_'+index+'" style="width: 256px;' +
                        'height: 144px;margin-left: 10px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                    $('#goodsGrallyRemove_' + index).bind('dblclick', function () {
                        delete files[index];
                        len = getJsonLength(files);
                        $(this).remove();
                    });
                }
            })
        },
        done: function(res){
            var jsonTemp={};
            jsonTemp.alt=res.fileName;
            jsonTemp.pid=1;
            jsonTemp.thumb="";
            jsonTemp.src=res.filePath;
            dataArray.push(jsonTemp);
        },
        allDone: function (res) {
            goodsGrallyList.data=dataArray;
            specificationPicUpload.upload();
        }
    });
    //图片json长度
    function getJsonLength(jsonData){
        var jsonLength = 0;
        for(var item in jsonData){
            jsonLength++;
        }
        return jsonLength;
    }

    //商品规格图片上传
    var specificationPicUpload=upload.render({
        elem: '#specificationPicUpload',
        url: '/goods/uploadGoods',
        acceptMime: 'image/*',
        auto: false,
        size: 5120,
        // bindAction: '#submitBtn',
        choose:function(obj){
            var files = obj.pushFile();
            var len = getJsonLength(files);
            obj.preview(function (index, file, result) {
                if(parseInt(len)>1){
                    layer.msg("商品规格图为一张", {icon: 5});
                    delete files[index];
                    len = getJsonLength(files);
                }else{
                    $('#specificationPicPreview').append('<img id="specificationPicRemove_'+index+'" style="width: 256px;' +
                        'height: 144px;margin-left: 10px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                    $('#specificationPicRemove_' + index).bind('dblclick', function () {
                        delete files[index];
                        len = getJsonLength(files);
                        $(this).remove();
                    });
                }
            })
        },
        done:function(res){
            specificationPicFilePath = res.filePath;
            picUrlUpload.upload();
        }
    });

    //商品首页展示图片上传
    var picUrlUpload=upload.render({
        elem: '#uploadShopPic',
        url: '/shop/uploadShopPic',
        type:"post",
        acceptMime: 'image/*',
        auto: true,
        size: 5120,
        choose:function(obj){
            var files = obj.pushFile();
            var len = getJsonLength(files);
            obj.preview(function (index, file, result) {
                if(parseInt(len)>1){
                    layer.msg("上传成功", {icon: 5});
                    delete files[index];
                    len = getJsonLength(files);
                }else{
                    $('#shopPicUploadPreview').append('<img id="picUrlRemove_'+index+'" style="width: 256px;' +
                        'height: 144px;margin-left: 10px" src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">')
                    $('#picUrlRemove_' + index).bind('dblclick', function () {
                        delete files[index];
                        len = getJsonLength(files);
                        $(this).remove();
                    });
                }
            })
        },
        done:function(res){
            console.log(res);
            picUrlFilePath = res.filePath;
            $("#shop_pic").val(picUrlFilePath);
        }
    });

    //加载省份分类下拉框
    function init() {
        $.ajax({
            url: '/shop/queryRegion?pid=0',
            type: 'get',
            dataType: 'json',
            success: function (suc) {
                var provineHtml = '';
                if(suc.code == 0){
                    provineHtml += '<option value="">请选择父类目</option>';
                    $.each(suc.data, function(i, v) {
                        provineHtml += '<option value="'+v.id+'">'+v.name+'</option>';
                    });
                }else{
                    provineHtml += '<option value="" selected="" disabled="">暂无数据</option>';
                }
                $("#province").html(provineHtml);
                form.render("select");
            }
        });
    }

    //市级二级菜单联动
    form.on('select(province)',function (data) {

        var v = data.value;
        if(v !== ''){
            //$("#category2").empty();
            $.ajax({
                url: '/shop/queryRegion?pid='+v,
                type: 'get',
                dataType: 'json',
                success: function (suc) {
                    var cityHtml = '';
                    if(suc.code == 0){
                        cityHtml += '<option value="">请选择子类目</option>';
                        $.each(suc.data, function(i, v) {
                            cityHtml += '<option value="'+v.id+'">'+v.name+'</option>';
                        });
                    }else{
                        cityHtml += '<option value="" selected="" disabled="">暂无数据</option>';
                    }
                    $("#city").html(cityHtml);
                    form.render("select");
                }
            });
        }else{
            $("#city").empty();
            $("#city").html('<option value="">请选择子类目</option>');
            form.render("select");
        }
    })

    //地区二级菜单联动
    form.on('select(city)',function (data) {

        var v = data.value;
        if(v !== ''){
            //$("#category2").empty();
            $.ajax({
                url: '/shop/queryRegion?pid='+v,
                type: 'get',
                dataType: 'json',
                success: function (suc) {
                    var areaHtml = '';
                    if(suc.code == 0){
                        areaHtml += '<option value="">请选择子类目</option>';
                        $.each(suc.data, function(i, v) {
                            areaHtml += '<option value="'+v.id+'">'+v.name+'</option>';
                        });
                    }else{
                        areaHtml += '<option value="" selected="" disabled="">暂无数据</option>';
                    }
                    $("#area").html(areaHtml);
                    form.render("select");
                }
            });
        }else{
            $("#city").empty();
            $("#city").html('<option value="">请选择子类目</option>');
            form.render("select");
        }
    })

    //    用户名失去焦点事件
    $("#shopuser_name").blur(function (data) {
        var value = $("#shopuser_name").val();
        $.ajax({
            url: '/shopUser/queyByName?shopuser_name='+value,
            type: 'get',
            dataType: 'json',
            success:function (suc) {
                if(!isEmpty(suc.msg)){
                    $("#shopuser_name").focus();
                    $("#message").html(suc.msg);
                }else {
                    if(isEmpty(suc.msg)){
                        $("#message").html("");
                    }
                }
            }

        });
    });
    //    店铺名失去焦点事件
    $("#shop_name").blur(function (data) {
        var value = $("#shop_name").val();
        $.ajax({
            url: '/shop/queryShopByShopName?shop_name='+value,
            type: 'get',
            dataType: 'json',
            success:function (suc) {
                if(!isEmpty(suc.msg)){
                    $("#shop_name").focus();
                    $("#message").html(suc.msg);
                }else {
                    if(isEmpty(suc.msg)){
                        $("#message").html("");
                    }
                }
            }

        });
    });

    //判断返回的对象是否为空
    function isEmpty(obj){
        if(typeof obj == "undefined" || obj == null || obj == ""){
            return true;
        }else{
            return false;
        }
    };
    //验证规则-------------------------------------------------------------------
    //密码验证
    $("#shopuser_password").blur(function (data) {
        var shopuser_password = $("#shopuser_password").val();
        if (shopuser_password.length<6){
            $("#shopuser_password").focus();
            return $("#message").html('密码不能小于6位数！');
        }else {
            return $("#message").html("");
        }
    });
    //确认密码验证
    $("#shopuser_password1").blur(function (data) {
        var shopuser_password = $("#shopuser_password").val();
        var shopuser_password1 = $("#shopuser_password1").val();
        if (shopuser_password!=shopuser_password1){
            $("#shopuser_password1").focus();
            return $("#message").html('两次密码不一致！');
        }else {
            return $("#message").html("");
        }
    });
    //店铺标语验证
    $("#shop_slogan").blur(function (data) {
        var shop_slogan = $("#shop_slogan").val();
        if (shop_slogan==""){
            $("#shop_slogan").focus();
            return $("#message").html('店铺标语不能为空！');
        }else {
            return $("#message").html("");
        }
    });
    //经营范围验证
    $("#shop_category").blur(function (data) {
        var shop_category = $("#shop_category").val();
        if (shop_category==""){
            $("#shop_category").focus();
            return $("#message").html('请输入经营范围！');
        }else {
            return $("#message").html("");
        }
    });

    //电话号码验证
    $("#shop_tel").blur(function (data) {
        var shop_tel = $("#shop_tel").val();
        if (shop_tel.length!=11){
            $("#shop_tel").focus();
            return $("#message").html('电话号码只能为11位！');
        }else {
            return $("#message").html("");
        }
    });

    //店铺详情验证
    $("#shop_detail").blur(function (data) {
        var shop_detail = $("#shop_detail").val();
        if (shop_detail==null){
            $("#shop_detail").focus();
            return $("#message").html('店铺详情不能为空！');
        }else {
            return $("#message").html("");
        }
    });
    //验证规则结束---------------------------------------------

    //表单监听提交
    $("form").submit(function(data){
        var formdata = $('#addShopForm').serialize();
        $.ajax({
            url: '/shop/addShop',
            type: 'post',
            dataType: 'json',
            data:formdata,
            success:function (suc) {
                alert(suc.msg)
            }
        })
    });

});

