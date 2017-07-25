/**
 * Created by Administrator on 2017/3/10.
 */
//@ sourceURL = common.js
//全选
$(".checkAll").click(function () {
    if (this.checked) {
        $("input[name='checkbox']:checkbox").prop('checked', true);
    } else {
        $("input[name='checkbox']:checkbox").prop('checked', false);
    }
});

//分页
function paging(page, btnURL) {
    $(".previous").html('<a href="#"><i class="icon-arrow-left"></i> 上一页</a>');
    $(".previous").removeClass("disabled");
    $(".next").html('<a href="#">下一页 <i class="icon-arrow-right"></i></a>');
    $(".next").removeClass("disabled");
    if (page.pageNumber == 1) {
        $(".previous").html('<a href="#"><i class="icon-arrow-left"></i> 没有上一页</a>');
        $(".previous").addClass("disabled");
    } else {
        if (page.pageNumber == page.totalPageCount) {
            $(".next").html('<a href="#">没有下一页 <i class="icon-arrow-right"></i></a>');
            $(".next").addClass("disabled");
        }
    }
    var pageUp = (page.pageNumber <= 1 ? 1 : page.pageNumber - 1);
    var pageDown = (page.pageNumber >= page.totalPageCount ? page.totalPageCount : page.pageNumber + 1);
    $(".previous").attr("onclick", "loadData('" + btnURL + "?pageNumber=" + pageUp + "')");
    $(".next").attr("onclick", "loadData('" + btnURL + "?pageNumber=" + pageDown + "')");
}