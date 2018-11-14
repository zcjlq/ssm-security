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

    $('#dg').datagrid({
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
            {field: 'id', title: '序号', width: '5%', hidden: true},
            {field: 'parentId', hidden: true},
            {field: 'parentName', title: '父节点菜单', width: '10%', sortable: true},
            {field: 'text', title: '菜单名称', width: '10%', sortable: true},
            {field: 'iconcls', title: '图标', width: '10%', sortable: true},
            {field: 'url', title: 'url', width: '10%', sortable: true},
            {field: 'state', title: '节点状态', width: '7%', sortable: true},
            {field: 'checked', title: '选中状态', width: '7%', sortable: true},
            {field: 'isExpand', title: '打开状态', width: '7%', sortable: true},
            {field: 'attributes', title: '属性', width: '10%', sortable: true},
            {field: 'operUser', title: '操作用户', width: '10%', sortable: true},
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
function searchLog() {
    var form = $("#search_form").serializeJSON();
    $('#dg').datagrid({
        url: "/menu/list",
        queryParams: {}
    })
}

/**
 * 重置查询条件
 */
function resetCondition() {

}

function addMenu() {
    initParentMenuCombobox();
    initStateCombobox();
    $('#addMenuDialog').dialog({
        closed: false,
        title: "新增菜单",
        buttons: [{
            text: '保存',
            iconCls: 'icon-ok',
            handler: function () {
                submitAddMenuForm();
            }
        }, {
            text: '重置',
            handler: function () {
                // $(this).dialog('closed')
                $('#addMenuForm').form('clear');
                // $('#add_parentMenuId').combobox({value: ''})
            }
        }]

    });

}

function deleteMenu() {

}

function initParentMenuCombobox() {
    $('#add_parentMenuId').combobox({
        url: '/menu/parent',
        method: 'get',
        valueField: 'id',
        textField: 'text',
        limitToList: true,
        value: '0',
        onChange: function () {
            var parentText = $(this).combobox('getText');
            if (parentText === '一级菜单') {
                $("#add_url").textbox({'readonly': true});
            } else {
                $("#add_url").textbox({'readonly': false});
            }
        }
    })
}

function initStateCombobox() {
    $('#add_state').combobox({
        valueField: 'id',
        textField: 'text',
        editable: false,
        data: [
            {value: 'open', text: 'open'},
            {value: 'closed', text: 'closed'}
        ]
    })
}

function submitAddMenuForm() {
    // validate
    $('#addMenuForm').form({
        url: '/menu',
        type: 'post',
        onSubmit: function () {
            return $(this).form('enableValidation').form('validate');
        },
        success: function (data) {

        }
    });
}
