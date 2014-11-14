/**
 * Created by shizhenchao on 2014-9-26.
 */
var myTreeId = "menusTreeForDeal"
var setting = {
    view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: false,
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        onDrag: zTreeOnDrag
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "pid",
            rootPId: ""
        }
    },
    check: {
        enable: true
    },
    edit: {
        enable: true
    },
    callback: {
        beforeRemove: beforeRemove,
        beforeRename: beforeRename,
        beforeEditName: beforeEditName,
        onRename: zTreeOnRename,
        onRemove: zTreeOnRemove,
        beforeDrop: zTreeBeforeDrop,
        onDrop: zTreeOnDrop
    }
};
var log, className = "dark";
/**
 * 编辑之前
 * @param treeId
 * @param treeNode
 * @returns {*}
 */
function beforeEditName(treeId, treeNode) {
    className = (className === "dark" ? "" : "dark");
    var zTree = $.fn.zTree.getZTreeObj(myTreeId);
    zTree.selectNode(treeNode);
    return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
}
/**
 * 移除之前
 * @param treeId
 * @param treeNode
 * @returns {*}
 */
function beforeRemove(treeId, treeNode) {
    className = (className === "dark" ? "" : "dark");
    var zTree = $.fn.zTree.getZTreeObj(myTreeId);
    zTree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}
/**
 * 重命名之前
 * @param treeId
 * @param treeNode
 * @param newName
 * @param isCancel
 * @returns {boolean}
 */
function beforeRename(treeId, treeNode, newName, isCancel) {
    className = (className === "dark" ? "" : "dark");
    if (newName.length == 0) {
        alert("节点名称不能为空.");
        var zTree = $.fn.zTree.getZTreeObj(myTreeId);
        setTimeout(function () {
            zTree.editName(treeNode)
        }, 10);
        return false;
    }
    return true;
}
/**
 * 重命名
 */
function zTreeOnRename(event, treeId, treeNode, isCancel) {
    $.post("../menus/rename", {"menu.id": treeNode.id, "menu.name": treeNode.name}, function (r) {
            //回调
            if (r == "yes") {
                var treeObj = $.fn.zTree.getZTreeObj(myTreeId);
                treeObj.refresh();
            } else {

            }
        },
        "text");
}
/**
 * 删除
 */
function zTreeOnRemove(event, treeId, treeNode) {
    $.post("../menus/remove", {"menu.id": treeNode.id}, function (r) {
    });
}
/**
 * 自定义图标
 */
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    if (treeNode.editNameFlag || $("#" + treeNode.tId + "_add").length > 0) return;
    var addStr = "<span class='button add' id='" + treeNode.tId + "_add"
        + "' title='增加节点' onfocus='this.blur();'></span>";
    sObj.after(addStr);
    var btn = $("#" + treeNode.tId + "_add");
    if (btn) btn.bind("click", function () {
        var zTree = $.fn.zTree.getZTreeObj(myTreeId);
        $("#pid").val(treeNode.id)
        //创建dialog
        $("#treeNodeAddDialog").dialog({
            autoOpen: true,
            modal: true,
            show: {
                effect: "blind",
                duration: 1000
            },
            hide: {
                effect: "explode",
                duration: 1000
            },
            buttons: [
                {
                    text: "新增节点",
                    click: function () {
                        var data = $("#treeNodeAddForm").serialize();
                        console.log(data);
                        $.post("../menus/add", data, function (r) {
                            $("#treeNodeAddDialog").dialog('close');
                            zTree.addNodes(treeNode, {pId: treeNode.id, name: $("#name").val()});
                        });
                    }
                },
                {
                    text: "取消",
                    click: function () {
                        $(this).dialog('close');
                    }
                }
            ]
        });
        return false;
    });
};
/**
 * 自定义图标鼠标移除效果
 */
function removeHoverDom(treeId, treeNode) {
    $("#" + treeNode.tId + "_add").unbind().remove();
};
/**
 * 拖拽之前
 * @param treeId
 * @param treeNodes
 * @param targetNode
 * @param moveType
 * @returns {boolean}
 */
function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
    //禁止拖拽成为根节点
    return !(targetNode == null || (moveType != "inner" && !targetNode.parentTId));
};
/**
 * 拖拽操作
 * @param event
 * @param treeId
 * @param treeNodes
 */
function zTreeOnDrag(event, treeId, treeNodes) {

};
/**
 * drop操作
 * @param event
 * @param treeId
 * @param treeNodes
 * @param targetNode
 * @param moveType
 */
function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
    var object = treeNodes[0];
    console.log(object)
    //执行update操作
    $.post("../menus/dragAndDrop", {"menu.id": object.id, "menu.targetNodeId": targetNode.id})
};
/**
 * 创建tree的方法
 */
function createTree() {
    $.ajax({
        url: '../menus/menusTreeForDeal',
        type: 'POST',
        dataType: 'json',
        ContentType: 'application/json; charset=utf-8',
        success: function (data) {
            var zNodes = data;
            $.fn.zTree.init($('#menusTreeForDeal'), setting, zNodes);
        },
        error: function (msg) {
            alert('指标树加载异常！');
        }
    });
}
var selectTreeNodes = [];
$(document).ready(function () {
    createTree();
    $("#btn").button().click(function () {
        $("#btnDialog").dialog({
            beforeClose: function (event, ui) {
                //关闭之前保存已经选择的节点
                var treeObj = $.fn.zTree.getZTreeObj("ulTree2");
                var selectTreeNodeArr = treeObj.getCheckedNodes(true);
                for (var i = 0; i < selectTreeNodeArr.length; i++) {
                    selectTreeNodes.push(selectTreeNodeArr[i]);
                }
            },
            open: function (e, ui) {
                $.ajax({
                    url: '../menus/menusTreeForDeal',
                    type: 'POST',
                    dataType: 'json',
                    ContentType: 'application/json; charset=utf-8',
                    success: function (data) {
                        var zNodes = data;
                        $.fn.zTree.init($('#ulTree2'), setting, zNodes);
                        var treeObj = $.fn.zTree.getZTreeObj("ulTree2");
                        if (selectTreeNodes.length == 0) {
                            //全选
                            treeObj.checkAllNodes(true);
                        } else {
                            //选中历史
                            for (var i = 0; i < selectTreeNodes.length; i++) {
                                treeObj.checkNode(selectTreeNodes[i], true, true);
                            }
                        }
                    },
                    error: function (msg) {
                        alert('指标树加载异常！');
                    }
                });
            }
        });
    });
});
/**
 * js数组操作
 */
Array.prototype.clear = function () {
    this.length = 0;
}
Array.prototype.insertAt = function (index, obj) {
    this.splice(index, 0, obj);
}
Array.prototype.removeAt = function (index) {
    this.splice(index, 1);
}
Array.prototype.remove = function (obj) {
    var index = this.indexOf(obj);
    if (index >= 0) {
        this.removeAt(index);
    }
}

