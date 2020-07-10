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
        elem: '#picUrlUpload',
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
                    layer.msg("商品首页展示图为一张", {icon: 5});
                    delete files[index];
                    len = getJsonLength(files);
                }else{
                    $('#picUrlPreview').append('<img id="picUrlRemove_'+index+'" style="width: 256px;' +
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
            picUrlFilePath = res.filePath;
            $("#picUrl").val(picUrlFilePath);
            $("#specificationPic").val(specificationPicFilePath);
            $("#goodsGallery").val(JSON.stringify(goodsGrallyList));
            $("#attributeEntity").val(table2Json("attributeTable"));
            $("#goodsAddSubmit").submit();
        }
    });

    //加载商品分类下拉框
    function init() {
        $.ajax({
            url: '/category/getCategoryPidList',
            type: 'get',
            dataType: 'json',
            success: function (suc) {
                var category1Html = '';
                if(suc.code === 0){
                    category1Html += '<option value="">请选择父类目</option>';
                    $.each(suc.data, function(i, v) {
                        category1Html += '<option value="'+v.categoryId+'">'+v.categoryName+'</option>';
                    });
                }else{
                    category1Html += '<option value="" selected="" disabled="">暂无数据</option>';
                }
                $("#category1").html(category1Html);
                form.render("select");
            }
        });
    }
    //商品分类二级菜单联动
    form.on('select(category1)',function (data) {
        var v = data.value;
        if(v !== ''){
            $("#category2").empty();
            $.ajax({
                url: '/category/getCategoryListByPid',
                type: 'get',
                dataType: 'json',
                data: {'categoryId': v},
                success: function (suc) {
                    var category2Html = '';
                    if(suc.code === 0){
                        category2Html += '<option value="">请选择子类目</option>';
                        $.each(suc.data, function(i, v) {
                            category2Html += '<option value="'+v.categoryId+'">'+v.categoryName+'</option>';
                        });
                    }else{
                        category2Html += '<option value="" selected="" disabled="">暂无数据</option>';
                    }
                    $("#category2").html(category2Html);
                    form.render("select");
                }
            });
        }else{
            $("#category2").empty();
            $("#category2").html('<option value="">请选择子类目</option>');
            form.render("select");
        }
    })

});

