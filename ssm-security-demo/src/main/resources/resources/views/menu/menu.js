$(function () {
    loading_process();
    initParentMenuCombobox('parentModule');
    initDataGrid();
    loading_success();
});


// 初始化datagrid
function initDataGrid() {
    $('#dg_menu').datagrid({
        //url: '/log',
        method: 'get',
        fit: true,
        //fitColumns: true,
        border: false,
        collapsible: true,
        rownumbers: true,
        pagination: true,
        striped: true,
        multiSort: true,
        pageSize: ssm_page_size,
        pageList: ssm_page_list,
        columns: [[
            {field: 'ck', checkbox: true, checked: false},
            {field: 'parentId', hidden: true},
            {field: 'parentName', title: '父节点菜单', width: '10%'},
            {field: 'id', title: '序号', width: '5%'},
            {field: 'text', title: '菜单名称', width: '10%', sortable: true},
            {field: 'url', title: 'url', width: '15%', sortable: true},
            {field: 'iconCls', title: '图标', width: '7%'},
            {field: 'state', title: '节点状态', width: '6%'},
            {field: 'checked', title: '选中状态', width: '6%'},
            {field: 'isExpand', title: '打开状态', width: '6%'},
            // {field: 'attributes', title: '属性', width: '7%', sortable: true},
            {field: 'createUser', title: '创建用户', width: '7%', sortable: true},
            {
                field: 'createTime', title: '创建时间', width: '10%', sortable: true, formatter: function (value, row) {
                    return formatDatebox(value);
                }
            },
            {field: 'operUser', title: '操作用户', width: '7%', sortable: true},
            {
                field: 'lastUpdate', title: '更新时间', width: '10%', sortable: true, formatter: function (value, row) {
                    return formatDatebox(value);
                }
            }
        ]]
    });
}

/**
 * 查询
 */
function searchMenu() {
    loading_process();
    var form = $("#search_form").serializeJSON();
    $('#dg_menu').datagrid({
        url: "/menu/list",
        queryParams: {
            parentModuleId: form.parentModule,
            text: form.text,
            url: form.url,
            operUser: form.operUser
        },
        onLoadSuccess: function (data) {
            var mark = 1;                                    //这里涉及到简单的运算，mark是计算每次需要合并的格子数
            for (var i = 1; i < data.rows.length; i++) {     //这里循环表格当前的数据
                if (data.rows[i]['parentId'] !== 0
                    && data.rows[i]['parentId'] === data.rows[i - 1]['parentId']) {   //后一行的值与前一行的值做比较，相同就需要合并
                    mark += 1;
                    $(this).datagrid('mergeCells', {
                        index: i + 1 - mark,   //datagrid的index，表示从第几行开始合并；紫色的内容需是最精髓的，就是记住最开始需要合并的位置
                        field: 'parentName',   //合并单元格的区域，就是clomun中的filed对应的列
                        rowspan: mark          //纵向合并的格数，如果想要横向合并，就使用colspan：mark
                    });
                } else {
                    mark = 1;                  //一旦前后两行的值不一样了，那么需要合并的格子数mark就需要重新计算
                }
            }
            loading_success();
        }
    });
}

/**
 * 重置查询条件
 */
function resetMenu() {
    reset('search_form', 'dg_menu')
}

/**
 * 新增
 */
function addMenu() {
    $('#addMenuForm').form('clear');
    initParentMenuCombobox('add_parentMenuId');
    initStateCombobox('add_state');
    var addMenuDialog = $('#addMenuDialog');
    addMenuDialog.dialog({
        title: "新增菜单",
        closed: false,
        height: 400,
        width: 400,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        modal: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            plain: true,
            handler: function () {
                submitAddMenuForm();
            }
        }, {
            text: '关闭',
            plain: true,
            iconCls: 'icon-undo',
            handler: function () {
                $('#addMenuDialog').dialog({'closed': true});
                $('#addMenuForm').form('clear');
                // $('#add_parentMenuId').combobox({value: ''})
            }
        }]
    });
    addMenuDialog.dialog('center')
    // var a = $(document).scrollTop() + ($(window).height() - 400) * 0.5;
    // $('#addMenuDialog').panel("move", {top: a});
}

