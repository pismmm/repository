/**
 * ztree
 * @type {{view: {selectedMulti: boolean, showLine: boolean}, async: {enable: boolean, url: string, autoParam: string[]}, simpleData: {enable: boolean, idKey: string, pIdKey: string, rootPId: number}, callback: {onClick: zTreeOnClick}}}
 */
var setting = {
    view: {
        selectedMulti: false,
        showLine: false//不显示连接线
    },
    async: {
        enable: true,
        url: "../menus/loadMenuTree",
        autoParam: ["id"]
    },
    simpleData: {
        enable: true,
        idKey: "id",
        pIdKey: "pid",
        rootPId: 0
    },
    callback: {
        onClick: zTreeOnClick

    }
};
function zTreeOnClick(event, treeId, treeNode) {
    //在右侧创建tab页面
    if (!treeNode.isParent) {
        addTab(treeNode.name, treeNode.webSite, treeNode.id);
    }
}
/**
 * tabs
 * @type {string}
 */
var tabTemplate = "<li><a href='@{href}' id='@{id}'>@{label}</a><span class=\"ui-icon ui-icon-close\" role=\"presentation\">移除标签页</span></li>";
var index = 1;
// 实际的 addTab 函数：使用上面表单的输入添加新的标签页
function addTab(title, website, id) {
    var tabs = $("#tabs");
    var tabsAnchor = tabs.find(".ui-tabs-anchor");
    var isExist = false;
    //判断tab是否已经处于打开状态
    if (tabsAnchor.length > 0) {
        for (var i = 0; i < tabsAnchor.length; i++) {
            if ($(tabsAnchor[i]).html() != title) {
                continue;
            } else {
                isExist = true;
                break;
            }
        }
    }
    if (isExist) {
        $("#tabs-" + id).click();
    } else {
        var tabsLength = tabs.find(".ui-tabs-anchor").length;
        id = "tabs-" + id;
        var li = $(tabTemplate.replace(/@\{id\}/g, id).replace(/@\{href\}/g, website).replace(/@\{label\}/g, title));
        tabs.find(".ui-tabs-nav").append(li);
        tabs.tabs("refresh");
        tabs.tabs({ active: tabsLength });
    }
}
/**
 * 初始化方法
 */
$(document).ready(function () {
    $("body").layout({
        applyDefaultStyles: false,
        scrollToBookmarkOnLoad: false,//页加载时滚动到标签
        showOverflowOnHover: false,//鼠标移过显示被隐藏的，只在禁用滚动条时用。
        north__closable: false,//可以被关闭
        north__resizable: false,//可以改变大小
        north__size: 50,//pane的大小
        spacing_open: 8,//边框的间隙
        spacing_closed: 60,//关闭时边框的间隙
        resizerTip: "可调整大小",//鼠标移到边框时，提示语
        resizerCursor: "resize-p",// 鼠标移上的指针样式
        resizerDragOpacity: 0.9,//调整大小边框移动时的透明度
        maskIframesOnResize: "#ifa",//在改变大小的时候，标记iframe（未通过测试）
        sliderTip: "显示/隐藏侧边栏",//在某个Pane隐藏后，当鼠标移到边框上显示的提示语。
        sliderCursor: "pointer",//在某个Pane隐藏后，当鼠标移到边框上时的指针样式。
        slideTrigger_open: "dblclick",//在某个Pane隐藏后，鼠标触发其显示的事件。(click", "dblclick", "mouseover)
        slideTrigger_close: "click",//在某个Pane隐藏后，鼠标触发其关闭的事件。("click", "mouseout")
        togglerTip_open: "关闭",//pane打开时，当鼠标移动到边框上按钮上，显示的提示语
        togglerTip_closed: "打开",//pane关闭时，当鼠标移动到边框上按钮上，显示的提示语
        togglerLength_open: 100,//pane打开时，边框按钮的长度
        togglerLength_closed: 200,//pane关闭时，边框按钮的长度
        hideTogglerOnSlide: true,//在边框上隐藏打开/关闭按钮(测试未通过)
        togglerAlign_open: "left",//pane打开时，边框按钮显示的位置
        togglerAlign_closed: "right",//pane关闭时，边框按钮显示的位置
        togglerContent_open: "<div style='background:red;height: 100%' class='ui-resizable'>AAA</div>",//pane打开时，边框按钮中需要显示的内容可以是符号"<"等。需要加入默认css样式.ui-layout-toggler .content
        togglerContent_closed: "<img src='/images/logo.jpg'/>",//pane关闭时，同上。
        enableCursorHotkey: true,//启用快捷键CTRL或shift + 上下左右。
        customHotkeyModifier: "shift",//自定义快捷键控制键("CTRL", "SHIFT", "CTRL+SHIFT"),不能使用alt
        south__customHotkey: "shift+0",//自定义快捷键（测试未通过）
        fxName: "drop",//打开关闭的动画效果
        fxSpeed: "slow"//动画速度
        //fxSettings: { duration: 500, easing: "bounceInOut" }//自定义动画设置(未通过测试)
        //initClosed:true,//初始时，所有pane关闭
        //initHidden:true //初始时，所有pane隐藏
        //onresize :ons,//调整大小时调用的函数
        //onshow_start:start,
        //onshow_end:end
    });
    //加载tree
    $.fn.zTree.init($("#tree"), setting);
    //$.fn.zTree.getZTreeObj("tree").expandAll(true);
    //展开所有tree
    var treeObj = $.fn.zTree.getZTreeObj("tree");
    treeObj.expandAll(true);
    //初始化tabs
    var tabs = $("#tabs").tabs({
        cache: false,
        //active: true,
        collapsible: true,//折叠
        cookie: null,//保存最后一次选项卡到cookie中
        fx: {
            opacity: "toggle",
            duration: "slow"
        },
        event: "click",//激活选项卡的方式，默认是click
        heightStyle: "content",//选项卡的高度，三个参数，fill、auto、content
        hide: "slideUp",
        show: "blind",
        activate: function (event, ui) {//设置选项卡激活后的回调方法
            //alert("选项卡已激活");
        },
        beforeActivate: function (event, ui) {//设置选项卡即将激活的回调方法
            // alert("选项卡即将激活");
        },
        beforeLoad: function (event, ui) {//设置选项加载面板之前的回调方法
            //alert("选项卡内容即将加载");
        }
    });
    //可以调整位置
    tabs.find(".ui-tabs-nav").sortable({
        axis: "x",
        stop: function () {
            tabs.tabs("refresh");
        }
    });
    // 关闭图标：当点击时移除标签页
    tabs.delegate("span.ui-icon-close", "click", function () {
        var panelId = $(this).closest("li").remove().attr("aria-controls");
        $("#" + panelId).remove();
        tabs.tabs("refresh");
    });
    //右键菜单
    $('.ui-tabs-nav').contextMenu('myMenu', {
        bindings: {
            'open': function (t) {
                alert('Trigger was ' + t.id + '\nAction was Open');
            },
            'email': function (t) {
                alert('Trigger was ' + t.id + '\nAction was Email');
            },
            'save': function (t) {
                alert('Trigger was ' + t.id + '\nAction was Save');
            },
            'delete': function (t) {
                alert('Trigger was ' + t.id + '\nAction was Delete');
            }

        }

    });
    $("button").button();
});
