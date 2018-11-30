$(function () {
    initDataGrid();
});


// 初始化datagrid
function initDataGrid() {
    $("#menuState").combobox({
        valueField: "value",
        textField: "label",
        data: [{
            label: "全部",
            value: ""
        }, {
            label: "开启",
            value: "open"
        }, {
            label: "关闭",
            value: "closed"
        }],
        value: ""
    });

    $('#dg_menu').datagrid({
        //url: '/log',
        method: 'get',
        fit: true,
        fitColumns: true,
        collapsible: true,
        rownumbers: true,
        pagination: true,
        striped: true,
        pageSize: ssm_page_size,
        pageList: ssm_page_list,
        columns: [[
            {field: 'ck', checkbox: true, checked: false},
            {field: 'id', title: '序号', width: '5%'},
            {field: 'parentId', hidden: true},
            {field: 'parentName', title: '父节点菜单', width: '10%', sortable: true},
            {field: 'text', title: '菜单名称', width: '10%', sortable: true},
            {field: 'url', title: 'url', width: '15%', sortable: true},
            {field: 'iconCls', title: '图标', width: '7%', sortable: true},
            {field: 'state', title: '节点状态', width: '6%', sortable: true},
            {field: 'checked', title: '选中状态', width: '6%', sortable: true},
            {field: 'isExpand', title: '打开状态', width: '6%', sortable: true},
            {field: 'attributes', title: '属性', width: '7%', sortable: true},
            {field: 'operUser', title: '操作用户', width: '7%', sortable: true},
            {
                field: 'createTime', title: '创建时间', width: '10%', sortable: true, formatter: function (value, row) {
                    return formatDatebox(value);
                }
            },
            {
                field: 'lastUpdate', title: '更新时间', width: '10%', sortable: true, formatter: function (value, row) {
                    return formatDatebox(value);
                }
            }
        ]],
        onSelect: function (index, row) {

        },
        onLoadSuccess: function (data) {
            //$(this).datagrid('tooltip');
        }
    });
}

/**
 * 查询
 */
function searchMenu() {
    var form = $("#search_form").serializeJSON();
    $('#dg_menu').datagrid({
        url: "/menu/list",
        queryParams: {
            parentModule: form.parentModule,
            text: form.text,
            url: form.url,
            operUser: form.operUser
        },
        onLoadSuccess: function (data) {
            //$(this).datagrid('tooltip');
            $('#dg_menu').datagrid('clearChecked');
            var a = $('#dg_menu').datagrid('getSelections');
        }
    });
}

/**
 * 重置查询条件
 */
function resetMenu() {
    $("#search_form").form('clear');
    $('#dg_menu').datagrid('loadData', {
        rows: [],
        url: ''
    });
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
    debugger
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
        value: '0',
        onChange: function () {
            // 如果新增菜单为一级菜单，
            var parentText = $(this).combobox('getText');
            debugger
            if (parentText === '一级菜单') {
                $("#add_url").textbox({'readonly': true});
            } else {
                $("#add_url").textbox({'readonly': false});
            }
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
        value: 'open'
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
