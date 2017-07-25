package service;

import common.service.IBaseService;
import model.TbUser;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/27.
 */

public interface IUserService extends IBaseService<TbUser, Serializable> {

    public TbUser getById(Integer id);

    public TbUser getByUsername(String username);

}
