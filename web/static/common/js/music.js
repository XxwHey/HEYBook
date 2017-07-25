/**
 * Created by Administrator on 2017/3/1.
 */
//VueJS
new Vue({
    el: "#musicApp",
    data: {
        theads: [
            {name: "NAME"},
            {name: "ARTIST"},
            {name: "ALBUM"},
            {name: "DESCRIPTION"},
            {name: "PLAY"}
        ]
    }
});

$(function () {
    loadData();
});

//控制数据+分页
function loadData(url, param) {
    var dataUrl = (url == null ? "music/list.do" : url);
    $("tbody").html("");
    $.ajax({
        type: "post",
        url: dataUrl,
        dataType: "json",
        data: param,
        success: function (j) {
            var list = j.data.resultList;
            var page = j.data;
            $.each(list, function () {
                $("tbody").append(loadHTML(this));
            });
            //分页
            paging(page, "music/list.do");
        }
    });
}

function loadHTML(data) {
    var html =
        '<tr style="text-align: center">'
        // + '<td style="width: 2%">'
        // + '<div>'
        // + '<input name="checkbox" type="checkbox" class="checkbox-inline">'
        // + '<input name="id" type="hidden" value=' + data.id + '>' + '</div>' + '</td>'
        + '<td style="width: 20%" id="name">' + data.name + '</td>'
        + '<td style="width: 15%" id="artist">' + data.artist + '</td>'
        + '<td style="width: 20%" id="album">' + data.album + '</td>'
        + '<td style="width: 30%; color: #878787" id="description">' + data.description + '</td>'
        + '<td id="playStatus"><span><i class="icon icon-play-circle icon-2x" onclick="tabPlay()"></i></span></td>'
        + '</tr>';
    return html;
}