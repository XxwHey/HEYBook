package service;

import common.service.IBaseService;
import common.utils.PageResult;
import model.TbConcert;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface IConcertService extends IBaseService<TbConcert, Serializable> {

    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions);
}
