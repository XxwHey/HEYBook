/**
 * Created by Administrator on 2017/3/21.
 */
var nextPage = null;
var isTotal = false;
$(function () {
    loadData("photo/list.do");
});

// $(".photos").infinitescroll({
//     navSelector: '#pageNav',
//     nextSelector: '#pageNav a',
//     itemSelector: '.box',
//     extraScrollPx: "0",
//     loading: {
//         finishedMsg: "加载完成",
//         loadingText: "加载中"
//     }
// }, function (newElements) {
//     var $newElems = $(newElements).css({opacity: 0});
//     $newElems.imagesLoaded(function () {
//         $newElems.animate({opacity: 1});
//         $(".photos").masonry('appended', $newElems, true);
//     });
// });

function loadData(url, param) {
    isTotal = false;
    $.ajax({
        type: "post",
        url: url,
        dataType: "json",
        data: param,
        success: function (j) {
            var page = j.data[0];
            var total = j.data[1];
            if (page >= total) {
                isTotal = true;
            } else {
                nextPage = page + 1;
            }
            // $("#pageNav a").attr("href", "photo/list.do?pageNumber=" + nextPage);
            // var list = j.data.resultList;
            // $.each(list, function () {
            //     $(".photos").append(loadPhotos(this));
            // });
            if (page == 1) {
                $(j.data[2]).find('img').each(function(){
                    loadPhotos($(this).attr('src'));
                });
                $(".photos").html(j.data[2]);
                $(".photos").imagesLoaded(function () {
                    $(".photos").masonry({
                        itemSelector: '.box',
                        isAnimated: true
                    });
                });
            } else {
                //继续加载
                var $newElems = $(j.data[2]).css({ opacity: 0}).appendTo($(".photos"));
                $newElems.imagesLoaded(function(){
                    $newElems.animate({ opacity: 1},800);
                    $(".photos").masonry( 'appended', $newElems, true);
                });
            }
        }
    });
}

//预加载
function loadPhotos(url) {
    var img = new Image();
    //创建一个Image对象，实现图片的预下载
    img.src = url;
    if (img.complete) {
        return img.src;
    }
    img.onload = function () {
        return img.src;
    };
}

$("#mainContent").unbind("onscroll");
$("#mainContent")[0].onscroll = function () {
    if ($("#mainContent")[0].scrollTop >= $("#mainContent")[0].scrollHeight - $("#mainContent").height() ) {
        if (isTotal == true) {
            var info = new $.zui.Messager("没啦！！", {
                placement: 'top'
            });
            info.show();
            setTimeout(function () {
                info.hide();
            }, 1000)
        } else {
            loadData("photo/list.do?pageNumber=" + nextPage);
        }
    }
};