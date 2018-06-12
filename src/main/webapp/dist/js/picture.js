$(function () {
    //隐藏弹框
    $('#pictureModal').modal('hide');
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    //modal绑定hide事件
    $('#pictureModal').on('hide.bs.modal', function () {
        reset();
    })
    $("#jqGrid").jqGrid({
        url: 'pictures/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, sortable: false, key: true},
            {label: '图片地址', name: 'path', index: 'path', sortable: false, width: 105, formatter: imgFormatter},
            {label: '添加时间', name: 'time', index: 'time', sortable: false, width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    function imgFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"120\" width=\"135\"/>";
    }

    new AjaxUpload('#upload', {
        action: 'images',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                swal('只支持jpg、png、gif格式的图片！', {
                    icon: "error",
                });
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r.code == 200) {
                swal("上传成功", {
                    icon: "success",
                });
                $("#picturePath").val(r.url);
                $("#img").attr("src", r.url);
                $("#img").attr("style", "width: 100px;height: 100px;display:block;");
                return false;
            } else {
                swal(r.msg, {
                    icon: "error",
                });
            }
        }
    });

});


//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObject()) {
        //一切正常后发送网络请求
        //ajax
        var id = $("#pictureId").val();
        var picturePath = $("#picturePath").val();
        var pictureUrl = $("#pictureUrl").val();
        var data = {"path": picturePath, "url": pictureUrl};
        var url = 'pictures/save';
        //id>0表示编辑操作
        if (id > 0) {
            data = {"id": id, "path": picturePath, "url": pictureUrl};
            url = 'pictures/update';
        }
        $.ajax({
            type: 'POST',//方法类型
            dataType: "json",//预期服务器返回的数据类型
            url: url,//url
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (result) {
                console.log(result);//打印服务端返回的数据
                if (result.code == 200) {
                    $('#pictureModal').modal('hide');
                    swal("保存成功", {
                        icon: "success",
                    });
                    //reload
                    reload();
                }
                else {
                    $('#pictureModal').modal('hide');
                    swal("保存失败", {
                        icon: "error",
                    });
                    //reload
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });

    }
});

function pictureAdd() {
    reset();
    $('.modal-title').html('图片添加');
    $('#pictureModal').modal('show');
}

function pictureEdit() {
    reset();
    $('.modal-title').html('图片编辑');

    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    //请求数据
    $.get("pictures/info/" + id, function (r) {
        if (r.code == 200 && r.picture != null) {
            //填充数据至modal
            $('#pictureId').val(r.picture.id);
            $('#picturePath').val(r.picture.path);
            $('#pictureUrl').val(r.picture.url);
        }
    });
    //显示modal
    $('#pictureModal').modal('show');
}

/**
 * 数据验证
 */
function validObject() {
    var picturePath = $('#picturePath').val();
    if (isNull(picturePath)) {
        showErrorInfo("图片不能为空!");
        return false;
    }
    var pictureUrl = $('#pictureUrl').val();
    if (isNull(pictureUrl)) {
        showErrorInfo("图片跳转路径不能为空!");
        return false;
    }
    if (!validLength(pictureUrl, 120)) {
        showErrorInfo("跳转路径长度不能大于120!");
        return false;
    }
    if (!validLength(picturePath, 120)) {
        showErrorInfo("图片上传有误!");
        return false;
    }
    if (!checkUrl(pictureUrl)) {
        showErrorInfo("跳转路径有误!");
        return false;
    }
    return true;
}

/**
 * 重置
 */
function reset() {
    //隐藏错误提示框
    $('.alert-danger').css("display", "none");
    //清空数据
    $('#pictureId').val(0);
    $('#picturePath').val('');
    $('#pictureUrl').val('');
    $("#img").attr("style", "display:none;");
}

function deletePicture() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
        if (flag) {
            $.ajax({
                type: "POST",
                url: "pictures/delete",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.code == 200) {
                        swal("删除成功", {
                            icon: "success",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        swal(r.msg);
                    }
                }
            });
        }
    });
}

/**
 * jqGrid重新加载
 */
function reload() {
    reset();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}