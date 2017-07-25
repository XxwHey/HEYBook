package controller;

import common.constant.CommonConstants;
import common.utils.PageResult;
import common.utils.ResponseUtils;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IConcertService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/9.
 */
@Controller
public class ConcertCtrl {

    @Autowired
    IConcertService concertService;

    @RequestMapping(value = "concert/list.do")
    public void list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
        pageNumber = (pageNumber == null ? 1 : pageNumber);
        PageResult result = concertService.getAll(pageNumber, CommonConstants.PAGE_SIZE, new Criterion[0]);
        ResponseUtils.setWrite(response, "data", result);
    }

}
