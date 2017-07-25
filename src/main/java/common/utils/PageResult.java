package common.utils;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 * 分页对象，接收分页查询结果
 */
public class PageResult {

    private int pageNumber;     //页码
    private int pageSize;       //页大小
    private int totalCount;     //总数据量
    private int totalPageCount; //总页数
    private List resultList;    //数据结果列表

    public PageResult() {
    }

    public PageResult(int pageNumber, int pageSize, int totalCount, List resultList) {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.resultList = resultList;
        if (totalCount == 0) {
            this.pageNumber = 0;
            this.totalPageCount = 0;
        } else {
            this.pageNumber = pageNumber;
            int temp = totalCount / pageSize;
            this.totalPageCount = (totalCount % pageSize == 0) ? temp : temp+1;
        }
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public List getResultList() {
        return resultList;
    }

    public void setResultList(List resultList) {
        this.resultList = resultList;
    }
}
