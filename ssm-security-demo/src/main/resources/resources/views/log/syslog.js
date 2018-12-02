$(function () {
    loading_process();
    initDateBox('startDate', 'endDate');
    initSysLogDataGrid('dg_sys_log');
    loading_success();
});
