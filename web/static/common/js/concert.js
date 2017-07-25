/**
 * Created by Administrator on 2017/3/10.
 */
$(function () {
    var today = new Date();
    var time = today.getFullYear() + "-" + (today.getMonth() + 1) + "-" + today.getDate();
    $("#time").html("更新至：" + time);
    loadData();
});
//控制数据+分页
function loadData(url, param) {
    var dataUrl = (url == null ? "concert/list.do" : url);
    $("#concert").html("");
    $.ajax({
        type: "post",
        url: dataUrl,
        dataType: "json",
        data: param,
        success: function (j) {
            var list = j.data.resultList;
            var page = j.data;
            $.each(list, function () {
                $("#concert").append(loadHTML(this));
            });
            //分页
            paging(page, "concert/list.do");
        }
    });
}

function loadHTML(result) {
    var comeFrom = result.from;
    if (comeFrom == "") {
        comeFrom = '未知';
    } else {
        comeFrom = '<a style="text-decoration: none" target="_blank" href="' + result.fromAddress + '" data-placement="right" title="点击进入' + result.from + '首页">'+ result.from + '</a>'
    }
    var html = 
        '<div class="item" style="padding: 20px">'
        + '<div class="item-content">' 
        + '<div class="media pull-left"><img src="' + result.cover + '"></div>'
        + '<div class="text">' 
        + '<div class="list list-condensed">'
        + '<div class="items">' 
        + '<div class="item item-heading">'
        + '<div class="pull-right"><a style="text-decoration: none" target="_blank" href="' + result.detailAddress + '"><small>查看详情</small></a></div>'
        + '<h3 style="text-indent: 1rem">' + result.name + '</h3></div>'
        + '<input id="id" type="hidden" value="' + result.id + '">'
        + '<div id="date" class="item" style="display: inline-block;">时间：' + (result.date == "" ? "待定" : result.date) + '</div>'
        + '<div id="address" class="item" style="display: inline-block; padding-left: 10px">场馆：' + (result.address == "" ? "待定" : result.address) + '</div>'
        + '<br><div id="price" class="item" style="display: inline-block;">价格：' + (result.price == "" ? "待定" : result.price) + '</div>'
        + '<div id="from" class="item" style="display: inline-block; padding-left: 10px">来源：' + comeFrom + '</div>'
        + '<div style="padding-top: 20px; padding-left: 20px; text-indent: 2rem">' + result.description + '...</div>' + '</div>' + '</div>' + '</div>' + '</div>' + '</div>';
    return html;
}