$(function () {

    initDataGrid();
});

var editIndex = undefined;

function initDataGrid() {
    $('#dg').datagrid({
        title: 'datagride增删改查',
        iconCls: 'icon-edit',
        singleSelect: true,
        toolbar: '#tb',
        url: '/param/list',
        method: 'get',
        onClickRow: onClickRow,
        columns: [[
            {field: 'itemid', title: 'Item ID', width: 80},
            {
                field: 'productId',
                title: 'Product',
                width: 120,
                formatter: function (value, row) {
                    return row.productName;
                },
                editor: {
                    field: 'productId',
                    width: 100,
                    type: 'combobox',
                    options: {
                        valueField: 'productId',
                        textField: 'productName',
                        method: 'get',
                        url: '/product/names',
                        required: true
                    }
                }
            },
            {
                field: 'listprice',
                title: 'List Price',
                width: 80,
                align: 'right',
                editor: {type: 'numberbox', options: {precision: 1}}
            },
            {field: 'unitcost', title: 'Unit Cost', width: 80, align: 'right', editor: 'numberbox'},
            {field: 'attr1', title: 'Attribute', width: 250, editor: 'textbox'},
            {
                field: 'status',
                title: 'Status',
                width: 60,
                align: 'center',
                editor: {type: 'checkbox', options: {on: 'P', off: ''}}
            }
        ]]
    });

}

// 是否结束编辑，如果editIndex未定义，返回true
// 如果有值，代表正在编辑，或者编辑完成，进入验证逻辑，验证
function endEditing() {
    if (editIndex === undefined) return true;
    var b = $('#dg').datagrid('validateRow', editIndex);
    console.log('validate:' + b);
    if (b) {
        console.log('进入endEdit validate');
        $('#dg').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    }
    return false;
}

function onClickRow(index) {
    if (editIndex !== index) {
        if (endEditing()) {
            console.log(index);
            $("#dg").datagrid('selectRow', index).datagrid('beginEdit', index);
            editIndex = index;
        } else {
            $("#dg").datagrid('selectRow', index)
        }
    }
}

// 点击新增一行
function append() {
    if (endEditing()) {
        var dg = $("#dg").datagrid('appendRow', {'status': 'P'});
        editIndex = dg.datagrid('getRows').length - 1;
        dg.datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
}

// 移除一行
function removeit() {
    console.log('come into remove,editIndex:' + editIndex);
    if (editIndex === undefined) {
        return;
    }
    console.log("come into remove，editIndex：" + editIndex);
    // 如果editIndex有值，把当前行删除
    $("#dg").datagrid('cancelEdit', editIndex)
        .datagrid('deleteRow', editIndex);
}

// 保存
function accept() {
    if (endEditing()) {
        console.log('come into accept ,editIndex' + editIndex);
        $('#dg').datagrid('acceptChanges');

    }
}