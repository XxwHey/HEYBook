package dao.impl;

import common.dao.impl.BaseDAOImpl;
import dao.IUserDAO;
import model.TbUser;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/27.
 */
@Repository("UserDAO")
public class UserDAOImpl extends BaseDAOImpl<TbUser, Serializable> implements IUserDAO {
    @Override
    protected Class<TbUser> getEntityClass() {
        return TbUser.class;
    }
}
