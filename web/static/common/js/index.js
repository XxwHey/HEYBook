/**
 * Created by Administrator on 2017/3/1.
 */
/*点击导航栏，异步加载页面到主窗口*/
$(function () {
    $('[data-toggle="tooltip"]').tooltip();
    $('#mainContent').height($(window).height() - $('.navbar').height() - 70);
    $('audio').audioPlayer();
});

$("#index").click(function () {
    location.reload();
});

function loadPage(url, param, element) {
    $('#mainContent').html("");
    $.ajax({
        type: "POST",
        url: url,
        data: param,
        dataType: "html",
        cache: false,
        success: function (data) {
            $('#mainContent').html(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            // if (XMLHttpRequest.status == 404) {
            //     $('#mainContent').load('error500.do');
            // } else if (XMLHttpRequest.status == 500) {
            //     $('#mainContent').load('error500.do');
            // }
        }
    });
}

function tabPlay() {
    var player = $("#player")[0];
    player.pause();
    // $("#player").attr("src", "./static/common/audio/yanyuan.mp3");
    player.play();
}

function searchTest() {
    var key = $("#search").val();
    $.ajax({
        type: "post",
        url: "search.do",
        dataType: "json",
        data: {"key": key},
        success: function (j) {
            alert("发送成功");
        },
        error: function () {
            alert("无响应");
        }
    });
}