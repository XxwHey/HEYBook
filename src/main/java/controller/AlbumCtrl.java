package controller;

import common.utils.PageResult;
import common.utils.ResponseUtils;
import model.TbAlbum;
import model.TbMusic;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.IAlbumService;
import service.IMusicService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Controller
public class AlbumCtrl {

    @Autowired
    IAlbumService albumService;

    @Autowired
    IMusicService musicService;

    @RequestMapping(value = "album/list.do")
    public void album(HttpServletRequest request, HttpServletResponse response) {
        List<TbAlbum> albums = albumService.getAll();
        ResponseUtils.setWrite(response, "data", albums);
    }

    @RequestMapping(value = "album/details.do")
    public void details(Integer id, HttpServletRequest request, HttpServletResponse response) {
        TbAlbum album = albumService.getById(id);
        List<TbMusic> musics = musicService.getByAlbumId(id);
        List<Object> objects = new ArrayList<>();
        Integer exist = 1;
        if (musics == null) {
            exist = 0;
        }
        objects.add(album);
        objects.add(musics);
        objects.add(exist);
        ResponseUtils.setWrite(response, "data", objects);
    }
}
