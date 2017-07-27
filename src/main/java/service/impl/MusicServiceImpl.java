package service.impl;

import common.service.Impl.BaseServiceImpl;
import common.utils.CommonUtils;
import common.utils.PageResult;
import dao.IMusicDAO;
import model.TbMusic;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IMusicService;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
@Service("MusicService")
@Transactional
public class MusicServiceImpl extends BaseServiceImpl<TbMusic, Serializable> implements IMusicService {

    @Autowired
    private IMusicDAO musicDAO;

    @Override
    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions) {
        return musicDAO.listByCriteria(pageNumber, pageSize, criterions);
    }

    @Override
    public List<TbMusic> getAll(Criterion[] criterions) {
        return musicDAO.listByCriteria(criterions);
    }

    @Override
    public List<TbMusic> getByAlbumId(Integer albumId) {
        return musicDAO.listByCriteria(new Criterion[]{Restrictions.eq("albumId", albumId)});
    }

}