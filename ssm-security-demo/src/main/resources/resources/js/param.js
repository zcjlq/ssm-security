function initFunctionTree() {
    $('#functionTree').tree({
        url: '/function/tree',
        method: 'get',
        animate: true,
        dnd: true
    })
}

// 初始
$(function () {
    // 初始化时关闭新增窗口
    initAddDialog();
    // init
    initDataGrid();

    initComboBox();

    initFunctionTree();

});

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
            $('#dg').datagrid({singleSelect: (this.value == 0)})
        }, onLoadSuccess: function () {
        }
    })
}

// 初始化收入支出
function initplusMinusComboBox() {
    $('#plusMinus').combobox({
        width: 100,
        valueField: 'value',
        textField: 'label',
        data: [{
            label: '收入',
            value: '0'
        }, {
            label: '支出',
            value: '1',
            "selected": true
        }],
        onchange: function () {
            $('#dg').datagrid({singleSelect: (this.value == 0)})
        }, onLoadSuccess: function () {
        }
    })
}

// 点击增加
function addFun() {
    $('#addDialog').dialog('open');
    var curr_time = new Date();
    $('#addForm').form('clear');
    $("#yearMonth").datebox("setValue", myformatter(curr_time));
    initplusMinusComboBox();
}

// 增加提交
function addForm() {
    console.log($('#yearMonth').val());
    $.post('/param/add', $('#addForm').serializeJSON(), function (obj) {
        $('#addDialog').dialog({closed: true});
        $('#dg').datagrid('reload');
    });
}

// 初始化时关闭新增窗口
function initAddDialog() {
    // 初始化年月
    $('#yearMonth').datebox({
        //显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
        onShowPanel: function () {
            //触发click事件弹出月份层
            span.trigger('click');
            if (!tds)
            //延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                setTimeout(function () {
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function (e) {
                        //禁止冒泡执行easyui给月份绑定的事件
                        e.stopPropagation();
                        //得到年份
                        var year = /\d{4}/.exec(span.html())[0],
                            //月份
                            //之前是这样的month = parseInt($(this).attr('abbr'), 10) + 1;
                            month = parseInt($(this).attr('abbr'), 10);

                        //隐藏日期对象
                        $('#yearMonth').datebox('hidePanel')
                        //设置日期的值
                            .datebox('setValue', year + '-' + month);
                    });
                }, 0);
        },
        //配置parser，返回选择的日期
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth());
        formatter: function (d) {
            var currentMonth = (d.getMonth() + 1);
            var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
            return d.getFullYear() + '-' + currentMonthStr;
        }
    });

    //日期选择对象
    var p = $('#yearMonth').datebox('panel'),
        //日期选择对象中月份
        tds = false,
        //显示月份层的触发控件
        span = p.find('span.calendar-text');
    var curr_time = new Date();

    //设置前当月
    $("#yearMonth").datebox("setValue", myformatter(curr_time));

    $('#addDialog').dialog({
        title: '新增项目',
        iconCls: 'icon-save',
        resizable: true,
        modal: true,
        onResize: function () {
            $(this).dialog('center');
        },
        closed: true
    });

    $('#addDialog').dialog('close');
}

//格式化日期
function myformatter(date) {
    //获取年份
    var y = date.getFullYear();
    //获取月份
    var m = date.getMonth() + 1;
    return y + '-' + m;
}

// 初始化datagrid
function initDataGrid() {
    var dg = $('#dg').datagrid({
        url: '/param/list',
        method: 'get',
        singleSelect: true,
        fit: true,
        fitColumns: true,
        collapsible: true,
        columns: [[
            {field: 'paramType', title: '参数类型', width: '52%', sortable: true},
            {field: 'paramTypeName', title: '类型名称', width: '52%', sortable: true}
        ]],
        onSelect: function (index, row) {
            var dg1 = $('#dg1').datagrid({
                url: '/param/paramDetail',
                queryParams: {'paramType': row.paramType}
            });
            dg1.datagrid('enableFilter')
        }
    });
    dg.datagrid('enableFilter');

    var dg2 = $('#dg1').datagrid({
        singleSelect: true,
        fit: true,
        fitColumns: true,
        collapsible: true,
        multiSort: true,
        columns: [[
            {field: 'paramName', title: '参数类型', width: '20%', sortable: true, frozen: true},
            {field: 'paramValue', title: '类型名称', width: '20%', sortable: true},
            {field: 'remark', title: '备注', width: '20%', sortable: true},
            {field: 'operUser', title: '操作用户', width: '20%', sortable: true},
            {field: 'lastUpdate', title: '最后更新时间', width: '20%', sortable: true, formatter: formatDatebox}
        ]]
    });
}

// 删除
function deleteFun() {
    var rows = $('#dg').datagrid('getSelections');

    var paramIds = [];
    if (rows) {
        for (var i = 0; i < rows.length; i++) {
            var row = rows[i];
            paramIds.push(row.tid);
        }
    }

    if (row) {
        $.messager.confirm('删除', '确定要删除吗？', function (r) {
            if (r) {
                $.ajax({
                    url: '/param/delete',
                    data: {paramIds: paramIds.toString()},
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

function reset() {

}

function searchParam() {

}