package controller;

import common.utils.PageResult;
import common.utils.ResponseUtils;
import model.TbPhotos;
import model.TbUser;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IPhotoService;
import service.IUserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */
@Controller
public class PhotoCtrl {

    @Autowired
    IPhotoService photoService;

    @RequestMapping(value = "photo.do")
    public String picture(Integer pageNumber, HttpServletRequest request, HttpServletResponse response, ModelMap map) {
        return "photo";
    }

    @RequestMapping(value = "photo/list.do")
    public void list(Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws IOException {
        pageNumber = (pageNumber == null ? 1 : pageNumber);
        PageResult result = photoService.getAll(pageNumber, 20, new Criterion[0]);
        List<TbPhotos> photosList = result.getResultList();
        String print = "";
        for (TbPhotos photos : photosList) {
            print += "<div class='box'><img src='" + photos.getAddress() + "'></div>";
        }
//        PrintWriter out = response.getWriter();
//        out.print(print);
        List<Object> objects = new ArrayList<>();
        objects.add(result.getPageNumber());
        objects.add(result.getTotalPageCount());
        objects.add(print);
        ResponseUtils.setWrite(response, "data", objects);
    }
}
