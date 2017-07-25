package common.dao.impl;

import common.annotation.Alias;
import common.annotation.ModelAliasLink;
import common.constant.CommonConstants;
import common.dao.IBaseDAO;
import common.model.BaseModel;
import common.utils.AfxBeanUtils;
import common.utils.PageResult;
import common.dao.callback.ParameterSetterCallback;
import org.apache.commons.lang.StringUtils;
import org.hibernate.*;
import org.hibernate.Query;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2017/2/27.
 * Hibernate基础工具实现类
 */
public abstract class BaseDAOImpl<T, ID extends Serializable> implements IBaseDAO<T, ID> {

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 根据id获取实体
     *
     * @param id
     *            唯一标识
     * @return 持久化对象
     */
    @Override
    public T get(ID id) {
        return get(id, false);
    }

    /**
     * 根据id获取实体
     *
     * @param id
     *            唯一标识
     * @param lock
     *            是否锁定，使用LockMode.UPGRADE
     * @return 持久化对象
     */
    @SuppressWarnings("unchecked")
    protected T get(ID id, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().get(getEntityClass(), id, LockMode.UPGRADE);
        } else {
            entity = (T) getSession().get(getEntityClass(), id);
        }
        return entity;
    }

    /**
     * 保存实体
     *
     * @param entity
     *            实体对象
     * @return 唯一标识
     */
    @Override
    @SuppressWarnings("unchecked")
    public ID save(T entity) {
        return (ID) this.getSession().save(entity);
    }

    /**
     * 更新实体
     *
     * @param entity
     *            实体对象
     */
    @Override
    public void update(T entity) {
        this.getSession().update(entity);
    }

    /**
     * 根据实体执行保存或者更新
     *
     * @param entity
     *            实体
     */
    @Override
    public void saveOrUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
    }

    /**
     * 根据实体执行保存或者更新(带有合并功能，能够合并session中的持久化实体和游离态的实体)
     *
     * @param entity
     *            实体
     * @return 返回合并后的实体(持久态)
     */
    @Override
    @SuppressWarnings("unchecked")
    public T merge(T entity) {
        return (T) this.getSession().merge(entity);
    }

    /**
     * 根据提供的HQL来更新数据
     *
     * @param hql
     *            自定义的HQL，(注意：写实体后面的语法即可，因为内部已经补上"UPDATE "防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    @Override
    public int updateByHQL(String hql, ParameterSetterCallback callback) {
        Query query = this.getSession().createQuery("UPDATE " + hql);
        callback.onSetter(query);
        return query.executeUpdate();
    }

    /**
     * 根据提供的SQL来更新数据
     *
     * @param sql
     *            自定义的SQL，(注意：写实体后面的语法即可，因为内部已经补上"UPDATE "防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    @Override
    public int updateBySQL(String sql, ParameterSetterCallback callback) {
        Query query = this.getSession().createSQLQuery("UPDATE " + sql);
        callback.onSetter(query);
        return query.executeUpdate();
    }

    /**
     * 查询所有的记录
     *
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listAll() {
        return this.listAll(new Order[] { Order.desc("id") });
    }

    /**
     * 查询所有的记录,并排序
     *
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     *
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listAll(Order[] orders) {
        return this.listByCriteria(null, orders, null);
    }

    /**
     * 添加排序条件
     *
     * @param criteria
     *            查询对象
     * @param orders
     *            排序对象数组
     */
    protected void addOrderToCriteria(Criteria criteria, Order[] orders) {
        if (null != orders && 0 < orders.length) {
            for (int i = 0; i < orders.length; i++) {
                criteria.addOrder(orders[i]);
            }
        }
    }

    /**
     * 添加组条件
     *
     * @param criteria
     *            查询对象
     * @param groupPropertyNames
     *            组属性名称的数组
     */
    protected void addGroupToCriteria(Criteria criteria, String[] groupPropertyNames) {
        if (null != groupPropertyNames && 0 < groupPropertyNames.length) {
            ProjectionList projectionList = Projections.projectionList();
            for (int i = 0; i < groupPropertyNames.length; i++) {
                projectionList.add(Projections.groupProperty(groupPropertyNames[i]));
            }
            criteria.setProjection(projectionList);
        }
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByProperty(String property, Object value) {
        return this.listByProperty(property, value, new Order[] { Order.desc("id") });
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByProperty(String property, Object value, Order[] orders) {
        return this.listByProperty(property, value, orders, null);
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listByProperty(String property, Object value, Order[] orders, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(new Criterion[] { Restrictions.eq(property, value) });
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        return criteria.list();
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByProperty(String property, Object value, int pageNumber, int pageSize) {
        return this.listByProperty(property, value, new Order[] { Order.desc("id") }, pageNumber, pageSize);
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByProperty(String property, Object value, Order[] orders, int pageNumber, int pageSize) {
        return this.listByProperty(property, value, orders, null, pageNumber, pageSize);
    }

    /**
     * 按属性列出对象列表
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageResult listByProperty(String property, Object value, Order[] orders, String[] groupPropertyNames, int pageNumber, int pageSize) {
        Criteria criteria = createCriteria(new Criterion[] { Restrictions.eq(property, value) });
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        Criteria criteriaCount = createCriteria(new Criterion[] { Restrictions.eq(property, value) });
        this.addGroupToCriteria(criteriaCount, groupPropertyNames);
        this.addOrderToCriteria(criteriaCount, orders);
        int totalCount = this.countByCriteria(criteriaCount);
        criteria.setFirstResult(pageSize * (pageNumber - 1));
        criteria.setMaxResults(pageSize);
        List<T> resultList = criteria.list();
        PageResult pageResult = new PageResult(pageNumber, pageSize, totalCount, resultList);
        return pageResult;
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByPropertySet(Map<String, Object> criterionMap) {
        return this.listByPropertySet(criterionMap, new Order[] { Order.desc("id") });
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByPropertySet(Map<String, Object> criterionMap, Order[] orders) {
        return this.listByPropertySet(criterionMap, orders, null);
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listByPropertySet(Map<String, Object> criterionMap, Order[] orders, String[] groupPropertyNames) {
        Set<Criterion> criterionSet = new HashSet<Criterion>();
        Iterator<String> keyIt = criterionMap.keySet().iterator();
        while (keyIt.hasNext()) {
            String key = keyIt.next();
            Object origValue = criterionMap.get(key);
            criterionSet.add(Restrictions.eq(key, origValue));
        }
        Criteria criteria = createCriteria(criterionSet.toArray(new Criterion[0]));
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        return criteria.list();
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByPropertySet(Map<String, Object> criterionMap, int pageNumber, int pageSize) {
        return this.listByPropertySet(criterionMap, new Order[] { Order.desc("id") }, pageNumber, pageSize);
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByPropertySet(Map<String, Object> criterionMap, Order[] orders, int pageNumber, int pageSize) {
        return this.listByPropertySet(criterionMap, orders, null, pageNumber, pageSize);
    }

    /**
     * 按Criterion集合条件列出记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageResult listByPropertySet(Map<String, Object> criterionMap, Order[] orders, String[] groupPropertyNames, int pageNumber, int pageSize) {
        Set<Criterion> criterionSet = new HashSet<Criterion>();
        Iterator<String> keyIt = criterionMap.keySet().iterator();
        while (keyIt.hasNext()) {
            String key = keyIt.next();
            Object origValue = criterionMap.get(key);
            criterionSet.add(Restrictions.eq(key, origValue));
        }
        Criteria criteria = createCriteria(criterionSet.toArray(new Criterion[0]));
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        Criteria criteriaCount = createCriteria(criterionSet.toArray(new Criterion[0]));
        this.addGroupToCriteria(criteriaCount, groupPropertyNames);
        this.addOrderToCriteria(criteriaCount, orders);
        int totalCount = this.countByCriteria(criteriaCount);
        criteria.setFirstResult(pageSize * (pageNumber - 1));
        criteria.setMaxResults(pageSize);
        List<T> resultList = criteria.list();
        PageResult pageResult = new PageResult(pageNumber, pageSize, totalCount, resultList);
        return pageResult;
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByCriteria(Criterion[] criterion) {
        return this.listByCriteria(criterion, "");
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体别名
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByCriteria(Criterion[] criterion, String alias) {
        return this.listByCriteria(criterion, alias, new Order[] { Order.desc("id") }, null);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByCriteria(Criterion[] criterion, Order[] orders) {
        return this.listByCriteria(criterion, orders, null);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    @Override
    public List<T> listByCriteria(Criterion[] criterion, Order[] orders, String[] groupPropertyNames) {
        return this.listByCriteria(criterion, null, orders, groupPropertyNames);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体别名
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回查询到的实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listByCriteria(Criterion[] criterion, String alias, Order[] orders, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(criterion, alias);
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        return criteria.list();
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion) {
        String alias = null;
        return this.listByCriteria(pageNumber, pageSize, criterion, alias);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体的别名
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, String alias) {
        return this.listByCriteria(pageNumber, pageSize, criterion, alias, new Order[] { Order.desc("id") }, null);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, Order[] orders) {
        return this.listByCriteria(pageNumber, pageSize, criterion, orders, null);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, Order[] orders, String[] groupPropertyNames) {
        return this.listByCriteria(pageNumber, pageSize, criterion, null, orders, groupPropertyNames);
    }

    /**
     * 按Criterion查询列表数据.
     *
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @param criterion
     *            数量可变的Criterion.
     * @param alias
     *            当前实体别名
     * @param orders
     *            指定的排序条件,默认是倒序Id排列
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     *
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageResult listByCriteria(int pageNumber, int pageSize, Criterion[] criterion, String alias, Order[] orders, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(criterion, alias);// 别名
        this.addGroupToCriteria(criteria, groupPropertyNames);
        this.addOrderToCriteria(criteria, orders);
        Criteria criteriaCount = createCriteria(criterion, alias);// 别名
        this.addGroupToCriteria(criteriaCount, groupPropertyNames);
        this.addOrderToCriteria(criteriaCount, orders);
        int totalCount = this.countByCriteria(criteriaCount);
        if (pageNumber > 0 && pageSize > 0) {
            criteria.setFirstResult(pageSize * (pageNumber - 1));
            criteria.setMaxResults(pageSize);
        }
        List<T> resultList = criteria.list();
        PageResult pageResult = new PageResult(pageNumber, pageSize, totalCount, resultList);
        return pageResult;
    }

    /**
     * 根据HQL查询记录
     *
     * @param hql
     *            自定义的HQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回查询到的实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<T> listByHQL(String hql, ParameterSetterCallback callback) {
        Query query = this.getSession().createQuery(hql);
        callback.onSetter(query);
        return query.list();
    }

    /**
     * 计算总分页数
     *
     * @param totalCount
     *            条目总数
     * @param pageSize
     *            每页大小
     * @return 总分页数
     */
    private Integer calcTotalPagesCount(Integer totalCount, Integer pageSize) {
        int litlePageCount = (totalCount % pageSize);
        int mainPageCount = (totalCount / pageSize);
        return (litlePageCount == 0 ? mainPageCount : mainPageCount + 1);
    }

    /**
     * 根据HQL查询记录
     *
     * @param hql
     *            自定义的HQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的实体的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageResult listByHQL(String hql, ParameterSetterCallback callback, int pageNumber, int pageSize) {
        Query query = this.getSession().createQuery(hql);
        int totalCount = this.countByHQLQuery(query, callback);
        callback.onSetter(query);
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);
        List<T> resultList = query.list();
        PageResult pageResult = new PageResult(pageNumber, pageSize, totalCount, resultList);
        return pageResult;
    }

    /**
     * 根据SQL查询记录
     *
     * @param sql
     *            自定义的SQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @return 返回查询到的记录
     */
    @Override
    public List<?> listBySQL(String sql, ParameterSetterCallback callback) {
        Query query = this.getSession().createSQLQuery(sql);
        callback.onSetter(query);
        return query.list();
    }

    /**
     * 根据SQL查询记录
     *
     * @param sql
     *            自定义的SQL
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @param pageNumber
     *            页码，大于0
     * @param pageSize
     *            页面大小，大于0
     * @return 返回封装了查询到的记录的分页对象
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageResult listBySQL(String sql, ParameterSetterCallback callback, int pageNumber, int pageSize) {
        SQLQuery query = this.getSession().createSQLQuery(sql);
        int totalCount = this.countBySQLQuery(query, callback);
        callback.onSetter(query);
        query.setFirstResult(pageSize * (pageNumber - 1));
        query.setMaxResults(pageSize);
        List<List<?>> resultList = query.list();
        PageResult pageResult = new PageResult(pageNumber, pageSize, totalCount, resultList);
        return pageResult;
    }

    /**
     * 按属性获取唯一对象
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回唯一的实体
     */
    @Override
    public T getUniqueByProperty(String property, Object value) {
        return this.getUniqueByProperty(property, value, null);
    }

    /**
     * 按属性获取唯一对象
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 返回唯一的实体
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getUniqueByProperty(String property, Object value, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(new Criterion[] { Restrictions.eq(property, value) });
        this.addGroupToCriteria(criteria, groupPropertyNames);
        return (T) criteria.uniqueResult();
    }

    /**
     * 创建默认的Criteria,后续可进行更多处理,辅助函数.
     *
     * @return 返回创建好的Criteria对象(无别名)
     */
    protected Criteria createCriteria() {
        return this.createCriteria(null);
    }

    /**
     * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
     *
     * @param criterions
     *            多个条件
     * @return 返回创建好的Criteria对象(无别名)
     */
    protected Criteria createCriteria(Criterion[] criterions) {
        return this.createCriteria(criterions, null);
    }

    /**
     * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
     *
     * @param criterions
     *            多个条件
     * @param alias
     *            指定别名
     * @return 返回创建好的Criteria对象
     */
    protected Criteria createCriteria(Criterion[] criterions, String alias) {
        Criteria criteria = (StringUtils.isNotBlank(alias) ? getSession().createCriteria(getEntityClass(), alias) : getSession().createCriteria(getEntityClass()));
        /**
         * Edit by Bin.20140423 为离线关联的子对象建立别名引用
         */
        Field[] fields = AfxBeanUtils.getAllFields(getEntityClass());
        for (int index = 0; index < fields.length; index++) {
            Field field = fields[index];
            Class<?> fieldClass = field.getType();
            if (!fieldClass.isPrimitive() && !fieldClass.isArray()) {
                String pageName = fieldClass.getPackage().getName();
                if (CommonConstants.MODEL_PAGECKAGE_NAME.equals(pageName) || BaseModel.class.isAssignableFrom(fieldClass)) {
                    ModelAliasLink modelAlias = field.getAnnotation(ModelAliasLink.class);
                    if (null != modelAlias) {
                        String thisAlias = modelAlias.thisAlias();
                        if (StringUtils.isNotBlank(thisAlias)) {
                            String aliasName = thisAlias;
                            criteria.createAlias(field.getName(), aliasName);
                            Alias[] allAlias = modelAlias.alias();
                            if (0 < allAlias.length) {
                                for (int i = 0; i < allAlias.length; i++) {
                                    Alias nowAlias = allAlias[i];
                                    criteria.createAlias(nowAlias.item(), nowAlias.alias());
                                }
                            }
                        } else {
                            String aliasName = field.getName();
                            criteria.createAlias(aliasName, aliasName);
                        }
                    }
                }
            }
        }
        if (null != criterions) {
            for (int i = 0; i < criterions.length; i++) {
                criteria.add(criterions[i]);
            }
        }
        return criteria;
    }

    /**
     * 创建一个DetachedCriteria查询对象，默认要查询的类型是当前支持实体
     *
     * @return DetachedCriteria查询对象
     */
    @Override
    public DetachedCriteria createDetachedCriteria() {
        return this.createDetachedCriteria(this.getEntityClass());
    }

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @return DetachedCriteria查询对象
     */
    @Override
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz) {
        return this.createDetachedCriteria(entityClz, null);
    }

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @param alias
     *            实体的别名
     * @return DetachedCriteria查询对象
     */
    @Override
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz, String alias) {
        return this.createDetachedCriteria(entityClz, null, alias);
    }

    /**
     * 创建一个DetachedCriteria查询对象
     *
     * @param entityClz
     *            要针对创建的实体类类对象
     * @param criterions
     *            多个条件
     * @param alias
     *            实体的别名
     * @return DetachedCriteria查询对象
     */
    @Override
    public DetachedCriteria createDetachedCriteria(Class<?> entityClz, Criterion[] criterions, String alias) {
        DetachedCriteria detachedCriteria = StringUtils.isNotBlank(alias) ? DetachedCriteria.forClass(entityClz, alias) : DetachedCriteria.forClass(entityClz);
        Field[] fields = AfxBeanUtils.getAllFields(entityClz);
        for (int index = 0; index < fields.length; index++) {
            Field field = fields[index];
            Class<?> fieldClass = field.getType();
            if (!fieldClass.isPrimitive() && !fieldClass.isArray()) {
                String pageName = fieldClass.getPackage().getName();
                if (CommonConstants.MODEL_PAGECKAGE_NAME.equals(pageName) || BaseModel.class.isAssignableFrom(fieldClass)) {
                    ModelAliasLink modelAlias = field.getAnnotation(ModelAliasLink.class);
                    if (null != modelAlias) {
                        String thisAlias = modelAlias.thisAlias();
                        if (StringUtils.isNotBlank(thisAlias)) {
                            String aliasName = thisAlias;
                            detachedCriteria.createAlias(field.getName(), aliasName);
                            Alias[] allAlias = modelAlias.alias();
                            if (0 < allAlias.length) {
                                for (int i = 0; i < allAlias.length; i++) {
                                    Alias nowAlias = allAlias[i];
                                    detachedCriteria.createAlias(nowAlias.item(), nowAlias.alias());
                                }
                            }
                        } else {
                            String aliasName = field.getName();
                            detachedCriteria.createAlias(aliasName, aliasName);
                        }
                    }
                }
            }
        }
        if (null != criterions) {
            for (int i = 0; i < criterions.length; i++) {
                detachedCriteria.add(criterions[i]);
            }
        }
        return detachedCriteria;
    }

    /**
     * 按属性统计记录数
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 记录数
     */
    protected int countByProperty(String property, Object value) {
        return this.countByProperty(property, value, null);
    }

    /**
     * 按属性统计记录数
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 记录数
     */
    protected int countByProperty(String property, Object value, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(new Criterion[] { Restrictions.eq(property, value) });
        this.addGroupToCriteria(criteria, groupPropertyNames);
        return ((Number) (criteria.setProjection(Projections.rowCount()).uniqueResult())).intValue();
    }

    /**
     * 按criteria统计记录数
     *
     * @param criteria
     *            指定的criteria
     * @return 记录数
     */
    protected int countByCriteria(Criteria criteria) {
        Criteria criteria1 = criteria.setProjection(Projections.rowCount());
        Object o = criteria1.uniqueResult();
        Number o1 = (Number) o;
        int i = o1.intValue();
        return i;
        //return ((Number) (criteria.setProjection(Projections.rowCount()).uniqueResult())).intValue();
    }

    /**
     * 按criterions统计记录数
     *
     * @param criterions
     *            指定的criterions数组
     * @param groupPropertyNames
     *            组属性名，指定的属性划分组 ,默认是无组
     * @return 记录数
     */
    @Override
    public int countByCriteria(Criterion[] criterions, String[] groupPropertyNames) {
        Criteria criteria = createCriteria(criterions);
        this.addGroupToCriteria(criteria, groupPropertyNames);
        return this.countByCriteria(criteria);
    }

    /**
     * 按criterions统计记录数
     *
     * @param criterions
     *            指定的criterions数组
     * @return 记录数
     */
    @Override
    public int countByCriteria(Criterion[] criterions) {
        return this.countByCriteria(criterions, null);
    }

    /**
     * 按指定的SQL语句统计记录数
     *
     * @param sql
     *            sql查询语句
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    @Override
    public int countBySQLQuery(String sql, final ParameterSetterCallback callback) {
        String countSQL = "SELECT COUNT(*) " + sql;
        Query queryCount = this.getSession().createSQLQuery(countSQL);
        callback.onSetter(queryCount);
        Number number = (Number) queryCount.uniqueResult();
        return number.intValue();
    }

    /**
     * 按指定的HQL语句统计记录数
     *
     * @param hql
     *            hql查询语句
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    @Override
    public int countByHQLQuery(String hql, final ParameterSetterCallback callback) {
        String countHQL = "SELECT COUNT(*) " + hql;
        Query queryCount = this.getSession().createQuery(countHQL);
        callback.onSetter(queryCount);
        Number number = (Number) queryCount.uniqueResult();
        return number.intValue();
    }

    /**
     * 按存在的query统计记录数
     *
     * @param query
     *            指定存在的query
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    protected int countByHQLQuery(final Query query, final ParameterSetterCallback callback) {
        String hql = query.getQueryString();
        String countHQL = "SELECT COUNT(*) " + hql;
        Query queryCount = this.getSession().createQuery(countHQL);
        callback.onSetter(queryCount);
        Number number = (Number) queryCount.uniqueResult();
        return number.intValue();
    }

    /**
     * 按存在的sql query统计记录数
     *
     * @param query
     *            指定存在的sql query
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 记录数
     */
    protected int countBySQLQuery(final SQLQuery query, final ParameterSetterCallback callback) {
        String sql = query.getQueryString();
        String countSQL = "SELECT COUNT(*) FROM (" + sql + ") _T";
        Query queryCount = this.getSession().createSQLQuery(countSQL);
        callback.onSetter(queryCount);
        Number number = (Number) queryCount.uniqueResult();
        return number.intValue();
    }

    /**
     * 根据id删除记录
     *
     * @param id
     *            唯一标识
     * @return 返回受影响的行数
     */
    @Override
    public Integer deleteById(ID id) {
        this.deleteByEntity(this.get(id));
        return 1;
    }

    /**
     * 根据多个id删除记录
     *
     * @param ids
     *            多个唯一标识
     * @return 返回受影响的行数
     */
    @Override
    public Integer deleteByIds(ID[] ids) {
        int count = 0;
        for (int i = 0; i < ids.length; i++) {
            count += (this.deleteById(ids[i]));
        }
        return count;
    }

    /**
     * 根据实体删除记录
     *
     * @param entity
     *            实体
     */
    @Override
    public void deleteByEntity(T entity) {
        this.getSession().delete(entity);
    }

    /**
     * 根据多个实体删除记录
     *
     * @param entitys
     *            多个实体
     */
    @Override
    public void deleteByEntitys(T[] entitys) {
        for (int i = 0; i < entitys.length; i++) {
            this.deleteByEntity(entitys[i]);
        }
    }

    /**
     * 根据属性值删除记录
     *
     * @param property
     *            属性名
     * @param value
     *            属性值
     * @return 返回受影响的行数
     */
    @Override
    public int deleteByProperty(String property, Object value) {
        List<T> deleteEntityList = this.listByProperty(property, value);
        for (int i = 0; i < deleteEntityList.size(); i++) {
            T entity = deleteEntityList.get(i);
            this.deleteByEntity(entity);
        }
        return deleteEntityList.size();
    }

    /**
     * 根据属性集合删除记录
     *
     * @param criterionMap
     *            Criterion集合条件,用键值对匹配
     * @return 返回受影响的行数
     */
    @Override
    public int deleteByPropertySet(Map<String, Object> criterionMap) {
        List<T> deleteEntityList = this.listByPropertySet(criterionMap);
        for (int i = 0; i < deleteEntityList.size(); i++) {
            T entity = deleteEntityList.get(i);
            this.deleteByEntity(entity);
        }
        return deleteEntityList.size();
    }

    /**
     * 根据过滤条件删除记录
     *
     * @param criterions
     *            过滤条件数组
     * @return 返回受影响的行数
     */
    @Override
    public Integer deleteByCriteria(Criterion[] criterions) {
        return this.deleteByCriteria(criterions, null);
    }

    /**
     * 根据过滤条件删除记录
     *
     * @param criterions
     *            过滤条件数组
     * @param alias
     *            查询中包括的主别名
     * @return 返回受影响的行数
     */
    @Override
    public Integer deleteByCriteria(Criterion[] criterions, String alias) {
        List<T> deleteEntityList = this.listByCriteria(criterions, alias);
        for (int i = 0; i < deleteEntityList.size(); i++) {
            T entity = deleteEntityList.get(i);
            this.deleteByEntity(entity);
        }
        return deleteEntityList.size();
    }

    /**
     * 根据提供的HQL来删除数据
     *
     * @param hql
     *            自定义的HQL，(注意：写实体后面的语法即可，因为内部已经补上"DELETE FROM"防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理HQL中对应参数的设置
     * @return 返回受影响的行数
     */
    @Override
    public int deleteByHQL(String hql, ParameterSetterCallback callback) {
        Query query = this.getSession().createQuery("DELETE FROM " + hql);
        callback.onSetter(query);
        return query.executeUpdate();
    }

    /**
     * 根据提供的SQL来删除数据
     *
     * @param sql
     *            自定义的SQL，(注意：写实体后面的语法即可，因为内部已经补上"DELETE FROM"防止其他用途)
     * @param callback
     *            用来处理参数设置问题的回调函数，用来处理SQL中对应参数的设置
     * @return 返回受影响的行数
     */
    @Override
    public int deleteBySQL(String sql, ParameterSetterCallback callback) {
        Query query = this.getSession().createSQLQuery("DELETE FROM " + sql);
        callback.onSetter(query);
        return query.executeUpdate();
    }

    /**
     * 获得Dao对于的实体类
     *
     * @return 实体类类型
     */
    abstract protected Class<T> getEntityClass();

}
