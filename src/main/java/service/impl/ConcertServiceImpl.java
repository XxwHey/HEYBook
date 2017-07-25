package service.impl;

import common.service.Impl.BaseServiceImpl;
import common.utils.PageResult;
import dao.IConcertDAO;
import model.TbConcert;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IConcertService;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service("ConcertService")
@Transactional
public class ConcertServiceImpl extends BaseServiceImpl<TbConcert, Serializable> implements IConcertService {

    @Autowired
    private IConcertDAO concertDAO;

    @Override
    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions) {
        return concertDAO.listByCriteria(pageNumber, pageSize, criterions);
    }
}