/**x
 * 修改
 */
function updateMenu() {
    // 只能选择一行
    var dg_menu = $('#dg_menu');
    var rows = dg_menu.datagrid('getSelections');
    var ids = [];
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    if (ids == null || ids.length > 1) {
        $.messager.alert('警告', '只能选择一条记录', 'error');
        return;
    }

    var row = dg_menu.datagrid('getSelected');
    //
    if (!row) {
        return;
    }
    var updateMenuForm = $('#updateMenuForm');
    var updateMenuDialog = $('#updateMenuDialog');
    updateMenuDialog.dialog({
        title: "新增菜单",
        closed: false,
        height: 400,
        width: 400,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        modal: true,
        buttons: [{
            text: '保存',
            iconCls: 'icon-save',
            plain: true,
            handler: function () {
                submitUpdateMenuForm();
            }
        }, {
            text: '关闭',
            plain: true,
            iconCls: 'icon-undo',
            handler: function () {
                updateMenuDialog.dialog({'closed': true});
                updateMenuForm.form('clear');
                // $('#add_parentMenuId').combobox({value: ''})
            }
        }]
    });

    // 加载父菜单
    initParentMenuCombobox('update_parentMenuId');
    $('#update_state').combobox({'value': row.state});
    updateMenuForm.form('load', row);
    $('#update_parentMenuId').combobox({'value': row.parentId});
    initStateCombobox('update_state');
    updateMenuDialog.dialog('open').dialog('center');
}

/**
 * 删除
 */
function deleteMenu() {
    // 至少选择一条记录
    var dg_menu = $('#dg_menu');
    var rows = dg_menu.datagrid('getSelections');
    var ids = [];
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    if (ids == null || ids.length < 1) {
        $.messager.alert('警告', '至少选择一条记录', 'error');
        return;
    }

    // var ps = $.param(ids.join(), true);
    // 删除确认
    $.messager.confirm('删除', '确定删除吗?', function (r) {
        if (r) {
            $.ajax({
                url: '/menu/delete',
                data: {ids: ids.join(',')},
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    $('#dg_menu').datagrid('reload');
                    // console.log(data);
                    $.messager.alert('提示', data);
                }
            })
        }
    });
}

function initParentMenuCombobox(id) {
    $('#' + id).combobox({
        url: '/menu/parent',
        method: 'get',
        valueField: 'id',
        textField: 'text',
        limitToList: true,
        onLoadSuccess: function (data) {
            $("#search_form").form('clear');
        }
    })
}

function initStateCombobox(id) {
    $('#' + id).combobox({
        valueField: 'id',
        textField: 'text',
        editable: false,
        data: [
            {id: 'open', text: 'open'},
            {id: 'closed', text: 'closed'}
        ],
        value: ''
    })
}

// 新增提交表单
function submitAddMenuForm() {
    var addMenuForm = $('#addMenuForm');
    // var data = addMenuForm.serializeJSON();
    addMenuForm.form({
        url: '/menu',
        type: 'post',
        onSubmit: function () {
            return $(this).form('enableValidation').form('validate');
        },
        success: function (data) {
            $.messager.alert('提示', data);
            $('#addMenuDialog').dialog({'closed': true});
            $('#dg_menu').datagrid('reload')
        }
    });
    addMenuForm.submit();
}

function submitUpdateMenuForm() {
    var updateMenuForm = $('#updateMenuForm');
    // var data = addMenuForm.serializeJSON();
    updateMenuForm.form({
        url: '/menu/update',
        type: 'post',
        onSubmit: function () {
            return $(this).form('enableValidation').form('validate');
        },
        success: function (data) {
            $.messager.alert('提示', data);
            $('#updateMenuDialog').dialog({'closed': true});
            $('#dg_menu').datagrid('reload')
        }
    });
    updateMenuForm.submit();
}
