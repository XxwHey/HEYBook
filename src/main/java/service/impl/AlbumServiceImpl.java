package service.impl;

import common.service.Impl.BaseServiceImpl;
import common.utils.PageResult;
import dao.IAlbumDAO;
import model.TbAlbum;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IAlbumService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
@Service("AlbumService")
@Transactional
public class AlbumServiceImpl extends BaseServiceImpl<TbAlbum, Serializable> implements IAlbumService {

    @Autowired
    private IAlbumDAO albumDAO;

    @Override
    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions) {
        return albumDAO.listByCriteria(pageNumber, pageSize, criterions);
    }

    @Override
    public List<TbAlbum> getAll() {
        return albumDAO.listAll();
    }

    @Override
    public TbAlbum getById(Integer id) {
        return albumDAO.get(id);
    }
}
