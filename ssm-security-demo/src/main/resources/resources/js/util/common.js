ssm_page_size = 100;
ssm_page_list = [20, 50, 100, 500, 1000];

// 重写combobox 默认值
$.fn.combobox.defaults.prompt = '--请选择--';
// $.fn.combobox.defaults.editable = true;

// 格式化时间
Date.prototype.format = function (format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }

    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
}

function formatDate(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }

    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}

function msg(data) {
    if (data) {
        return;
    }
    $.messager.alert({
        title: '提示',
        msg: data,
        showType: 'info'
    });
}

function loading_process() {
    $.messager.progress({
        title: '提示',
        msg: '数据加载中，请稍候……',
        text: ''
    });
}

function loading_success() {
    $.messager.progress('close');
}

function reset(formId, datagridId) {
    $("#" + formId).form('clear');
    const dg_menu = $('#' + datagridId);
    dg_menu.datagrid('loadData', {
        url: '',
        total: 0,
        rows: []
    });
    const dg = dg_menu.datagrid('options');
    dg.sortName = '';
    dg.sortOrder = '';
}