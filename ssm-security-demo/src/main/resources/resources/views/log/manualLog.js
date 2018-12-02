$(function () {
    loading_process();
    initDateBox('startDate', 'endDate');
    initSysLogDataGrid('dg_manual_log');
    loading_success();
});
