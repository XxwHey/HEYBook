package service;

import common.service.IBaseService;
import common.utils.PageResult;
import model.TbPhotos;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */
public interface IPhotoService extends IBaseService<TbPhotos, Serializable> {

    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions);
}
