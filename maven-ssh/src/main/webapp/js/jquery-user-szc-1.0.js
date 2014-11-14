/**
 * Created by shizhenchao on 2014-9-25.
 */
$(function () {
    jQuery("#user-list").jqGrid({
        url: '../user/userGrid',
        datatype: "json",
        mtype: "post",
        colNames: ['编 号', '照 片', '账 号', '密 码', '出生年月', 'QQ', "邮箱", "性别"],
        colModel: [
            {name: 'id', index: 'id', width: 55, hidden: true, editable: true, editoptions: {readonly: true}, formoptions: { rowpos: 0, elmprefix: "", elmsuffix: "用户不必填写" }},
            {name: 'pic', index: 'invdate', width: 90, align: 'center', editable: true, edittype: 'file', editrules: {required: true}, formatter: picFormatter, formoptions: { rowpos: 0, elmprefix: "", elmsuffix: "<button>上传</button>" }},
            {name: 'username', index: 'invdate', width: 90, editable: true, editrules: {required: true}},
            {name: 'password', index: 'password asc, invdate', width: 100, editable: true, edittype: "password", editrules: {required: true}},
            {name: 'birthday', index: 'invdate', width: 80, editable: true, editoptions: {
                dataInit: function (el) {
                    $(el).datepicker({dateFormat: 'yy-mm-dd'});
                },
                defaultValue: function () {
                    var currentTime = new Date();
                    var month = parseInt(currentTime.getMonth() + 1);
                    month = month <= 9 ? "0" + month : month;
                    var day = currentTime.getDate();
                    day = day <= 9 ? "0" + day : day;
                    var year = currentTime.getFullYear();
                    return year + "-" + month + "-" + day;
                } },
                formoptions: {
                    rowpos: 0,
                    elmprefix: "",
                    elmsuffix: ""
                },
                editrules: {
                    required: true
                }
            },
            {name: 'qq', index: 'invdate', width: 90, editable: true, editrules: {required: true}},
            {name: 'email', index: 'invdate', width: 90, editable: true, editrules: {required: true}},
            {name: 'sex', index: 'invdate', width: 90, formatter: sexFormatter, editable: true, edittype: "select", editoptions: {value: {0: '男', 1: '女'}}, editrules: {required: true}}
        ],
        multiselect: true,
        rowNum: 10,
        editurl: "../user/operate",
        rowList: [10, 20, 30],
        pager: '#user-pager',
        sortname: 'id',
        viewrecords: true,
        autowidth: true,
        width: 700,
        height: '100%',
        loadonce: true,
        rownumbers: true,
        sortorder: "desc",
        prmNames: {id: "id"},
        caption: "用户列表",
        singleselect: true,
        jsonReader: {
            root: "rows",
            page: "page",
            total: "total",
            records: "records",
            repeatitems: false,
            cell: "rows",
            id: "id"
        }
    });
    jQuery("#user-list").jqGrid('navGrid', '#user-pager', {view: true}, //options
        {jqModal: true, checkOnUpdate: true, savekey: [true, 13], navkeys: [true, 38, 40], checkOnSubmit: true, reloadAfterSubmit: true, closeOnEscape: true, bottominfo: "Fields marked with (*) are required"}, // edit options
        {jqModal: true, checkOnUpdate: true, savekey: [true, 13], navkeys: [true, 38, 40], checkOnSubmit: true, reloadAfterSubmit: true, closeOnEscape: true, bottominfo: "Fields marked with (*) are required"}, // add options
        {reloadAfterSubmit: false, jqModal: false, closeOnEscape: true}, // del options
        {closeOnEscape: true}, // search options
        {navkeys: [true, 38, 40], height: 250, jqModal: false, closeOnEscape: true} // view options
    );
});
//sex格式化
function sexFormatter(cellvalue, options, rowObject) {
    if (cellvalue == 0) {
        return "男"
    } else {
        return "女";
    }
}//pic格式化
function picFormatter(cellvalue, options, rowObject) {
    return "<img src='../images/user/" + cellvalue + "'/>"
}