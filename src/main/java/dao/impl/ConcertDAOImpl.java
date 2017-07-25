package dao.impl;

import common.dao.impl.BaseDAOImpl;
import dao.IConcertDAO;
import model.TbConcert;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/10.
 */
@Repository("ConcertDAO")
public class ConcertDAOImpl extends BaseDAOImpl<TbConcert, Serializable> implements IConcertDAO {
    @Override
    protected Class<TbConcert> getEntityClass() {
        return TbConcert.class;
    }
}
