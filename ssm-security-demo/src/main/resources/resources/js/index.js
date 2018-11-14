// 初始
$(function () {
    // 初始化时关闭新增窗口
    initAddDialog();
    // init
    initDataGrid();

    initComboBox();

    initTree();
});

function initTree() {
    $("#tree").tree({
        url: '/menu/tree',
        method: 'get',
        animate: true,
        dnd: true,
        parentField: 'parentId',
        onClick: function (node) {
            if (node.url) {
                openTab(node.text, node.url, node.id);
            }
        },
        onLoad: function (panel) {
            debugger
            console.log(panel)
        }
    })
}

// 打开面板
function openTab(text, url, id) {
    if ($('#tabs').tabs("exists", text)) {
        $('#tabs').tabs("select", text)
    } else {
        var content = "<iframe frameborder='0' scrolling = 'auto' " +
            "style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs('add', {
            title: text,
            closable: true,
            content: content,
            tools: [{
                iconCls: 'icon-mini-refresh',
                handler: function () {
                    var currTab = self.parent.$('#tabs').tabs('getSelected');
                    var url = $(currTab.panel('options').content).attr('src');
                    currTab.find('iframe:first').attr('src', url);
                }
            }]
        })
    }
}

function initComboBox() {
    $('#multiId').combobox({
        width: 100,
        valueField: 'value',
        textField: 'label',
        data: [{
            label: 'Single Row',
            value: '0',
            "selected": true
        }, {
            label: 'Multiple Rows',
            value: '1'
        }],
        onchange: function () {
            console.log('this' + this);
            $('#dg').datagrid({singleSelect: (this.value == 0)})
        }, onLoadSuccess: function () {
        }
    })
}

// 点击增加
function addFun() {
    $('#addDialog').dialog('open');
    $('#addForm').form('clear');
}

// 增加提交
function addForm() {
    $.post('/menu/add', $('#addForm').serializeJSON(), function (obj) {
        console.log(obj);
        $('#addDialog').dialog({closed: true});
        $('#dg').datagrid('reload');
    });
}

// 初始化时关闭新增窗口
function initAddDialog() {
    $('#addDialog').dialog({
        iconCls: 'icon-save',
        onResize: function () {
            $(this).dialog('center');
        },
        closed: true
    });
}

// 初始化datagrid
function initDataGrid() {
    $('#dg').datagrid({
        url: '/menu/list',
        method: 'get',
        singleSelect: false,
        fit: true,
        fitColumns: true,
        rownumbers: true,
        pagination: true,
        multiSort: true,
        columns: [[
            {field: 'ck', checkbox: true},
            {field: 'itemId', title: 'Item ID', width: '14%', sortable: true},
            {field: 'productId', title: 'Product ID', width: '14%', sortable: true},
            {field: 'listPrice', title: 'List Price', width: '14%', align: 'right', sortable: true},
            {field: 'unitCost', title: 'Unit Cost', width: '14%', align: 'right', sortable: true},
            {field: 'attr1', title: 'Attribute', width: '14%'},
            {field: 'status1', title: 'Status', width: '14%'},
            {field: 'lastUpdate', title: 'lastUpdate', width: '14%', formatter: formatDatebox}
        ]]
    })
}

// 删除
function deleteFun() {
    var rows = $('#dg').datagrid('getSelections');

    var itemIds = [];
    if (rows) {
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            itemIds.push(row.itemId);
        }
    }

    if (row) {
        $.messager.confirm('删除', '确定要删除吗？', function (r) {
            if (r) {
                $.ajax({
                    url: '/menu/delete',
                    data: {itemIds: itemIds.toString()},
                    dataType: 'json',
                    success: function (result) {
                        // todo 没有进入成功回调，进入了错误的
                        $('#dg').datagrid('reload');

                        console.log(result);
                        if (result) {
                            $('#dg').datagrid('reload');
                        } else {
                            $.messager.show({    // show error message
                                title: 'Error',
                                msg: result.errorMsg
                            });
                        }
                    }, error: function (obj) {
                        $('#dg').datagrid('reload');
                        console.log('error' + obj)
                    }

                });
            }
        });
    }
}


/**
 * 更换EasyUI主题的方法
 * @param themeName
 * 主题名称
 */
changeTheme = function (themeName) {
    var $easyuiTheme = $('#easyuiTheme');
    var url = $easyuiTheme.attr('href');
    var href = url.substring(0, url.indexOf('themes')) + 'themes/' + themeName + '/easyui.css';
    $easyuiTheme.attr('href', href);
    var $iframe = $('iframe');
    if ($iframe.length > 0) {
        for (var i = 0; i < $iframe.length; i++) {
            var ifr = $iframe[i];
            $(ifr).contents().find('#easyuiTheme').attr('href', href);
        }
    }
    $.cookie('easyuiThemeName', themeName, {
        expires: 7
    });
};
