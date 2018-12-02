$(function () {
    // loading_process();
    initDataGrid();
    // loading_success()
});

// 初始化datagrid
function initDataGrid() {
    var dg = $('#dg_param_type');
    dg.datagrid({
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
            // loading_process();
            var dg1 = $('#dg_param_value');
            dg1.datagrid({
                url: '/param/paramDetail',
                queryParams: {'paramType': row.paramType},
                onLoadSuccess: function (data) {
                    $('#dg_param_value').datagrid('enableFilter')
                    // loading_success();
                }
            });
        },
        onLoadSuccess: function (data) {
            $('#dg_param_type').datagrid('enableFilter');
        }
    });

    var dg2 = $('#dg_param_value').datagrid({
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