package controller;

import common.utils.PageResult;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAlbumService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Controller
public class LoadCtrl {

    @Autowired
    IAlbumService albumService;

    @RequestMapping(value = "music.do")
    public String music() {
        return "music";
    }

    @RequestMapping(value = "album.do")
    public String album(ModelMap modelMap) {
        PageResult result = albumService.getAll(1, 8, new Criterion[0]);
        List<Integer> pageCount = new ArrayList<>();
        for (int i = 2; i < result.getTotalPageCount() + 1; i++) {
            pageCount.add(i);
        }
        modelMap.put("pageCount", pageCount);
        return "album";
    }

    @RequestMapping(value = "concert.do")
    public String concert() {
        return "concert";
    }

}
