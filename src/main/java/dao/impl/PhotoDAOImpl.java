package dao.impl;

import common.dao.impl.BaseDAOImpl;
import dao.IPhotoDAO;
import model.TbPhotos;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */
@Repository("PhotoDAO")
public class PhotoDAOImpl extends BaseDAOImpl<TbPhotos, Serializable> implements IPhotoDAO {
    @Override
    protected Class<TbPhotos> getEntityClass() {
        return TbPhotos.class;
    }
}
