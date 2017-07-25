package dao.impl;

import common.dao.impl.BaseDAOImpl;
import dao.IAlbumDAO;
import model.TbAlbum;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/2.
 */
@Repository("AlbumDAO")
public class AlbumDAOImpl extends BaseDAOImpl<TbAlbum, Serializable> implements IAlbumDAO {
    @Override
    protected Class<TbAlbum> getEntityClass() {
        return TbAlbum.class;
    }
}
