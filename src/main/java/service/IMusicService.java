package service;

import common.service.IBaseService;
import common.utils.PageResult;
import model.TbMusic;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */
public interface IMusicService extends IBaseService<TbMusic, Serializable> {

    PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions);

    List<TbMusic> getAll(Criterion[] criterions);

    List<TbMusic> getByAlbumId(Integer albumId);

}
