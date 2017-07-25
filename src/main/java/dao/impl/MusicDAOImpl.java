package dao.impl;

import common.dao.impl.BaseDAOImpl;
import dao.IMusicDAO;
import model.TbMusic;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/1.
 */
@Repository("MusicDAO")
public class MusicDAOImpl extends BaseDAOImpl<TbMusic, Serializable> implements IMusicDAO {
    @Override
    protected Class<TbMusic> getEntityClass() {
        return TbMusic.class;
    }
}
