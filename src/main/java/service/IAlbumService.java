package service;

import common.service.IBaseService;
import common.utils.PageResult;
import model.TbAlbum;
import org.hibernate.criterion.Criterion;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 */
public interface IAlbumService extends IBaseService<TbAlbum, Serializable> {

    public PageResult getAll(Integer pageNumber, Integer pageSize, Criterion[] criterions);

    public List<TbAlbum> getAll();

    public TbAlbum getById(Integer id);
}
