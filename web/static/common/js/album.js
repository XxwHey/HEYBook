/**
 * Created by Administrator on 2017/3/10.
 */
//@ sourceURL = album.js
$(function () {
    loadData();
    $(".carousel").height($(window).height());
});

//控制数据+分页
function loadData(url, param) {
    var dataUrl = (url == null ? "album/list.do" : url);
    $(".cards").html("");
    $.ajax({
        type: "post",
        url: dataUrl,
        dataType: "json",
        data: param,
        success: function (j) {
            var list = j.data;
            var size = list.length;
            var total = size;
            var count;
            for (var i = 0; i <= total / 8; i++) {
                var cards = i + 1;
                if (size > 0) {
                    count = 0;
                    $.each(list, function (index, item) {
                        if (index == 8 || item == undefined) {
                            return false;
                        }
                        $("#cards" + cards).append(loadHTML(this));
                        count++;
                    });
                    size -= 8;
                    list.splice(0, count);
                }
            }
        }
    });
}

function loadHTML(result) {
    var html = 
        '<div class="col-md-4 col-sm-6 col-lg-3 album" style="padding-right: 10%; padding-bottom: 2rem;">'
        + '<a class="card" href="#" style="text-decoration: none" onclick="showDetails(this)">'
        + '<input type="hidden" id="id" value="' + result.id + '">'
        + '<div class="media-wrapper"><img src="' + result.address + '" alt=""></div>'
        + '<div class="caption">歌曲数：' + result.quantity + '</div>'
        + '<div class="card-heading"><strong>' + result.name + '</strong></div>'
        + '<div class="card-content text-muted">语言：' + result.language + '<br>' + '发行：' + result.createTime + '</div>'
        + '</a></div>';
    return html;
}

function showDetails(element) {
    var id = $(element).find("input[type=hidden]").val();
    $("#dHead").html('');
    $("#dItems").html('');
    $(".modal-body").mLoading('show');
    $.ajax({
        type: "post",
        url: "album/details.do?id=" + id,
        dataType: "json",
        success: function (j) {
            var album = j.data[0];
            var musics = j.data[1];
            $("#dHead").html('<div class="media pull-left"><img src="' + album.address + '" style="width: 100px; height: 100px"></div>' +
                '<div style="padding-top: 7px"><h3 class="list-group-item-heading">&nbsp;&nbsp;' + album.name + '</h3>' +
                '<br><p style="display: inline-block; width: 30%; padding-left: 10px">语言：' + album.language + '</p>' +
                '<p style="display: inline-block;">唱片公司：' + album.company + '</p>' +
                '<br><p style="display: inline-block; width: 30%; padding-left: 10px">歌曲数：' + album.quantity + '</p>' +
                '<p style="display: inline-block;">发行时间：' + album.createTime + '</p></div>');
            if (j.data[2] == 0) {
                $("#dItems").html("<p style='margin: 0 auto'>暂无歌曲信息</p>");
            } else {
                $.each(musics, function (index) {
                    $("#dItems").append(loadDetails(this, index + 1));
                })
            }
            $(".modal-body").mLoading('hide');
        }
    });
    $("#detail").modal('show');
}

function loadDetails(result, index) {
    var html =
        '<div class="item"><h5 style="padding-bottom: 2px; margin: 0; width: 50%">'
        + index + '、' + result.name + '</h5>'
        + '<p style="margin: 0; color: #cccccc; text-indent: 1rem">' + result.description + '</p>'
        + '</div>';
    return html;
}