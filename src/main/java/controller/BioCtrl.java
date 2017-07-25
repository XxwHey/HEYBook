package controller;

import model.TbAlbum;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */
@Controller
public class BioCtrl {

    @Autowired
    IAlbumService albumService;

    @RequestMapping(value = "biography.do")
    public String biography(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

        List<TbAlbum> result = albumService.getAll();
        Collections.sort(result, (o1, o2) -> Integer.valueOf(Integer.parseInt(o2.getCreateTime().replace("-", ""))).compareTo(Integer.parseInt(o1.getCreateTime().replace("-", ""))));

        modelMap.put("result", result);
        return "biography";
    }
}
