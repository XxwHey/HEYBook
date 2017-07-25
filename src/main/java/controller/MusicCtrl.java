package controller;

import common.constant.CommonConstants;
import common.utils.PageResult;
import common.utils.ResponseUtils;
import model.TbAlbum;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAlbumService;
import service.IMusicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
@Controller
public class MusicCtrl {

    @Autowired
    IMusicService musicService;

    @Autowired
    IAlbumService albumService;

    @RequestMapping(value = "music/list.do")
    public void music(Integer pageNumber, HttpServletRequest request, HttpServletResponse response) {
        pageNumber = (pageNumber == null ? 1 : pageNumber);
        PageResult result = musicService.getAll(pageNumber, 15, new Criterion[0]);
        ResponseUtils.setWrite(response, "data", result);
    }

}
