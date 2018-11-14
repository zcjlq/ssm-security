$(function () {
    initDataGrid();
});

function setCurrentDay() {
    $("#startDate").datebox('setValue', formatDate(new Date()));
    $("#endDate").datebox('setValue', formatDate(new Date()));
}

// 初始化datagrid
function initDataGrid() {
    setCurrentDay();
    $("#logType").combobox({
        valueField: "value",
        textField: "label",
        data: [{
            label: "全部",
            value: ""
        }, {
            label: "系统记录",
            value: "1"
        }, {
            label: "手动记录",
            value: "2"
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
        pageSize: ssm_page_size,
        pageList: ssm_page_list,
        columns: [[
            // {field: 'ck', checkbox: true},
            {field: 'tid', title: '序号', width: '5%', hidden: true},
            {field: 'moduleName', title: '模块名称', width: '10%', sortable: true},
            {field: 'operType', title: '操作类型', width: '7%', sortable: true},
            {field: 'controller', title: 'controller', width: '10%', sortable: true},
            {field: 'method', title: 'method', width: '10%', sortable: true},
            {
                field: 'logType', title: '日志类型', width: '7%', sortable: true,
                formatter: function (value, row) {
                    return value === '1' ? '系统记录' : '手动记录';
                }
            },
            {field: 'operUser', title: '操作用户', width: '7%', sortable: true},
            {field: 'ip', title: 'ip', width: '7%', sortable: true},
            {field: 'remark', title: '备注', width: '10%', sortable: true},
            {field: 'useTime', title: '耗时(秒)', width: '8%', sortable: true},
            {
                field: 'startTime', title: '开始时间', width: '10%', sortable: true, formatter: function (value, row) {
                    return formatDatebox(value);
                }
            },
            {
                field: 'endTime', title: '结束时间', width: '10%', sortable: true, formatter: function (value, row) {
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
        url: "/log",
        queryParams: {
            startDate: form.startDate,
            endDate: form.endDate,
            logType: form.logType,
            ip: form.ip,
            moduleName: form.moduleName,
            operUser: form.operUser
        }
    })
}

/**
 * 重置查询条件
 */
function resetCondition() {
    setCurrentDay();
    $("#logType").combobox({value: ""});
    $("#ip").textbox('clear');
    $("#moduleName").textbox('clear');
    $("#operUser").textbox('clear');
}