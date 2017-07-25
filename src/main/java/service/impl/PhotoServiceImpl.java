package service.impl;

import common.service.Impl.BaseServiceImpl;
import common.utils.PageResult;
import dao.IPhotoDAO;
import model.TbPhotos;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IPhotoService;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */
@Service("PhotoService")
@Transactional
public class PhotoServiceImpl extends BaseServiceImpl<TbPhotos, Serializable> implements IPhotoService {

    @Autowired
    private IPhotoDAO photoDAO;

    @Override
    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions) {
        return photoDAO.listByCriteria(pageNumber, pageSize, criterions);
    }
}
