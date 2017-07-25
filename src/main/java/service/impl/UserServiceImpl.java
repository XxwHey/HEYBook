package service.impl;

import common.service.Impl.BaseServiceImpl;
import dao.IUserDAO;
import model.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.IUserService;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/27.
 */
@Service("UserService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<TbUser, Serializable> implements IUserService {

    @Autowired
    public IUserDAO userDAO;

    @Override
    public TbUser getById(Integer id) {
        return userDAO.get(id);
    }

    @Override
    public TbUser getByUsername(String username) {
        return userDAO.getUniqueByProperty("username", username);
    }

}
