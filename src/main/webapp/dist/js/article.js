var editor;
$(function () {
    //隐藏弹框
    $('#articleModal').modal('hide');
    //详情编辑器

    editor = KindEditor.create('textarea[id="editor"]', {
        items: ['source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'multiimage',
            'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
            'anchor', 'link', 'unlink'],
        uploadJson: 'images',
        filePostName: 'file',
        fileManagerJson: 'images',
        allowFileManager: true
    });

    $('#articleModal').on('hidden.bs.modal', function () {
        editor.html('请输入...');
    })

    //隐藏错误提示框
    $('.alert-danger').css("display", "none");

    $("#jqGrid").jqGrid({
        url: 'articles/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '文章标题', name: 'articleTitle', index: 'articleTitle', width: 240},
            {label: '发布时间', name: 'articleCreateDate', index: 'articleCreateDate', width: 120},
            {label: '添加人', name: 'addName', index: 'addName', width: 120}
        ],
        viewrecords: true,
        height: 360,
        rowNum: 10,
        rowList: [10, 20, 50],
        rownumbers: false,
        rownumWidth: 20,
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

});

//绑定modal上的保存按钮
$('#saveButton').click(function () {
    //验证数据
    if (validObject()) {
        //一切正常后发送网络请求
        //ajax
        var id = $("#articleId").val();
        var title = $("#articleName").val();
        var addName = $("#articleAuthor").val();
        var content = editor.html();
        var data = {"articleTitle": title, "articleContent": content, "addName": addName};
        var url = 'articles/save';
        //id>0表示编辑操作
        if (id > 0) {
            data = {"id": id, "articleTitle": title, "articleContent": content, "addName": addName};
            url = 'articles/update';
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
                    $('#articleModal').modal('hide');
                    swal("保存成功", {
                        icon: "success",
                    });
                    //reload
                    reload();
                }
                else {
                    $('#articleModal').modal('hide');
                    swal("保存失败", {
                        icon: "error",
                    });
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

function articleAdd() {
    reset();
    $('.modal-title').html('文章添加');
    $('#articleModal').modal('show');
}

function articleEdit() {
    reset();
    $('.modal-title').html('文章编辑');

    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    //请求数据
    $.get("articles/info/" + id, function (r) {
        if (r.code == 200 && r.article != null) {
            //填充数据至modal
            $('#articleId').val(r.article.id);
            $('#articleName').val(r.article.articleTitle);
            $('#articleAuthor').val(r.article.addName);
            editor.html('');
            editor.html(r.article.articleContent);
        }
    });
    //显示modal
    $('#articleModal').modal('show');
}

/**
 * 数据验证
 */
function validObject() {
    var articleName = $('#articleName').val();
    if (isNull(articleName)) {
        showErrorInfo("标题不能为空!");
        return false;
    }
    if (!validLength(articleName, 20)) {
        showErrorInfo("标题字符不能大于20!");
        return false;
    }
    var articleAuthor = $('#articleAuthor').val();
    if (isNull(articleAuthor)) {
        showErrorInfo("作者不能为空!");
        return false;
    }
    if (!validLength(articleAuthor, 10)) {
        showErrorInfo("作者字符不能大于10!");
        return false;
    }
    var ariticleContent = editor.html();
    if (isNull(ariticleContent) || ariticleContent == '请输入...') {
        showErrorInfo("内容不能为空!");
        return false;
    }
    if (!validLength(articleAuthor, 1000)) {
        showErrorInfo("内容字符不能大于1000!");
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
    $('#articleId').val(0);
    $('#articleName').val('');
    $('#articleAuthor').val('');
    $('#ariticleContent').val('');
}

function deleteArticle() {
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
                url: "articles/delete",
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